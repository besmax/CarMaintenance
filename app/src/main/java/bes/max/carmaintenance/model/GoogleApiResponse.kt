package bes.max.carmaintenance.model

data class GoogleApiResponse(
    val range: String,
    val majorDimension: String,
    val values: List<List<String>>
) {

    fun convertDataToCheckFormat(): List<Check> {
        val checks: MutableList<Check> = mutableListOf()
        if (values != null) {
            values.forEach { listOfString ->
                checks.add(
                    Check(
                        checkId = listOfString[0],
                        checkName = listOfString[1],
                        checkDate = listOfString[2],
                        checkCompany = listOfString[3],
                        checkMileage = listOfString[4],
                        checkPrice = listOfString[5]
                    )
                )
            }
        }
        return checks
    }
}
