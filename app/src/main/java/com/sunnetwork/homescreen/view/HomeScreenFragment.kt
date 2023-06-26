package com.sunnetwork.homescreen.view


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sunnetwork.databinding.FragmentHomeScreenBinding
import com.sunnetwork.exoPlayer.ExoPlayerActivity
import com.sunnetwork.homescreen.model.ItemsItem
import com.sunnetwork.homescreen.view.adapter.HomePageParentAdapter
import com.sunnetwork.homescreen.view.adapter.HomeScreenChildListener
import com.sunnetwork.homescreen.viewmodel.HomeScreenViewModel

class HomeScreenFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this@HomeScreenFragment).get(HomeScreenViewModel::class.java)
    }
    private lateinit var viewBinding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        initObserver()
        viewModel.start(requireContext())
        return viewBinding.root
    }

    private fun initObserver() {
        viewModel.homePageList.observe(requireActivity()){
            viewBinding.homePageRv.adapter = HomePageParentAdapter(requireActivity(),it.homeScreenResponseArray!!,object : HomeScreenChildListener {
                override fun onItemCLicked(item: ItemsItem) {
                    startActivity(Intent(requireContext(),ExoPlayerActivity::class.java)
                        .putExtra("url",item.videoUrl)
                        .putExtra("title",item.title)
                    )
                }
            })
        }
    }
}