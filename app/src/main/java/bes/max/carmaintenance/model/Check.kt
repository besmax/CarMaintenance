package bes.max.carmaintenance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checks_table")
data class Check(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "check_id")
    var checkId: String ="",

    @ColumnInfo(name = "check_name")
    var checkName: String = "",

    @ColumnInfo(name = "check_mileage")
    var checkMileage: String? = "",

    @ColumnInfo(name = "check_date")
    var checkDate: String,

    @ColumnInfo(name = "check_price")
    var checkPrice: String = "",

    @ColumnInfo(name = "check_company")
    var checkCompany: String = ""

)