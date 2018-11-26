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
import sunny.koranpagi.R.id.*
import sunny.koranpagi.adapter.TabLayoutAdapter

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        trySetup()
    }

    fun init() {
        val fragAdapter = TabLayoutAdapter(supportFragmentManager)
        viewpager.offscreenPageLimit = 8
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
        tablayout.tabGravity = scrollable
        tablayout.tabMode = fixed

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
}
