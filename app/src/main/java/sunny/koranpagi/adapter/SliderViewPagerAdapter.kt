package sunny.koranpagi.adapter

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.squareup.picasso.Picasso
import sunny.koranpagi.R

class SliderViewPagerAdapter : PagerAdapter() {

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
        val frameLayout: FrameLayout = LayoutInflater.from(container!!.context).inflate(R.layout.layout_child, null) as FrameLayout
        FrameLayout(container.context)
        var imgView: ImageView = frameLayout.findViewById(R.id.iv_slider_news)
        when (position) {
            0 -> Picasso.get().load("http://tvfiles.alphacoders.com/100/hdclearart-10.png")
                    .into(imgView)
            1 -> Picasso.get().load("http://cdn3.nflximg.net/images/3093/2043093.jpg")
                    .into(imgView)
            2 -> Picasso.get().load("http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg")
                    .into(imgView)
            3 -> Picasso.get().load("http://cdn3.nflximg.net/images/3093/2043093.jpg")
                    .into(imgView)
            4 -> Picasso.get().load("http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg")
                    .into(imgView)
        }
        container.addView(frameLayout)
        return frameLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as FrameLayout
        container!!.removeView(view)    }

}