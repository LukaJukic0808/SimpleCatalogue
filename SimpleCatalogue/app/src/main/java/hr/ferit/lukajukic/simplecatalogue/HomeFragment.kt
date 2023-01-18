package hr.ferit.lukajukic.simplecatalogue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val carsView = view.findViewById<View>(R.id.carsView)
        val booksView = view.findViewById<View>(R.id.booksView)
        val moviesView = view.findViewById<View>(R.id.moviesView)
        val addNewView = view.findViewById<View>(R.id.addNewView)

        val carsFragment = CarsFragment()
        val booksFragment = BooksFragment()
        val moviesFragment = MoviesFragment()
        val addNewFragment = AddNewFragment()

        carsView.setOnClickListener {
            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, carsFragment)
            fragmentTransaction?.commit()
        }

        booksView.setOnClickListener {
            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, booksFragment)
            fragmentTransaction?.commit()
        }

        moviesView.setOnClickListener {
            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, moviesFragment)
            fragmentTransaction?.commit()
        }

        addNewView.setOnClickListener {
            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, addNewFragment)
            fragmentTransaction?.commit()
        }

        return view
    }



}