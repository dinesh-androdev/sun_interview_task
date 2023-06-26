package com.sunnetwork.homescreen.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sunnetwork.databinding.HomePageChildListItemBinding
import com.sunnetwork.databinding.HomePageListItemBinding
import com.sunnetwork.homescreen.model.ItemsItem

class HomePageChildAdapter(
    val activity: FragmentActivity,
    private val homeChildPageList: List<ItemsItem?>,
    private val homeScreenChildListener:HomeScreenChildListener
) : RecyclerView.Adapter<HomePageChildAdapter.HomePageParentHolder>() {

    class HomePageParentHolder(val binding: HomePageChildListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageParentHolder {
        val homePageChildListItemBinding =
            HomePageChildListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomePageParentHolder(homePageChildListItemBinding)
    }

    override fun getItemCount(): Int {
        return homeChildPageList.size
    }

    override fun onBindViewHolder(holder: HomePageParentHolder, position: Int) {
        Glide.with(activity).load(homeChildPageList[position]!!.icon).into(holder.binding.homePageChildSIv)
        holder.itemView.setOnClickListener {
            homeScreenChildListener.onItemCLicked(homeChildPageList[position]!!)
        }
    }
}
interface HomeScreenChildListener{
    fun onItemCLicked(item:ItemsItem)
}