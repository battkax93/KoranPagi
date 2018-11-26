package sunny.koranpagi.feature.fragment.game_fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import sunny.koranpagi.R
import sunny.koranpagi.feature.fragment.base.ContractBaseFragment

class GamingFragment : Fragment(), ContractBaseFragment.mainView {

    lateinit var pbar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val game = inflater.inflate(R.layout.fragment_gaming, container, false)
        init(game)
        return game
    }

    override fun init(v: View) {
        pbar = v.findViewById(R.id.mainProgressbar)
    }

    override fun action() {
    }

    override fun updateUI() {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }
}
