package com.qaltera.todolistsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*
 * ************************************************
 * NoteAdapter
 * Date: 2020-09-16
 * Author: Yulia Rogovaya
 * ************************************************
 */

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    private val notes: ArrayList<Note> = ArrayList()
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: NoteHolder, position: Int
    ) {
        val currentNote = notes[position]
        holder.titleTextView.text = currentNote.title
        holder.descriptionTextView.text = currentNote.description

        holder.importanceImageView.visibility = if(
            currentNote.importance
        ) View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: List<Note>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Note {
        return notes[position]
    }

    inner class NoteHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = itemView.findViewById(
            R.id.descriptionTextView
        )
        val importanceImageView: ImageView = itemView.findViewById(R.id.importanceImageView)

        init {
            itemView.setOnClickListener(View.OnClickListener {
                listener?.let { listener ->
                    val position: Int = adapterPosition
                    if (position in 0..itemCount) {
                        listener.onItemClick(notes[position])
                    }
                }
            })
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(note: Note?)
    }
}