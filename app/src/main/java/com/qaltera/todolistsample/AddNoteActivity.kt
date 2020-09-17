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

        if (intent?.hasExtra(EXTRA_ID) == true) {
            title = "Edit Note"
            titleEditText.setText(intent.getStringExtra(EXTRA_TITLE))
            descriptionEditText.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
            importanceCheckBox.isChecked = intent.getBooleanExtra(
                EXTRA_IMPORTANCE,
                false
            )
        } else {
            title = "Add Note"
        }
    }

    private fun saveNote() {
        val title = titleEditText.text.toString()
        val description = descriptionEditText.text.toString()
        val checked = importanceCheckBox.isChecked
        if (title.trim { it <= ' ' }.isEmpty() || description.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent()
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_DESCRIPTION, description)
        data.putExtra(EXTRA_IMPORTANCE, checked)
        val id = intent.getIntExtra(EXTRA_ID, -1)
        if (id != -1) {
            data.putExtra(EXTRA_ID, id)
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
        const val EXTRA_TITLE = "com.qaltera.todolistsample.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION =
            "com.qaltera.todolistsample.EXTRA_DESCRIPTION"
        const val EXTRA_IMPORTANCE = "com.qaltera.todolistsample.EXTRA_IMPORTANCE"
        const val EXTRA_ID = "com.qaltera.todolistsample.EXTRA_ID"
    }
}