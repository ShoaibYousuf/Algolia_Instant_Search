package com.example.algoliatask

import com.algolia.instantsearch.core.highlighting.HighlightedString
import com.algolia.instantsearch.highlighting.Highlightable
import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Indexable
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Product(
    val name: String,
    val image: String,
    val price: Double,
    val description: String,
    override val objectID: ObjectID,
    override val _highlightResult: JsonObject?
) : Indexable, Highlightable {

    val highlightedName: HighlightedString?
        get() = getHighlight(Attribute("name"))
}



/*
@Serializable
data class Product(
    @SerializedName("onSale")
    var isOnSale: Boolean = false,

    @SerializedName("attributes")
    var attributes: ArrayList<String> = arrayListOf(),

    @SerializedName("productType")
    var productType: String,

    @SerializedName("gallery")
    var gallery: ArrayList<String> = arrayListOf(),

    @SerializedName("deleted")
    var isDeleted: Boolean,

    @SerializedName("productName")
    var productName: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("selectedAttributes")
    var selectedAttributes: ArrayList<String> = arrayListOf(),

    @SerializedName("price")
    var price: String,

    @SerializedName("regularPrice")
    var regularPrice: String?,

    @SerializedName("salePrice")
    var salePrice: String,

    @SerializedName("mainImage")
    var mainImage: String,

    @SerializedName("quantity")
    var quantity: Long,

    @SerializedName("active")
    var isActive: Boolean = false,

    @SerializedName("videoUrl")
    var videoUrl: String,

    @SerializedName("weight")
    var weight: Double,

    @SerializedName("sku")
    var sku: String,

    //@SerializedName("category")
    //var category: MainCategory?,

    @SerializedName("reviews")
    var reviews: ArrayList<String> = arrayListOf(),

    @SerializedName("id")
    var id: String,

    @SerializedName("variants")
    var variants: ArrayList<String> = arrayListOf(),

    var isSelected: Int,
    var isBulkSelected: Boolean,
    var isAlreadySelected: Boolean
)
*/