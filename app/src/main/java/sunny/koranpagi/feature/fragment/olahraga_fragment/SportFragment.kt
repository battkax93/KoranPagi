package sunny.koranpagi.feature.fragment.olahraga_fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast

import sunny.koranpagi.R
import sunny.koranpagi.adapter.NewsSportAdapter
import sunny.koranpagi.entity.NewsSport
import sunny.koranpagi.feature.base.ContractBaseFragment
import sunny.koranpagi.feature.base.PresentBaseFragment
import sunny.koranpagi.rest.NewsApi
import sunny.koranpagi.utils.Constant
import sunny.koranpagi.utils.RxBus

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SportFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SportFragment : Fragment(), ContractBaseFragment.mainOlahragaView {

    lateinit var pbar: ProgressBar
    lateinit var rv: RecyclerView
    lateinit var swp: SwipeRefreshLayout

    lateinit var api: NewsApi
    lateinit var newsss: NewsSport

    lateinit var prefs: SharedPreferences

    lateinit var adapter: NewsSportAdapter
    lateinit var present: ContractBaseFragment.mainPresent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val sport = inflater.inflate(R.layout.fragment_sport, container, false)
        init(sport)
        return sport
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenEvent()
    }

    override fun init(v: View) {
        Log.d("FLOW", "Sport.init")

        api = NewsApi()
        present = PresentBaseFragment()

        prefs = context!!.getSharedPreferences(requireContext().packageName, 0)

        pbar = v.findViewById(R.id.mainProgressBar)
        rv = v.findViewById(R.id.rv_sport)
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
        Log.d("FLOW", "Sport.action")

        present.getTechNews(api, "id", "sport", Constant.SportFragmentBus)
    }

    override fun updateUI(it: NewsSport) {
        Log.d("FLOW", "Sport.UpdateUI")

        val editor = prefs.edit()
        editor.putString(Constant.sportNewsKey, it.articles[0].urlToImage).commit()
        Log.d("CEK", prefs.getString(Constant.sportNewsKey, ""))

        if (it.status == "ok") {

            Toast.makeText(requireContext(), "succes", Toast.LENGTH_SHORT).show()

            newsss = it
            val layoutManager12 = LinearLayoutManager(context)
            adapter = NewsSportAdapter(requireContext(),requireActivity(), newsss.articles)
            rv.layoutManager = layoutManager12
            rv.adapter = adapter
            adapter.notifyDataSetChanged()

            if (swp.isRefreshing) swp.isRefreshing = false
        } else {
            Toast.makeText(requireContext(), "fail", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showLoading() {
        pbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbar.visibility = View.GONE
    }

    override fun listenEvent() {
        Log.d("FLOW", "Sport.ListEvent")
        RxBus.listen(String::class.java).subscribe({

            if (it == Constant.SportFragmentBus) {
                hideLoading()
                RxBus.listen(NewsSport::class.java).subscribe({
                    updateUI(it)
                })
            } else if (it == "loading") {
                showLoading()
            }
        })
    }
}// Required empty public constructor
