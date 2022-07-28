package com.azwar.githubuser.model

import com.google.gson.annotations.SerializedName

data class DetailUsersResponse(
    val id: Int,
    @SerializedName("login")
    val username : String,
    val name: String,
    @SerializedName("avatar_url")
    val photo: String,
    @SerializedName("public_repos")
    val repository: String,
    val company: String,
    val location: String,
    val followers: String,
    val following: String
)
