package com.sunnetwork.homescreen.model

import com.google.gson.annotations.SerializedName

data class HomeScreenListModel(

    @field:SerializedName("home_screen_response_array")
    val homeScreenResponseArray: List<HomeScreenResponseArrayItem?>? = null
)

data class ItemsItem(

    @field:SerializedName("video_url")
    val videoUrl: String? = null,

    @field:SerializedName("icon")
    val icon: String? = null,

    @field:SerializedName("title")
    val title: String? = null
)

data class HomeScreenResponseArrayItem(

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("items")
    val items: List<ItemsItem?>? = null
)
