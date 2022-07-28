package com.azwar.githubuser.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azwar.githubuser.api.RetrofitClient
import com.azwar.githubuser.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel: ViewModel(){
    val listFollowers = MutableLiveData<ArrayList<Users>>()

    fun setListFollowing(username: String){
        RetrofitClient.apiInstace
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<Users>>{
                override fun onResponse(
                    call: Call<ArrayList<Users>>,
                    response: Response<ArrayList<Users>>
                ) {
                    if (response.isSuccessful){
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<Users>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getListFollowing(): LiveData<ArrayList<Users>>{
        return listFollowers
    }
}