package com.azwar.githubuser.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Users(
    val login: String,
    val id: Int,
    val avatar_url: String
)
