package hr.ferit.lukajukic.simplecatalogue

data class MovieResponseData(
    val page : Int?,
    val next : String?,
    val entries : Int?,
    val results : ArrayList<Movie>
)

data class Movie(
    val id : String?,
    val primaryImage : Image?,
    val titleText : TitleText?,
    val releaseYear : YearRange?
)

data class Image(
    val url : String?
)

data class TitleText(
    val text : String?
)

data class YearRange(
    val year : Int?
)
