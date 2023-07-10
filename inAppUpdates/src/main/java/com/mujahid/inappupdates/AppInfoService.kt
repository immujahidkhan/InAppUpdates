package com.mujahid.inappupdates

import retrofit2.http.GET

interface AppInfoService {
    @GET("ff87f896f97fe6920481645308017eee/raw/inappupdates.json")
    suspend fun getAppInfo(): ArrayList<AppInfo>
}
