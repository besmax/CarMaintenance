package bes.max.carmaintenance

import android.app.Application
import bes.max.carmaintenance.data.CheckDatabase

class BaseApplication : Application() {

    val checkDatabase: CheckDatabase by lazy {
        CheckDatabase.getInstance(this)
    }
}