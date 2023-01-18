package hr.ferit.lukajukic.simplecatalogue

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoviesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movies, container, false)

        val backBtn = view.findViewById<ImageButton>(R.id.backBtn)
        val homeFragment = HomeFragment()
        backBtn.setOnClickListener {
            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, homeFragment)
            fragmentTransaction?.commit()
        }

        val searchQuery = view.findViewById<EditText>(R.id.queryText)
        val searchButton = view.findViewById<Button>(R.id.searchBtn)

        searchButton.setOnClickListener {
            val service = MovieServiceBuilder.buildService(FakerEndpoints::class.java)

            if(searchQuery.text.toString() == ""){
                Toast.makeText(context, "Search field can't be left empty.", Toast.LENGTH_LONG)
                    .show()
            }
            else {
                var movies = ArrayList<Movie>()
                var flag = false
                for (i in 1..3) {
                    val call = service.getMovies(searchQuery.text.toString(), 2010, i)
                    call.enqueue(object : Callback<MovieResponseData> {
                        override fun onResponse(
                            call: Call<MovieResponseData>,
                            response: Response<MovieResponseData>
                        ) {
                            if (response.isSuccessful) {
                                movies.addAll(response.body()!!.results)
                                if (response.body()!!.results.isEmpty()) {
                                    if(!flag) {             //show toast just once
                                        Toast.makeText(
                                            context,
                                            "No results. Capitalize first letter or enter valid genre.",
                                            Toast.LENGTH_LONG
                                        )
                                            .show()
                                    }
                                    flag = true
                                }
                            }
                        }

                        override fun onFailure(call: Call<MovieResponseData>, t: Throwable) {
                            Log.e("MovieFragment", t.message.toString())

                        }
                    })
                }
                val recyclerView =
                    view.findViewById<RecyclerView>(R.id.recyclerViewMovies)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = MovieRecyclerAdapter(movies)
                }
            }
        }
        return view
    }


}