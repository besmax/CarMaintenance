package bes.max.carmaintenance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "planned_checks_table")
data class PlannedCheck(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "planned_check_id")
    var checkId: Int,

    @ColumnInfo(name = "planned_check_name")
    var checkName: String = "",

    @ColumnInfo(name = "planned_check_date")
    var checkDate: String,
) {
    val date get() = LocalDate.parse(checkDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
}