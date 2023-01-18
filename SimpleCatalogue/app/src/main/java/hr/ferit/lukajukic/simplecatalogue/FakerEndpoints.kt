package hr.ferit.lukajukic.simplecatalogue

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FakerEndpoints {
    @Headers(
        "X-RapidAPI-Key: 26ae2e7ae3mshdd158937af15816p13c858jsn366c5c6e98e2",
        "X-RapidAPI-Host: moviesdatabase.p.rapidapi.com"
    )
    @GET("/titles")
    fun getMovies(@Query("genre") genre: String, @Query("startYear") startYear: Int, @Query("page") page: Int) : Call<MovieResponseData>


    @Headers(
        "X-RapidAPI-Key: 26ae2e7ae3mshdd158937af15816p13c858jsn366c5c6e98e2",
        "X-RapidAPI-Host: cars-by-api-ninjas.p.rapidapi.com"
    )
    @GET("/v1/cars")
    fun getCars(@Query("make") make: String, @Query("limit") limit: String) : Call<ArrayList<Car>>


    @Headers(
        "X-RapidAPI-Key: 26ae2e7ae3mshdd158937af15816p13c858jsn366c5c6e98e2",
        "X-RapidAPI-Host: book-finder1.p.rapidapi.com"
    )
    @GET("/api/search")
    fun getBooks(@Query("title") title: String) : Call<BookResponseData>

}
