package com.talie.bookcity.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.talie.bookcity.data.model.Book
import com.talie.bookcity.databinding.ItemBookBinding

class BookListAdapter:ListAdapter<Book,BookListAdapter.Holder>(DiffUtilCallback()) {
    var onItemClick: ((Book) -> Unit)? = null
    class Holder(private val binding: ItemBookBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(book: Book, onItemClick: ((Book) -> Unit)?) {
            with(binding) {
                txtBookname.text = book.title

                root.setOnClickListener {
                    onItemClick?.invoke(book)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
      return Holder(ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), onItemClick)

    }
    class DiffUtilCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
          return  oldItem.id==newItem.id
        }
    }


}