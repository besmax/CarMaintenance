package bes.max.carmaintenance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "checks_table")
data class Check(

    @PrimaryKey(autoGenerate = true)
    var checkId: Int = 0,

    @ColumnInfo(name = "check_name")
    var checkName: String = "",

    @ColumnInfo(name = "check_mileage")
    var checkMileage: Int = 0,

    @ColumnInfo(name = "check_date")
    var checkDate: LocalDate = LocalDate.parse("31-12-2018",
        DateTimeFormatter.ofPattern("dd-MM-yyyy")),

    @ColumnInfo(name = "check_price")
    var checkPrice: Int = 0,

    @ColumnInfo(name = "check_company")
    var checkCompany: String = ""

)