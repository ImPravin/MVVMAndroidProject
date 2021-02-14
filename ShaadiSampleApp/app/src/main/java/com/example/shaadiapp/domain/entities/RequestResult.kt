package com.example.shaadiapp.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class RequestResult(
        @SerializedName("results") val results: List<PersonRequestEntity>,
)

@Entity(tableName = "request_table")
data class PersonRequestEntity(
        // status 0 = accepted, status 1 = rejected, status 2 = not updated
        @ColumnInfo(name = "status") var status: Int,
        @SerializedName("gender") val gender: String,
        @SerializedName("name") val name: Name,
        @SerializedName("location") val location: Location,
        @PrimaryKey @ColumnInfo(name = "email")
        @SerializedName("email") val email: String,
        @SerializedName("dob") val dob: Dob,
        @SerializedName("phone") val phone: String,
        @SerializedName("cell") val cell: String,
        @SerializedName("picture") val picture: Picture,
        @SerializedName("nat") val nat: String
)

data class Name(
        @SerializedName("title") val title: String,
        @SerializedName("first") val first: String,
        @SerializedName("last") val last: String
)

data class Dob(
        @SerializedName("date") val date: String,
        @SerializedName("age") val age: Int
)

data class Location(
        @SerializedName("city") val city: String,
        @SerializedName("state") val state: String,
        @SerializedName("country") val country: String,
        @SerializedName("postcode") val postcode: String,
)

data class Picture(
        @SerializedName("large") val large: String,
        @SerializedName("medium") val medium: String,
        @SerializedName("thumbnail") val thumbnail: String
)