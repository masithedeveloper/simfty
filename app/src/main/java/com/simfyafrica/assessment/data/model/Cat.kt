package com.simfyafrica.assessment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

@Parcel
@Entity(tableName = "cat_table")
data class Cat(

    @SerializedName("id")
    @PrimaryKey
    val id: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("title")
    var title: String?
) {
    override fun toString(): String {
        return "Cat(description='$description', title='$title')"
    }
}

