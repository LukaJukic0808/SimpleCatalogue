package hr.ferit.lukajukic.simplecatalogue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList


class BookRecyclerAdapter(val items: ArrayList<Book>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_book, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is BookViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return this.items.size
    }


    class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val bookTitle = itemView.findViewById<TextView>(R.id.bookTitleText)
        private val bookCopyright = itemView.findViewById<TextView>(R.id.bookCopyrightText)
        private val bookCategory = itemView.findViewById<TextView>(R.id.bookCategoryText)
        private val bookAuthor = itemView.findViewById<TextView>(R.id.bookAuthorText)


        fun bind(book : Book) {
            bookTitle.text = book.title
            if(book.copyright == null){
                bookCopyright.text = "Unknown"
            }
            else {
                bookCopyright.text = book.copyright.toString()
            }
            if(book.categories!!.isEmpty() || book.categories[0]==""){
                bookCategory.text = "Unknown"
            }
            else{
                bookCategory.text = book.categories[0]
            }
            if(book.authors!!.isEmpty() || book.authors[0]==""){
                bookAuthor.text = "Unknown"
            }
            else{
                bookAuthor.text = book.authors[0]
            }
        }
    }
}