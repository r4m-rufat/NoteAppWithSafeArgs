package com.route4me.notesapp.ui.notes

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.route4me.notesapp.R
import com.route4me.notesapp.db.NoteEntity

class NoteAdapter(
    private val context: Context,
    private var list: List<NoteEntity>
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<NoteEntity>) {

        list = newList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_note_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.description.text = list[position].description
    }

    override fun getItemCount(): Int {
        if (list.isNotEmpty())
            return list.size
        return 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById(R.id.txtTitle) as TextView
        val description = itemView.findViewById(R.id.txtDescription) as TextView
    }

}