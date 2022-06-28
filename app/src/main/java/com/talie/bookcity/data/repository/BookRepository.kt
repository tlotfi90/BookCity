package com.talie.bookcity.data.repository

import com.talie.bookcity.data.Api
import com.talie.bookcity.data.model.BookAdd
import com.talie.bookcity.data.model.BookUpdate
import javax.inject.Inject


class BookRepository(private val api: Api) {

    suspend fun getAll() = api.getBooks()

    suspend fun getBookById(id: String) = api.getBookById(id)

    suspend fun addBook(book:BookAdd)=api.addBook(book)

    suspend fun updateBook(id: String,bookUpdate:BookUpdate)=api.updateBook(id, bookUpdate )

    suspend fun deleteBook(id:String)=api.deleteBook(id)


}