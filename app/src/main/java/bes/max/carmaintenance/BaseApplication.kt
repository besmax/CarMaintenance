package bes.max.carmaintenance

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import bes.max.carmaintenance.data.CheckDatabase

class BaseApplication : Application() {

    val checkDatabase: CheckDatabase by lazy {
        CheckDatabase.getInstance(this)
    }

    override fun onCreate() {
        super.onCreate()
        val darkThemePreference =
            getSharedPreferences(getString(R.string.settings_preferences), Context.MODE_PRIVATE)
        val darkTheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            darkThemePreference.getBoolean(
                getString(R.string.dark_theme_preference_key), resources.configuration.isNightModeActive
            )
        } else {
            darkThemePreference.getBoolean(
                getString(R.string.dark_theme_preference_key),
                (resources.configuration.uiMode == Configuration.UI_MODE_NIGHT_YES)
            )
        }
        switchTheme(darkTheme)
    }

    fun switchTheme(darkThemeEnabled: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (darkThemeEnabled) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO

        )

    }
}