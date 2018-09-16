package com.geeklabs.architectureviewmodellivedataroom


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.ArrayList
import java.util.Collections


class WordListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mWords = emptyList<Word>() // Cached copy of words

    inner class WordViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById<TextView>(R.id.textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = mWords[position]
        holder.wordItemView.text = current.word
    }

    internal fun setWords(words: List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mWords.size
    }
}