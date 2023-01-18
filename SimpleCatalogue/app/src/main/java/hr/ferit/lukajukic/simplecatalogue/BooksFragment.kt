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


class BooksFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_books, container, false)

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
            val service = BookServiceBuilder.buildService(FakerEndpoints::class.java)
            val call = service.getBooks(searchQuery.text.toString())

            call.enqueue(object : Callback<BookResponseData> {
                override fun onResponse(
                    call: Call<BookResponseData>,
                    response: Response<BookResponseData>
                ) {
                    if (response.isSuccessful) {
                        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewBooks)
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = BookRecyclerAdapter(response.body()!!.results)
                        }
                        if(response.body()!!.results.isEmpty()){
                            Toast.makeText(context, "No results. Try something else.", Toast.LENGTH_LONG)
                                .show()
                        }

                    }
                }

                override fun onFailure(call: Call<BookResponseData>, t: Throwable) {
                    Log.e("BookFragment", t.message.toString())

                }

            })

            if(searchQuery.text.toString().length < 2){
                Toast.makeText(context, "Please enter at least 2 letters.", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return view
    }


}