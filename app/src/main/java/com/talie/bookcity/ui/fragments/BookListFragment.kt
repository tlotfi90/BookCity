package com.talie.bookcity.ui.fragments

import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.talie.bookcity.MainActivity
import com.talie.bookcity.data.model.Book
import com.talie.bookcity.databinding.FragmentBookListBinding
import com.talie.bookcity.ui.adapter.BookListAdapter
import com.talie.bookcity.ui.viewModels.BookListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class BookListFragment : Fragment() {

    lateinit var binding: FragmentBookListBinding
    lateinit var bookadapter: BookListAdapter
    val viewmodel by viewModels<BookListViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        viewmodel.fetchBook()
        binding.btnAddBook.setOnClickListener {
            val direction =
                BookListFragmentDirections.actionBookListFragment2ToBookDetailFragment(null)
            findNavController().navigate(direction)
        }
        viewmodel.observBook().observe(viewLifecycleOwner, {
            bookadapter.submitList(it)
        })
    }

    fun setupRecycler() {
        bookadapter = BookListAdapter().apply {
            onItemClick = { book ->
                val direction =
                    BookListFragmentDirections.actionBookListFragment2ToBookDetailFragment(book.id)
                findNavController().navigate(direction)
            }
        }

        binding.rcBooklist.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookadapter
        }
        binding.btnAddBook.setOnClickListener {
            val direction =
                BookListFragmentDirections.actionBookListFragment2ToBookDetailFragment(null)
            findNavController().navigate(direction)
        }
    }


}