package com.example.diploma.utils.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.R
import com.example.diploma.databinding.RecordBinding
import com.example.diploma.model.Record
import com.example.diploma.repository.records.IRecordsRepository
import kotlinx.android.synthetic.main.record.view.*

class RecordsAdapter(private val listener: OnRecordListener) : RecyclerView.Adapter<RecordsAdapter.RecordsViewHolder>() {
    /**
     * The list of posts of the adapter
     */
    private var records: MutableList<Record> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RecordBinding = DataBindingUtil.inflate(layoutInflater, R.layout.record, parent, false)
        return RecordsViewHolder(
            binding,
            listener,
            parent.context
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

    fun updateRecords(records: List<Record>) {
        this.records = records.toMutableList()
        notifyDataSetChanged()
    }

    interface OnRecordListener {
        fun onRecordClick(record: Record)
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Post item
     */
    inner class RecordsViewHolder(private val binding: RecordBinding,
                            private val listener: OnRecordListener,
                            private val context: Context ):
                                RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener{
                listener.onRecordClick(records[adapterPosition])
            }
        }

        /**
         * Binds a post into the view
         */
        fun bind(record: Record) {
            binding.record = record
            binding.root.setBackgroundColor(ContextCompat.getColor(context,
                when (record.type) {
                    Record.dailyRecord -> {
                        R.color.colorDailyRecord
                    }
                    Record.universityRecord -> {
                        R.color.colorUniversityRecord
                    }
                    Record.holidayRecord -> {
                        R.color.colorHolidayRecord
                    }
                    else -> {
                        R.color.colorAccent
                    }
                }))
            binding.executePendingBindings()
        }
    }
}