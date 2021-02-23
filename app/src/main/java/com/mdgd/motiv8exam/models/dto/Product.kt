package com.mdgd.motiv8exam.models.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// {"name": "Oranges", "weight": "1.1kg", "bagColor": "#835322"}
class Product(name: String, weight: String, color: String) {

    @Expose
    @SerializedName("name")
    var name: String = name

    @Expose
    @SerializedName("weight")
    var weight: String = weight

    @Expose
    @SerializedName("bagColor")
    var color: String = color
}
