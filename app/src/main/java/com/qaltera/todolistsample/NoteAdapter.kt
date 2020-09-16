package com.qaltera.todolistsample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private var notes: List<Note> = ArrayList()
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
        holder.textViewTitle.text = currentNote.title
        holder.textViewDescription.text = currentNote.description
        holder.textViewPriority.text = currentNote.priority.toString()
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Note {
        return notes[position]
    }

    inner class NoteHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val textViewTitle: TextView = itemView.findViewById(R.id.text_view_title)
        val textViewDescription: TextView = itemView.findViewById(R.id
            .text_view_description)
        val textViewPriority: TextView = itemView.findViewById(R.id.text_view_priority)

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