package hr.ferit.lukajukic.simplecatalogue

data class BookResponseData(
    val total_results : Int?,
    val total_pages : Int?,
    val results : ArrayList<Book>
)

data class Book(
    val title : String?,
    val copyright : Int?,
    val categories : ArrayList<String>?,
    val authors : ArrayList<String>?
)
