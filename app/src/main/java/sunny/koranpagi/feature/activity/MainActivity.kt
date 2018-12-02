package sunny.koranpagi.feature.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_tab.*
import sunny.koranpagi.R
import sunny.koranpagi.adapter.TabLayoutAdapter
import sunny.koranpagi.feature.base.ContractBaseFragment
import sunny.koranpagi.feature.base.PresentBaseFragment
import sunny.koranpagi.feature.fragment.slider_fragment.SliderFragment
import sunny.koranpagi.rest.NewsApi
import sunny.koranpagi.utils.Constant
import sunny.koranpagi.utils.RxBus

class MainActivity : AppCompatActivity() {

    lateinit var api: NewsApi
    lateinit var present: ContractBaseFragment.mainPresent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        trySetup()
        requestData()
        loadFragment()
        appBarListener()
    }

    fun init() {
        Log.d("FLOW", "Main.init")
        val fragAdapter = TabLayoutAdapter(supportFragmentManager)
        viewpager.offscreenPageLimit = 3
        viewpager.adapter = fragAdapter
        viewpager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                viewpager.setCurrentItem(position, false)
            }
        })

        tablayout.setupWithViewPager(viewpager)
        tablayout.setTabTextColors(Color.GRAY, Color.BLACK)
//        tablayout.tabGravity = fill_horizontal
//        tablayout.tabMode = center

        api = NewsApi()
        present = PresentBaseFragment()
        RxBus.listen(String::class.java).subscribe({
            if (it == "tablayout|up") {
//                tablayout.visibility = View.GONE
            } else {
//                tablayout.visibility = View.VISIBLE
            }
        })
    }

    fun setupViewPager() {
        val tabTitle = resources.getStringArray(R.array.tabTitle)
        for (i in 0 until tabTitle.size) tablayout.getTabAt(i)!!.customView = prepareTabView(i)
    }

    @SuppressLint("InflateParams")
    fun prepareTabView(position: Int): View {
        val tabTitle = resources.getStringArray(R.array.tabTitle)
        val view = LayoutInflater.from(this).inflate(R.layout.custom_tab, null)
        tv_title.text = (tabTitle[position].toString())
        return view
    }

    fun trySetup() {
        try {
            setupViewPager()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadFragment() {
        if (supportFragmentManager.findFragmentById(R.id.slider_news) == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.slider_news, SliderFragment())
                    .commit()
        }
    }

    fun requestData() {
        Log.d("FLOW", "Main.ReqData")
        present.getHiburanNews(api, "id", "entertainment", Constant.HiburanFragmentBus)
        present.getTechNews(api, "id", "technology", Constant.TechnoFragmentBus)
        present.getSportNews(api, "id", "sport", Constant.SportFragmentBus)
    }

    fun appBarListener() {
        setSupportActionBar(toolbar)

        toolbar_layout.title = this.title
//        toolbar_layout.setb(Color.WHITE)
//        toolbar_layout.setBackgroundColor(Color.TRANSPARENT)

        id_appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                //  Collapse
                Log.d("flow", "collapse")
                toolbar.visibility = View.VISIBLE
                toolbar_layout.title = this.title
            } else {
                //Expanded
                Log.d("flow", "expanded")
                toolbar.visibility = View.INVISIBLE
                toolbar_layout.title = " "
            }
        })
    }

}
