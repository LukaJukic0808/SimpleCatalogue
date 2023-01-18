package hr.ferit.lukajukic.simplecatalogue

data class Idea(
    var id : String = "",
    var categoryName : String? = null,
    var categoryAPI : String? = null,
    var categorySearchBy : String? = null
)
