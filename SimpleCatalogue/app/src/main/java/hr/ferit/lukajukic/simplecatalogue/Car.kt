package hr.ferit.lukajukic.simplecatalogue

import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("class")
    val carClass : String?,
    val make : String?,
    val model : String?,
    val year : Int?,
    val fuel_type : String?
)
