package com.example.myapplication.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal (
    var portalTitle: String,
    var portalURL: String

) : Parcelable