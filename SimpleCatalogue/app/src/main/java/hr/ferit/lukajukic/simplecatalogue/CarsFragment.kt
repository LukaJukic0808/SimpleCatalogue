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


class CarsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cars, container, false)

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
            val service = CarServiceBuilder.buildService(FakerEndpoints::class.java)
            val call = service.getCars(searchQuery.text.toString(),"20")

            call.enqueue(object : Callback<ArrayList<Car>> {
                override fun onResponse(
                    call: Call<ArrayList<Car>>,
                    response: Response<ArrayList<Car>>
                ) {
                    if (response.isSuccessful) {
                        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCars)
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = CarRecyclerAdapter(response.body()!!)
                        }
                        if(response.body()!!.isEmpty()){
                            Toast.makeText(context, "No results. Enter valid manufacturer.", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<Car>>, t: Throwable) {
                    Log.e("CarFragment", t.message.toString())

                }
            })

            if(searchQuery.text.toString() == ""){
                Toast.makeText(context, "Search field can't be left empty.", Toast.LENGTH_LONG)
                    .show()
            }
        }
        return view
    }


}