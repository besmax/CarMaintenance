package bes.max.carmaintenance.data.mappers

import bes.max.carmaintenance.data.dto.GoogleApiResponse
import bes.max.carmaintenance.domain.models.Check

class GoogleApiResponseMapper {

    fun toListOfCheck(response: GoogleApiResponse): List<Check> {
        val checks: MutableList<Check> = mutableListOf()
        if (response.values != null) {
            response.values.forEach { listOfString ->
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