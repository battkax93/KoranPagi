package sunny.koranpagi.feature.fragment.teknologi_fragment

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
import sunny.koranpagi.adapter.NewsHiburanAdapter
import sunny.koranpagi.adapter.NewsTechnoAdapter
import sunny.koranpagi.entity.NewsHiburan
import sunny.koranpagi.entity.NewsTechno
import sunny.koranpagi.feature.fragment.base.ContractBaseFragment
import sunny.koranpagi.feature.fragment.base.PresentBaseFragment
import sunny.koranpagi.rest.NewsApi
import sunny.koranpagi.utils.Constant
import sunny.koranpagi.utils.MessageEventHiburan
import sunny.koranpagi.utils.MessageEventTechno
import sunny.koranpagi.utils.RxBus

class TechnologyFragment : Fragment(), ContractBaseFragment.mainTeknologiView {

    lateinit var pbar: ProgressBar
    lateinit var rv: RecyclerView
    lateinit var swp: SwipeRefreshLayout

    lateinit var api: NewsApi
    lateinit var newsss: NewsTechno

    lateinit var adapter: NewsTechnoAdapter
    lateinit var present: ContractBaseFragment.mainPresent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val tech = inflater.inflate(R.layout.fragment_technology, container, false)
        init(tech)
        return tech
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        action()
        listenEvent()
    }

    override fun init(v: View) {
        Log.d("FLOW", "Tech.init")

        api = NewsApi()
        pbar = v.findViewById(R.id.mainProgressBar)
        rv = v.findViewById(R.id.rv_tekno)
        swp = v.findViewById(R.id.swp_refresh)
        present = PresentBaseFragment()

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
        Log.d("FLOW", "Tech.action")
        present.getTechNews(api, "id", "technology", Constant.TechnoFragmentBus)
    }

    override fun updateUI(it: NewsTechno) {
        Log.d("FLOW", "Tech.UpdateUI")
        if (it.status == "ok") {
            Toast.makeText(requireContext(), "succes", Toast.LENGTH_SHORT).show()
            Log.d("FLOW", it.status)

            newsss = it
            val layoutManager12 = LinearLayoutManager(context)
//            val layoutManager12 = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = NewsTechnoAdapter(requireContext(), newsss.articles)
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
        Log.d("FLOW", "Tech.ListEvent")
        RxBus.listen(String::class.java).subscribe({

            if (it == Constant.TechnoFragmentBus) {
                hideLoading()
                RxBus.listen(NewsTechno::class.java).subscribe({
                    updateUI(it)
                })
            } else if (it == "loading") {
                showLoading()
            }
        })
    }

}// Required empty public constructor
