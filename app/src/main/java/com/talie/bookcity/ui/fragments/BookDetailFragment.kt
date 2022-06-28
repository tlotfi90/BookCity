package com.talie.bookcity.ui.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.talie.bookcity.data.model.Book
import com.talie.bookcity.data.model.BookAdd
import com.talie.bookcity.data.model.BookUpdate
import com.talie.bookcity.data.repository.BookRepository
import com.talie.bookcity.databinding.FragmentBookDetailBinding
import com.talie.bookcity.ui.viewModels.BookDetailViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailFragment : Fragment() {

    lateinit var binding: FragmentBookDetailBinding
    private val args by navArgs<BookDetailFragmentArgs>()
    val bookDetailViewModel by viewModels<BookDetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.id.isNullOrEmpty()) {
            binding.btnDelete.visibility = View.INVISIBLE
            binding.btnEdit.visibility = View.INVISIBLE
            binding.btnUpdateBook.visibility = View.INVISIBLE
            binding.edtId.visibility=View.INVISIBLE
           binding.edtCreatedAt.visibility=View.GONE
            binding.createdAt.visibility=View.GONE
            binding.id.visibility=View.GONE

            binding.btnAddBook.visibility = View.VISIBLE
        } else {
            binding.btnAddBook.visibility = View.INVISIBLE
            bookDetailViewModel.fetchBookById(args.id.toString())

        }

        bookDetailViewModel.observe().observe(viewLifecycleOwner, {

            binding.edtTitle.setText(it.title)
            binding.edtId.setText(it.id)
            binding.edtCreatedAt.setText(it.createdAt)
            binding.edtAuthor.setText(it.author)
            binding.edtYearPublished.setText(it.yearPublished)
            binding.edtGenre.setText(it.genre)
        })

        binding.btnAddBook.setOnClickListener {
            val book = BookAdd(
                binding.edtTitle.toString(),
                binding.edtAuthor.toString(),
                binding.edtGenre.toString(),
                binding.edtYearPublished.toString()
            )
            bookDetailViewModel.addBook(book)
        }

        binding.btnUpdateBook.setOnClickListener {
            val bookUpdate = BookUpdate(true)
            bookDetailViewModel.upDateBook(args.id.toString(), bookUpdate)
        }
        binding.btnDelete.setOnClickListener {

        }
        binding.btnDelete.setOnClickListener {
            bookDetailViewModel.deleteBook(args.id.toString())
            findNavController().navigate(BookDetailFragmentDirections.actionBookDetailFragmentToBookListFragment2())
        }


    }


}