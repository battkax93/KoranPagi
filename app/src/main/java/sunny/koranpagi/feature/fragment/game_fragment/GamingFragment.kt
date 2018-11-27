package sunny.koranpagi.feature.fragment.game_fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast

import sunny.koranpagi.R
import sunny.koranpagi.entity.NewsGames
import sunny.koranpagi.feature.fragment.base.ContractBaseFragment
import sunny.koranpagi.feature.fragment.base.PresentBaseFragment
import sunny.koranpagi.rest.NewsApi
import sunny.koranpagi.utils.Constant
import sunny.koranpagi.utils.RxBus


class GamingFragment : Fragment(), ContractBaseFragment.mainGameView {

    lateinit var pbar: ProgressBar
    lateinit var api: NewsApi
    lateinit var newsGames: NewsGames

    lateinit var present: ContractBaseFragment.mainPresent

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenEvent()
        action()
    }

    override fun init(v: View) {
        api = NewsApi()
        pbar = v.findViewById(R.id.mainProgressBar)
        present = PresentBaseFragment()
    }

    override fun action() {
        present.getNewsGames(api, requireContext(), "id", "gaming", Constant.GameFragmentBus)
    }

    override fun updateUI(it: NewsGames) {
        if (it.status == "ok") {
            Toast.makeText(requireContext(), "succes", Toast.LENGTH_SHORT).show()
            Log.d("FLOW", it.status)
        } else {
            Toast.makeText(requireContext(), "fail", Toast.LENGTH_SHORT).show()

        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun listenEvent() {
        RxBus.listen(String::class.java).subscribe({
            if (it == Constant.GameFragmentBus) {
                RxBus.listen(NewsGames::class.java).subscribe({
                    updateUI(it)
                })
            }
        })
    }

}
