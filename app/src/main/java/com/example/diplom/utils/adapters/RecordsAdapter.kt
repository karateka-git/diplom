package com.example.diplom.utils.adapters
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom.R
import com.example.diplom.databinding.RecordBinding
import com.example.diplom.model.Record
import kotlinx.android.synthetic.main.record.view.*

class RecordsAdapter(private val listener: OnRecordListener) : RecyclerView.Adapter<RecordsAdapter.RecordsViewHolder>() {
    /**
     * The list of posts of the adapter
     */
    private var records: List<Record> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RecordBinding = DataBindingUtil.inflate(layoutInflater, R.layout.record, parent, false)
        return RecordsViewHolder(
            binding,
            listener
        )
    }

    override fun getItemCount(): Int {
        return records.size
    }

    override fun onBindViewHolder(holder: RecordsViewHolder, position: Int) {
        /**
         * layoutPosition - position in terms of the update layout (last passed layout)
         * adapterPosition - updated adapter's position of this holder
         * 16 ms difference between them
         */

        holder.itemView.info.setOnClickListener {
            holder.itemView.info.text = "info was clicked"
        }
        holder.bind(records[holder.adapterPosition])
    }


    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Post item
     */
    class RecordsViewHolder(private val binding: RecordBinding,
                            private val listener: OnRecordListener):
                                RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener{
                listener.onRecordClick(adapterPosition)
            }
        }

        /**
         * Binds a post into the view
         */
        fun bind(record: Record) {
            binding.record = record
            binding.executePendingBindings()
        }
    }

    fun updateRecords(records: List<Record>) {
        this.records = records
    }

    interface OnRecordListener {
        fun onRecordClick(position: Int)
    }
}