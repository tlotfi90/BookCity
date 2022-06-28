package com.talie.bookcity.ui.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talie.bookcity.data.model.Book
import com.talie.bookcity.data.model.BookAdd
import com.talie.bookcity.data.model.BookUpdate
import com.talie.bookcity.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(private val bookRepository: BookRepository) :
    ViewModel() {

    private val book = MutableLiveData<Book>()
    private val TAG: String = "BookVListViewModel"
    fun fetchBookById(id:String) {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.getBookById(id).let { response ->
                if (response.isSuccessful) {
                    book.postValue(response.body())
                }
            }
        }
    }
    fun observe():LiveData<Book> =book
    fun addBook(addbook:BookAdd){
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.addBook(addbook).let{response->
                {  }

            }
        }
    }
    fun upDateBook(id: String,bookUpdate: BookUpdate){
        viewModelScope.launch(Dispatchers.IO){
            bookRepository.updateBook(id,bookUpdate).let {

            }
        }
    }

    fun deleteBook(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.deleteBook(id)
        }
    }




}