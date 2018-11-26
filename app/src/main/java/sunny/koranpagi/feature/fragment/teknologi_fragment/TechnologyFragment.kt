package sunny.koranpagi.feature.fragment.teknologi_fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import sunny.koranpagi.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TechnologyFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TechnologyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TechnologyFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_technology, container, false)
    }

   }// Required empty public constructor