package com.azwar.githubuser.api

import com.azwar.githubuser.model.DetailUsersResponse
import com.azwar.githubuser.model.UsersResponse
import com.azwar.githubuser.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q")
        query: String
    ): Call<UsersResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUsers(
        @Path("username") username: String
    ): Call<DetailUsersResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<Users>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<Users>>
}