package onoffrice.mytopgames

import androidx.multidex.MultiDexApplication
import onoffrice.mytopgames.data.local.PreferencesHelper

class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        setPreferencesHelper()
    }

    private fun setPreferencesHelper() {
        PreferencesHelper.init(applicationContext)
    }
}
