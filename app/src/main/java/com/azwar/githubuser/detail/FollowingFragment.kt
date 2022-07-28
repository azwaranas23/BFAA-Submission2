package com.azwar.githubuser.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.azwar.githubuser.R
import com.azwar.githubuser.databinding.FragmentFollowBinding
import com.azwar.githubuser.main.ListUserAdapter

class FollowingFragment: Fragment(R.layout.fragment_follow) {
    private var binding_ : FragmentFollowBinding? = null
    private val binding get() = binding_!!

    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: ListUserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val argument = arguments
        username = argument?.getString(DetailActivity.EXTRA_USER).toString()

        binding_ = FragmentFollowBinding.bind(view)

        adapter = ListUserAdapter()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }

        showLoading(true)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner, {
            if (it != null){
                adapter.setList(it)
                showLoading(false)
            }
        })

    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }
}