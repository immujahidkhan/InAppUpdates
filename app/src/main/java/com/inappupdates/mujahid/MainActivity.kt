package com.inappupdates.mujahid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.mujahid.inappupdates.CheckAppUpdate
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Launch the coroutine in a CoroutineScope
        lifecycleScope.launch {
            val checkAppUpdate = CheckAppUpdate(this@MainActivity, this@MainActivity.packageName)
            val isUpdated = checkAppUpdate.isAppUpdated()

            if (isUpdated) {
                // App is not up to date
                // Implement your logic here (e.g., show a dialog or perform an action)
                Log.e(TAG, "App is not up to date")
            } else {
                // App is up to date
                // Implement your logic here
                Log.e(TAG, "App is up to date")
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}