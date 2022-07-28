package com.azwar.githubuser.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.azwar.githubuser.R
import com.azwar.githubuser.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail User"

        val username = intent.getStringExtra(EXTRA_USER).toString()
        val bundle = Bundle()
        bundle.putString(EXTRA_USER, username)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        showLoading(true)

        viewModel.setUserDetail(username)
        viewModel.getUserDetail().observe(this){
            if (it != null){
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = '@'+it.username
                    tvFollowers.text = it.followers
                    tvFollowing.text = it.following
                    tvRepository.text = it.repository
                    tvLocation.text = it.location
                    tvCompany.text = it.company
                    Glide.with(this@DetailActivity)
                        .load(it.photo)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfile)
                }
                showLoading(false)
            }
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionsPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }

    companion object {
        const val EXTRA_USER = "extra_user"
        const val ID_USER = "id"
        const val USERNAME_USER = "username"
        const val AVATAR_USER = "photo"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }
}