package com.azwar.githubuser.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azwar.githubuser.api.RetrofitClient
import com.azwar.githubuser.model.DetailUsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    val user = MutableLiveData<DetailUsersResponse>()

    fun setUserDetail(username: String){
        RetrofitClient.apiInstace
            .getDetailUsers(username)
            .enqueue(object : Callback<DetailUsersResponse>{
                override fun onResponse(
                    call: Call<DetailUsersResponse>,
                    response: Response<DetailUsersResponse>
                ) {
                    if (response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUsersResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getUserDetail(): LiveData<DetailUsersResponse>{
        return user
    }
}