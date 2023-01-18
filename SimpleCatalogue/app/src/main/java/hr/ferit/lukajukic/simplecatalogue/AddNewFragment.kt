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
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AddNewFragment : Fragment(), IdeaRecyclerAdapter.ContentListener {
    private val db = Firebase.firestore
    private lateinit var recyclerAdapter: IdeaRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_new, container, false)

        val backBtn = view.findViewById<ImageButton>(R.id.backBtn)
        val homeFragment = HomeFragment()
        backBtn.setOnClickListener {
            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, homeFragment)
            fragmentTransaction?.commit()
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewIdeas)
        db.collection("ideas")
            .get()
            .addOnSuccessListener {
                val list: ArrayList<Idea> = ArrayList()
                for(data in it.documents) {
                    val idea = data.toObject(Idea::class.java)
                    if (idea != null) {
                        idea.id = data.id
                        list.add(idea)
                    }
                }
                recyclerAdapter = IdeaRecyclerAdapter(list, this)
                recyclerView.apply{
                    layoutManager= LinearLayoutManager(context)
                    adapter = recyclerAdapter
                }
            }
            .addOnFailureListener{
                Log.e("AddNewFragment", it.message.toString())
            }

        val saveButton = view.findViewById<Button>(R.id.saveBtn)
        val categoryNameEditText = view.findViewById<EditText>(R.id.editCategoryName)
        val categoryApiEditText = view.findViewById<EditText>(R.id.editCategoryAPI)
        val categorySearchByEditText = view.findViewById<EditText>(R.id.editCategorySearchBy)

        saveButton.setOnClickListener {
            if(categoryNameEditText.text.toString() == "")
                categoryNameEditText.setText("Unknown")

            if(categoryApiEditText.text.toString() == "")
                categoryApiEditText.setText("Unknown")

            if(categorySearchByEditText.text.toString() == "")
                categorySearchByEditText.setText("Unknown")

            val idea : Idea = Idea("", categoryNameEditText.text.toString(), categoryApiEditText.text.toString(), categorySearchByEditText.text.toString())
            db.collection("ideas").add(idea).addOnSuccessListener {
                idea.id = it.id
            }

            recyclerAdapter.addItem(idea)
        }

        return view
    }

    override fun onItemButtonClick(index: Int, idea: Idea, clickType: ItemClickType) {
        if(clickType == ItemClickType.EDIT){
            db.collection("ideas").document(idea.id).set(idea)  //ovdje se id atribut upise i u polje u bazi ("")
        }
        else if(clickType == ItemClickType.REMOVE){
            recyclerAdapter.removeItem(index)
            db.collection("ideas").document(idea.id).delete()
        }
    }

}