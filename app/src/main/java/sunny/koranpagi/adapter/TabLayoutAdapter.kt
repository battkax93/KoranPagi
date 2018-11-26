package sunny.koranpagi.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import sunny.koranpagi.feature.fragment.game_fragment.GamingFragment
import sunny.koranpagi.feature.fragment.hiburan_fragment.EntertainmentFragment
import sunny.koranpagi.feature.fragment.musik_fragment.MusicFragment
import sunny.koranpagi.feature.fragment.olahraga_fragment.SportFragment
import sunny.koranpagi.feature.fragment.teknologi_fragment.TechnologyFragment


class TabLayoutAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> GamingFragment()
            1 -> EntertainmentFragment()
            2 -> MusicFragment()
            3 -> TechnologyFragment()
            else -> SportFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "GAME"
            1 -> "HIBURAN"
            2 -> "MUSIC"
            3 -> "TEKNOLOGI"
            else -> "OLAHRAGA"
        }
    }
}




