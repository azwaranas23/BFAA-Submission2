package com.azwar.githubuser.model

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("items")
    val items: ArrayList<Users>
)
