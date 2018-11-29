package sunny.koranpagi.feature.fragment.game_fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast

import sunny.koranpagi.R
import sunny.koranpagi.adapter.NewsHiburanAdapter
import sunny.koranpagi.entity.NewsGames
import sunny.koranpagi.entity.NewsHiburan
import sunny.koranpagi.feature.activity.MainActivity
import sunny.koranpagi.feature.fragment.base.ContractBaseFragment
import sunny.koranpagi.feature.fragment.base.PresentBaseFragment
import sunny.koranpagi.rest.NewsApi
import sunny.koranpagi.utils.Constant
import sunny.koranpagi.utils.MessageEventHiburan
import sunny.koranpagi.utils.RxBus


class EntertainmentFragment : Fragment(), ContractBaseFragment.mainHiburanView {

    lateinit var pbar: ProgressBar
    lateinit var rv: RecyclerView
    lateinit var swp: SwipeRefreshLayout

    lateinit var api: NewsApi
    lateinit var newsss: NewsHiburan

    lateinit var prefs: SharedPreferences

    lateinit var adapter: NewsHiburanAdapter
    lateinit var present: ContractBaseFragment.mainPresent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val game = inflater.inflate(R.layout.fragment_second, container, false)
        init(game)
        return game
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        action()
        listenEvent()
    }

    override fun init(v: View) {
        Log.d("FLOW", "Ent.Init")
        api = NewsApi()
        present = PresentBaseFragment()

        prefs = context!!.getSharedPreferences(requireContext().packageName, 0)

        pbar = v.findViewById(R.id.mainProgressBar)
        rv = v.findViewById(R.id.rv_hiburan)
        swp = v.findViewById(R.id.swp_refresh)

        swp.setOnRefreshListener { action() }
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    // Scrolling
                    RxBus.publish("tablayout|up")
                } else {
                    // Scrolling down
                    RxBus.publish("tablayout|down")
                }
            }
        })

    }

    override fun action() {
        Log.d("FLOW", "Ent.action")
        present.getHiburanNews(api, "id", "entertainment", Constant.HiburanFragmentBus)
    }

    override fun updateUI(it: NewsHiburan) {
        Log.d("FLOW", "Ent.UpdateUI")

        val editor = prefs.edit()
        editor.putString(Constant.EntNewsKey, it.articles[0].urlToImage).commit()
        Log.d("cek", prefs.getString(Constant.EntNewsKey, ""))

        if (it.status == "ok") {
            Toast.makeText(requireContext(), "succes", Toast.LENGTH_SHORT).show()
            Log.d("FLOW", it.status)

            newsss = it
            val layoutManager12 = LinearLayoutManager(context)
//            val layoutManager12 = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = NewsHiburanAdapter(requireContext(), newsss.articles)
            rv.layoutManager = layoutManager12
            rv.adapter = adapter
            adapter.notifyDataSetChanged()

            if (swp.isRefreshing) swp.isRefreshing = false
        } else {
            Toast.makeText(requireContext(), "fail", Toast.LENGTH_SHORT).show()
        }
//        present.getTechNews(api, "id", "technology", Constant.TechnoFragmentBus)

    }

    override fun showLoading() {
        pbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbar.visibility = View.GONE
    }

    override fun listenEvent() {
        Log.d("FLOW", "Ent.ListEvent")
        RxBus.listen(String::class.java).subscribe({

            if (it == Constant.HiburanFragmentBus) {
                hideLoading()
                RxBus.listen(NewsHiburan::class.java).subscribe({
                    updateUI(it)
                })
            } else if (it == "loading") {
                showLoading()
            }
        })
    }

}
