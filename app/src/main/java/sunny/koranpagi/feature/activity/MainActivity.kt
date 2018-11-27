package sunny.koranpagi.feature.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_tab.*
import sunny.koranpagi.R
import sunny.koranpagi.adapter.TabLayoutAdapter
import sunny.koranpagi.feature.fragment.musik_fragment.SliderFragment
import sunny.koranpagi.utils.RxBus

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        trySetup()
        loadFragment()
    }

    fun init() {
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
}
