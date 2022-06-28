package com.talie.bookcity.ui.viewModels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talie.bookcity.data.model.Book
import com.talie.bookcity.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(private val bookRepository: BookRepository) :
    ViewModel() {

    private val book = MutableLiveData<List<Book>>()
    private val TAG: String = "BookVListViewModel"
    fun fetchBook() {
        viewModelScope.launch(Dispatchers.IO) {
            bookRepository.getAll().let { response ->
                if (response.isSuccessful) {
                    book.postValue(response.body())
                }

            }


        }

    }
    fun observBook():LiveData<List<Book>> =book



}