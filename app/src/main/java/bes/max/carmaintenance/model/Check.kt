package bes.max.carmaintenance.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Check(
    var checkId: Long = 0L,
    var checkName: String = "",
    var checkMileage: Long = 0L,
    var checkDate: LocalDate = LocalDate.parse("31-12-2018",
        DateTimeFormatter.ofPattern("dd-MM-yyyy")),
    var checkPrice: Long = 0L,
    var checkCompany: String = ""
)