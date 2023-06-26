package com.sunnetwork.homescreen.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunnetwork.BaseViewModel
import com.sunnetwork.TaskCallback
import com.sunnetwork.homescreen.model.HomeScreenListModel
import com.sunnetwork.homescreen.service.HomeScreenRepository

class HomeScreenViewModel : BaseViewModel() {

    var homePageListMutable = MutableLiveData<HomeScreenListModel>()

    val homePageList: LiveData<HomeScreenListModel>
        get() = homePageListMutable

    private val homeScreenRepository: HomeScreenRepository by lazy {
        HomeScreenRepository()
    }
    override fun start(context: Context) {
        homeScreenRepository.getHomeScreenList(context,object : TaskCallback<HomeScreenListModel> {
            override fun onComplete(result: HomeScreenListModel?) {
                homePageListMutable.postValue(result)
            }

            override fun onException(t: Throwable?) {

            }
        })
    }

}
