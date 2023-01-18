package hr.ferit.lukajukic.simplecatalogue

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class CarRecyclerAdapter(val items: ArrayList<Car>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CarViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_car, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CarViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return this.items.size
    }


    class CarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val carName = itemView.findViewById<TextView>(R.id.carNameText)
        private val carClass = itemView.findViewById<TextView>(R.id.carClassText)
        private val carFuel = itemView.findViewById<TextView>(R.id.carFuelText)
        private val carYear = itemView.findViewById<TextView>(R.id.carYearText)


        fun bind(car : Car) {
            carName.text = "${car.make?.capitalize(Locale.ROOT)} ${car.model?.capitalize(Locale.ROOT)}"
            carClass.text = car.carClass?.capitalize(Locale.ROOT)
            carFuel.text = car.fuel_type?.capitalize(Locale.ROOT)
            carYear.text = car.year.toString()
        }
    }
}