package com.talie.bookcity.data.model




data class Book(
    val title: String,
    val author: String,
    val id: String,
    val genre: String,
    val yearPublished: String,
    var checkedOut: Boolean,
    val createdAt: String
)
