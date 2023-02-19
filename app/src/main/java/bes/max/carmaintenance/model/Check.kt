package bes.max.carmaintenance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "checks_table")
data class Check(

    @PrimaryKey(autoGenerate = true)
    var checkId: Long = 0L,

    @ColumnInfo(name = "check_name")
    var checkName: String = "",

    @ColumnInfo(name = "check_mileage")
    var checkMileage: Long = 0L,

    @ColumnInfo(name = "check_date")
    var checkDate: LocalDate = LocalDate.parse("31-12-2018",
        DateTimeFormatter.ofPattern("dd-MM-yyyy")),

    @ColumnInfo(name = "check_price")
    var checkPrice: Long = 0L,

    @ColumnInfo(name = "check_company")
    var checkCompany: String = ""

)