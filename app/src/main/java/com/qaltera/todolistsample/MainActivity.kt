package com.qaltera.todolistsample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val adapter = NoteAdapter()
        recyclerView.adapter = adapter

        val buttonAddNote: FloatingActionButton = findViewById(R.id.button_add_note)
        buttonAddNote.setOnClickListener {
                val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
                startActivityForResult(intent, ADD_NOTE_REQUEST)
            }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)

        adapter.setOnItemClickListener(object :
            NoteAdapter.OnItemClickListener {
            override fun onItemClick(note: Note?) {
                if (note != null) {
                    val intent = Intent(this@MainActivity, AddNoteActivity::class.java)

                    intent.putExtra(AddNoteActivity.EXTRA_ID, note.id)
                    intent.putExtra(AddNoteActivity.EXTRA_TITLE, note.title)
                    intent.putExtra(AddNoteActivity.EXTRA_DESCRIPTION, note.description)
                    intent.putExtra(AddNoteActivity.EXTRA_IMPORTANCE, note.importance)
                    startActivityForResult(intent, EDIT_NOTE_REQUEST)
                }
            }
        })

        noteViewModel.allNotes.observe(
            this, object : Observer<List<Note>> {
                override fun onChanged(
                    @Nullable notes: List<Note>
                ) {
                    adapter.setNotes(notes);
                }
            })
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int, data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NOTE_REQUEST &&
            resultCode == Activity.RESULT_OK &&
            data != null) {
            val title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE)
            val description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
            val importance = data.getBooleanExtra(AddNoteActivity.EXTRA_IMPORTANCE, false)
            val note = Note(title, description, importance)
            noteViewModel.insert(note)
        } else if (requestCode === EDIT_NOTE_REQUEST &&
            resultCode === Activity.RESULT_OK &&
            data != null) {
            val id: Int = data.getIntExtra(AddNoteActivity.EXTRA_ID, -1)
            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show()
                return
            }
            val title: String = data.getStringExtra(AddNoteActivity.EXTRA_TITLE)
            val description: String =
                data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION)
            val importance = data.getBooleanExtra(AddNoteActivity.EXTRA_IMPORTANCE, false)
            val note = Note(title, description, importance)
            note.id = id
            noteViewModel.update(note)
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val ADD_NOTE_REQUEST = 1
        const val EDIT_NOTE_REQUEST = 2
    }
}