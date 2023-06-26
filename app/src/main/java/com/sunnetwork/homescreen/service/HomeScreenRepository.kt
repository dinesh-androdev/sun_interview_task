package com.sunnetwork.homescreen.service

import android.content.Context
import com.google.gson.Gson
import com.sunnetwork.R
import com.sunnetwork.TaskCallback
import com.sunnetwork.homescreen.model.HomeScreenListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream


class HomeScreenRepository {
    private val completedJob = Job()
    private val backgroundScope = CoroutineScope(Dispatchers.IO + completedJob)
    private val foregroundScope = CoroutineScope(Dispatchers.Main)

    fun getHomeScreenList(context: Context,taskCallback: TaskCallback<HomeScreenListModel>) {

        backgroundScope.launch {
            val homePageListItems = getHomePageListItems(context)
            foregroundScope.launch { taskCallback.onComplete(homePageListItems) }
        }
    }

    private fun getHomePageListItems(context: Context): HomeScreenListModel? {
        var jsonString =""
        try {
            val jsonResource: InputStream = context.resources.openRawResource(R.raw.home_screen_response)
            val jsonSize: Int = jsonResource.available()
            val buffer = ByteArray(jsonSize)
            jsonResource.read(buffer)
            jsonResource.close()
            jsonString = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return Gson().fromJson(jsonString,HomeScreenListModel::class.java)
    }
}
