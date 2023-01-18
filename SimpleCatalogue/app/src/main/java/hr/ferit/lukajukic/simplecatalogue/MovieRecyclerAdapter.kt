package hr.ferit.lukajukic.simplecatalogue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.collections.ArrayList


class MovieRecyclerAdapter(val items: ArrayList<Movie>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MovieViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return this.items.size
    }


    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val movieTitle = itemView.findViewById<TextView>(R.id.movieTitle)
        private val movieImdb = itemView.findViewById<TextView>(R.id.movieImdbId)
        private val movieYear = itemView.findViewById<TextView>(R.id.movieYear)
        private val movieImage = itemView.findViewById<ImageView>(R.id.movieImageLink)


        fun bind(movie : Movie) {
            movieTitle.text = movie.titleText?.text
            movieImdb.text = "IMDb ID: ${movie.id}"
            movieYear.text = "Release year: ${movie.releaseYear?.year.toString()}"
            if(movie.primaryImage != null){
                Glide.with(itemView.context).load(movie.primaryImage?.url).error(R.drawable.notavailableimage).into(movieImage)
            }
        }
    }
}