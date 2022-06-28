package com.talie.bookcity.data

import com.talie.bookcity.data.model.Book
import com.talie.bookcity.data.model.BookAdd
import com.talie.bookcity.data.model.BookUpdate
import retrofit2.Response
import retrofit2.http.*


interface Api {


    @FormUrlEncoded
    @POST("books")
    suspend fun addBook(@Body book:BookAdd): Response<*>?

    @GET("books")
    suspend fun getBooks(): Response<List<Book>>


    @GET("books/{id}")
    suspend fun getBookById(
        @Path("id") id:String
    ):Response<Book>

    @PATCH("books/{id}")
    suspend fun updateBook(@Path("id") id: String, @Body bookUpdate: BookUpdate):  Response<*>?

    @DELETE("books/{id}")
    fun deleteBook(@Path("id") id:String): Response<*>?










}