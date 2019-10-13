package onoffrice.mytopgames.ui.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import onoffrice.mytopgames.R
import android.net.ConnectivityManager

abstract class BaseActivity : AppCompatActivity() {

    //TOOLBAR METHODS
    fun setToolbar(title: String, displayHomeAsUpEnabled: Boolean) {
        setToolbar(title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)

    }

    //ACTION BAR METHODS
    private fun setToolbar(title: String) {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        setTitle(title)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null
    }

}