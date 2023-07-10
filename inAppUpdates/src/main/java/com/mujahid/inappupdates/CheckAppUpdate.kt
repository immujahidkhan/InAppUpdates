package com.mujahid.inappupdates

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class AppInfo(
    val packageName: String, val versionName: String
)

class CheckAppUpdate(private val context: Context, private val packageName: String) {

    suspend fun isAppUpdated(): Boolean {
        val packageInfo = getPackageInfo(packageName)
        packageInfo?.let {
            val currentVersionCode = packageInfo.versionCode
            val latestVersionCode = getLatestVersionCodeFromAPI()
            return currentVersionCode < latestVersionCode
        }
        return false
    }

    private suspend fun getLatestVersionCodeFromAPI(): Long = withContext(Dispatchers.IO) {
        try {
            val retrofit =
                Retrofit.Builder().baseUrl("https://gist.githubusercontent.com/mujahid-dzinemedia/")
                    .addConverterFactory(GsonConverterFactory.create()).build()

            val appInfoService = retrofit.create(AppInfoService::class.java)
            val response = appInfoService.getAppInfo()

            return@withContext response.firstOrNull<AppInfo> { it.packageName == packageName }?.versionName?.toLongOrNull()
                ?: 0L
        } catch (e: Exception) {
            // Handle network request or parsing error
            Log.e("CheckAppUpdate", "Error fetching latest version code: ${e.message}")
            return@withContext 0L
        }
    }

    private fun getPackageInfo(packageName: String): PackageInfo? {
        return try {
            context.packageManager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("CheckAppUpdate", "Package name not found: $packageName")
            null
        }
    }
}

