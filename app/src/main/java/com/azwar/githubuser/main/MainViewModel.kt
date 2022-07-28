package com.azwar.githubuser.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.azwar.githubuser.api.RetrofitClient
import com.azwar.githubuser.model.Users
import com.azwar.githubuser.model.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val listUser = MutableLiveData<ArrayList<Users>>()

    fun setSearchUser(query: String){
        RetrofitClient.apiInstace
            .getSearchUsers(query)
            .enqueue(object : Callback<UsersResponse>{
                override fun onResponse(
                    call: Call<UsersResponse>,
                    response: Response<UsersResponse>
                ) {
                    if (response.isSuccessful){
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getSearchUser(): LiveData<ArrayList<Users>>{
        return listUser
    }
}