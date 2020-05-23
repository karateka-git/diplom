package com.example.diplom_DP.utils.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diplom_DP.R
import com.example.diplom_DP.databinding.RecordBinding
import com.example.diplom_DP.model.Record

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

        holder.bind(records[holder.adapterPosition])
    }

    fun updateRecords(records: List<Record>) {
        this.records = records.toMutableList()
        notifyDataSetChanged()
    }

    interface OnRecordListener {
        fun onRecordClick(record: Record)
        fun onCompletedChange(record: Record)
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
            /**
             * layoutPosition - position in terms of the update layout (last passed layout)
             * adapterPosition - updated adapter's position of this holder
             * 16 ms difference between them
             */
            binding.root.setOnClickListener{
                listener.onRecordClick(records[adapterPosition])
            }
            binding.isCompleted.setOnClickListener {
                listener.onCompletedChange(records[adapterPosition])
            }
        }

        /**
         * Binds a post into the view
         */
        fun bind(record: Record) {
            binding.record = record
            when (record.type) {
                Record.dailyRecord -> {
                    binding.root.setBackgroundColor(ContextCompat.getColor(context, R.color.colorDailyRecord))
                }
                Record.universityRecord -> {
                    binding.root.isClickable = false
                    binding.isCompleted.visibility = View.INVISIBLE
                    binding.root.setBackgroundColor(ContextCompat.getColor(context,R.color.colorUniversityRecord))
                }
                Record.holidayRecord -> {
                    binding.root.setBackgroundColor(ContextCompat.getColor(context,R.color.colorHolidayRecord))
                }
                else -> {
                    binding.root.setBackgroundColor(ContextCompat.getColor(context, R.color.colorDailyRecord))
                }
            }
            binding.executePendingBindings()
        }
    }
}