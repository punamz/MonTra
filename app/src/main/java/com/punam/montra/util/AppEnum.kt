package com.punam.montra.util

import com.google.gson.annotations.SerializedName

enum class CategoryType {
    @SerializedName("0")
    Expenses,

    @SerializedName("1")
    Income,

    @SerializedName("2")
    Transfer
}

enum class OrderByType {
    @SerializedName("0")
    Highest,

    @SerializedName("1")
    Lowest,

    @SerializedName("2")
    Newest,

    @SerializedName("3")
    Oldest
}


enum class FrequencyType {
    @SerializedName("0")
    Today,

    @SerializedName("1")
    Week,

    @SerializedName("2")
    Month,

    @SerializedName("3")
    Year
}

