package hr.ferit.lukajukic.simplecatalogue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView



enum class ItemClickType{
    EDIT,
    REMOVE
}

class IdeaRecyclerAdapter(
    val items: ArrayList<Idea>,
    val listener: ContentListener
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return IdeaViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_addnew,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is IdeaViewHolder -> {
                holder.bind(position, items[position], listener)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeItem(index: Int) {
        items.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, items.size)
    }


    fun addItem(idea: Idea){
        items.add(idea)
        notifyItemInserted(items.size)
        notifyItemRangeChanged(items.size,items.size)
    }

    class IdeaViewHolder(val view: View): RecyclerView.ViewHolder(view){
        private val categoryName = view.findViewById<EditText>(R.id.categoryNameText)
        private val categoryApi = view.findViewById<EditText>(R.id.categoryApiNameText)
        private val categorySearchBy = view.findViewById<EditText>(R.id.categorySearchByText)
        private val btnEdit = view.findViewById<ImageButton>(R.id.editBtn)
        private val btnDelete = view.findViewById<ImageButton>(R.id.deleteBtn)


        fun bind(index:Int, idea: Idea, listener:ContentListener){
            categoryName.setText(idea.categoryName)
            categoryApi.setText(idea.categoryAPI)
            categorySearchBy.setText(idea.categorySearchBy)

            btnEdit.setOnClickListener {
                if(categoryName.text.toString() != ""){
                    idea.categoryName = categoryName.text.toString()
                }
                else{
                    idea.categoryName = "Unknown"
                    categoryName.setText("Unknown")
                }
                if(categoryApi.text.toString() != ""){
                    idea.categoryAPI = categoryApi.text.toString()
                }
                else{
                    idea.categoryAPI = "Unknown"
                    categoryApi.setText("Unknown")
                }
                if(categorySearchBy.text.toString() != ""){
                    idea.categorySearchBy = categorySearchBy.text.toString()
                }
                else{
                    idea.categorySearchBy = "Unknown"
                    categorySearchBy.setText("Unknown")
                }
                listener.onItemButtonClick(index,idea,ItemClickType.EDIT)
            }

            btnDelete.setOnClickListener {
                listener.onItemButtonClick(index,idea,ItemClickType.REMOVE)
            }
        }
    }

    interface ContentListener{
        fun onItemButtonClick(index: Int, idea: Idea, clickType: ItemClickType)
    }
}