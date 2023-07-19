package bes.max.carmaintenance.data.dto

data class GoogleApiResponse(
    val range: String,
    val majorDimension: String,
    val values: List<List<String>>
)
