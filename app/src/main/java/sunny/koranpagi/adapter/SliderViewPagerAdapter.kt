package sunny.koranpagi.adapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.squareup.picasso.Picasso
import sunny.koranpagi.R
import sunny.koranpagi.utils.Constant

class SliderViewPagerAdapter(val ctx: Context, val act: Activity) : PagerAdapter() {

    private var isMultiScr: Boolean? = null

    fun ViewPagerAdapter(isMultiScr: Boolean) {
        this.isMultiScr = isMultiScr
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return 5
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val frameLayout: FrameLayout = LayoutInflater.from(container.context).inflate(R.layout.layout_child, null) as FrameLayout
        try {
            FrameLayout(container.context)
            val imgView: ImageView = frameLayout.findViewById(R.id.iv_slider_news)
            imgView.setOnClickListener { goToChromeTabs("http://google.com") }
            when (position) {
                0 -> Picasso.get().load(loadUrl(Constant.EntNewsKey, ctx)).into(imgView)

                1 -> Picasso.get().load(loadUrl(Constant.techNewsKey, ctx))
                        .into(imgView)
                2 -> Picasso.get().load(loadUrl(Constant.sportNewsKey, ctx))
                        .into(imgView)
                3 -> Picasso.get().load("http://cdn3.nflximg.net/images/3093/2043093.jpg")
                        .into(imgView)
                4 -> Picasso.get().load("http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg")
                        .into(imgView)
            }
            container.addView(frameLayout)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return frameLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as FrameLayout
        container.removeView(view)
    }


    fun loadUrl(s: String, ctx: Context): String {
        val prefs = ctx.getSharedPreferences(ctx.packageName, 0)
        return prefs.getString(s, "")
    }

    private fun goToChromeTabs(url: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        CustomTabActivityHelper.openCustomTab(act, // activity
                customTabsIntent,
                Uri.parse(url),
                WebviewFallback()
        )
    }
}