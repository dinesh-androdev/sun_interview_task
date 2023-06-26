package com.sunnetwork.homescreen.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.sunnetwork.databinding.HomePageListItemBinding
import com.sunnetwork.homescreen.model.HomeScreenListModel
import com.sunnetwork.homescreen.model.HomeScreenResponseArrayItem

class HomePageParentAdapter(
    val activity: FragmentActivity,
    private val homePageList: List<HomeScreenResponseArrayItem?>,
    private val homeScreenChildListener:HomeScreenChildListener
) : RecyclerView.Adapter<HomePageParentAdapter.HomePageParentHolder>() {

    class HomePageParentHolder(val binding: HomePageListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageParentHolder {
        val homePageListItemBinding =
            HomePageListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomePageParentHolder(homePageListItemBinding)
    }

    override fun getItemCount(): Int {
        return homePageList.size
    }

    override fun onBindViewHolder(holder: HomePageParentHolder, position: Int) {
        val  item = homePageList[position]!!
        holder.binding.homePageParentTitle.text = item.title
        holder.binding.homePageParentRv.adapter = HomePageChildAdapter(activity,item.items!!,homeScreenChildListener)
    }
}