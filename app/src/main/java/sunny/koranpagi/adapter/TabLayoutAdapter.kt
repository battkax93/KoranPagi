package sunny.koranpagi.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import sunny.koranpagi.feature.fragment.game_fragment.EntertainmentFragment
import sunny.koranpagi.feature.fragment.olahraga_fragment.SportFragment
import sunny.koranpagi.feature.fragment.teknologi_fragment.TechnologyFragment


class TabLayoutAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> EntertainmentFragment()
            1 -> TechnologyFragment()
            else -> SportFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "HIBURAN"
            1 -> "TEKNOLOGI"
            else -> "OLAHRAGA"
        }
    }
}




