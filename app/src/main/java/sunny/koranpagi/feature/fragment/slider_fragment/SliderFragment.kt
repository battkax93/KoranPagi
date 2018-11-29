package sunny.koranpagi.feature.fragment.slider_fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CompoundButton
import com.tmall.ultraviewpager.UltraViewPager
import com.tmall.ultraviewpager.transformer.UltraScaleTransformer
import kotlinx.android.synthetic.main.layout_child.*
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.parseSingle
import org.jetbrains.anko.db.select

import sunny.koranpagi.R
import sunny.koranpagi.adapter.SliderViewPagerAdapter
import sunny.koranpagi.feature.fragment.base.ContractBaseFragment
import sunny.koranpagi.utils.DBHelper

class SliderFragment : Fragment(), ContractBaseFragment.mainSliderView, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    var pagerAdapter: SliderViewPagerAdapter? = null
    private var gravity_indicator: UltraViewPager.Orientation? = null

    lateinit var ultraViewPager: UltraViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val slide = inflater.inflate(R.layout.fragment_slider, container, false)
        init(slide)
        return slide
    }

    override fun init(v: View) {

        ultraViewPager = v.findViewById(R.id.ultra_viewpager)

        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL)
        pagerAdapter = SliderViewPagerAdapter(requireContext())
        ultraViewPager.adapter = pagerAdapter
        ultraViewPager.setMultiScreen(1.0f)
        ultraViewPager.setItemRatio(0.5)
        ultraViewPager.setRatio(1.5f)
        ultraViewPager.setMaxHeight(800)
//        ultraViewPager.viewPager.pageMargin = -150
        ultraViewPager.setAutoMeasureHeight(true)
//        ultraViewPager.setPageTransformer(false, UltraScaleTransformer())
        ultraViewPager.elevation = 5f
        ultraViewPager.setBackgroundColor(resources.getColor(R.color.white_milk))
        gravity_indicator = UltraViewPager.Orientation.HORIZONTAL

        //initIndicator
        ultraViewPager.initIndicator()
        ultraViewPager.indicator
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.GREEN)
                .setNormalColor(Color.WHITE)
                .setRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, resources.displayMetrics).toInt())
        ultraViewPager.indicator.setGravity(Gravity.BOTTOM or Gravity.CENTER)
        ultraViewPager.indicator.setMargin(0, 0, 0, 100)
        ultraViewPager.indicator.build()
        ultraViewPager.setInfiniteLoop(true)
        ultraViewPager.setAutoScroll(3500)
    }

    override fun action() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
    }

}

