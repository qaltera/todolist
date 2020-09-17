package com.qaltera.todolistsample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/*
 * ************************************************
 * AddNoteActivity
 * Date: 2020-09-16
 * Author: Yulia Rogovaya
 * ************************************************
 */

class AddNoteActivity : AppCompatActivity() {
    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var importanceCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        titleEditText = findViewById(R.id.titleEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        importanceCheckBox = findViewById(R.id.importanceCheckBox)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        if (intent?.hasExtra(NOTE_ID_EXTRA_KEY) == true) {
            title = "Edit Note"
            val note = intent.getSerializableExtra(NOTE_EXTRA_KEY) as Note
            titleEditText.setText(note.title)
            descriptionEditText.setText(note.description)
            importanceCheckBox.isChecked = note.importance
        } else {
            title = "Add Note"
        }
    }

    private fun saveNote() {
        val title = titleEditText.text.toString()
        val description = descriptionEditText.text.toString()
        val checked = importanceCheckBox.isChecked
        if (title.isBlank() || description.isBlank()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent()
        data.putExtra(NOTE_EXTRA_KEY, Note(title, description, checked))
        val id = intent.getIntExtra(NOTE_ID_EXTRA_KEY, -1)
        if (id != -1) {
            data.putExtra(NOTE_ID_EXTRA_KEY, id)
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.save_note -> {
                saveNote()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val NOTE_EXTRA_KEY = "com.qaltera.todolistsample.NOTE_EXTRA_KEY"
        const val NOTE_ID_EXTRA_KEY = "com.qaltera.todolistsample.NOTE_ID_EXTRA_KEY"
    }
}