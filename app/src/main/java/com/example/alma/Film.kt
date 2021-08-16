package com.example.alma

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Film(

    var title: String ?= null,
    var year: Int ?= null,
    var genre: List<String> = emptyList(),
    var runtime: Int ?= null,
    var rating: Double ?= null,
    var votes: Int ?= null,

) : Parcelable
