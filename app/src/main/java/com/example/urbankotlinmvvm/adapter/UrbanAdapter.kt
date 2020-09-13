package com.example.urbankotlinmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.urbankotlinmvvm.R
import com.example.urbankotlinmvvm.model.Definition
import kotlinx.android.synthetic.main.list_item.view.*

class UrbanAdapter : RecyclerView.Adapter<UrbanAdapter.DefinitionViewHolder>() {

    inner class DefinitionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Definition>() {
        override fun areItemsTheSame(oldItem: Definition, newItem: Definition): Boolean {
            return oldItem.defid == newItem.defid
        }

        override fun areContentsTheSame(oldItem: Definition, newItem: Definition): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
        return DefinitionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        val definition = differ.currentList[position]
        holder.itemView.apply {
            tv_author.text = definition.author
            tv_definition.text = definition.definition
            tv_examples.text = definition.example
            tv_thumbs_down.text = definition.thumbs_down.toString()
            tv_thumbs_up.text = definition.thumbs_up.toString()
            tv_word.text = definition.word
            tv_writtenOn.text = definition.written_on
        }
    }
}