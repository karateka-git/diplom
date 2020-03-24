package com.example.diplom.ui.main
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.R
import com.example.diplom.databinding.NoteBinding
import com.example.diplom.model.Record

class RecordsAdapter(private val context: Context) : RecyclerView.Adapter<RecordsAdapter.NotesViewHolder>() {
    /**
     * The list of posts of the adapter
     */
    private var records: List<Record> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: NoteBinding = DataBindingUtil.inflate(layoutInflater, R.layout.note, parent, false)
        return NotesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return records.size
    }

    override fun onBindViewHolder(holder: RecordsAdapter.NotesViewHolder, position: Int) {
        holder.bind(records[position])
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Post item
     */
    class NotesViewHolder(private val binding: NoteBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a post into the view
         */
        fun bind(record: Record) {
            binding.note = record
            binding.executePendingBindings()
        }
    }

    fun updateNotes(records: List<Record>) {
        this.records = records
    }
}