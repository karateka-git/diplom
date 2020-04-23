package com.example.diploma.repository.records

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.diploma.R
import com.example.diploma.db.AppDatabase
import com.example.diploma.db.entity.RecordEntity
import com.example.diploma.model.Record
import com.example.diploma.utils.MyCalendar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.util.*

class RecordsRepository(private val appDatabase: AppDatabase) :
    IRecordsRepository <RecordEntity>{

    private val classTag = RecordsRepository::class.java.simpleName

    private val selectedIndex = MutableLiveData<Int>()

    lateinit var selectedDate: String

    override var valuesMap = Transformations.switchMap(selectedIndex){
        Log.d(classTag, "From Transformation $selectedDate")
        when (it) {
            R.id.all_records -> appDatabase.recordDao.getAllRecords(selectedDate)
            R.id.my_records -> appDatabase.recordDao.getAllRecordsType(Record.dailyRecord)
            R.id.holiday_records -> appDatabase.recordDao.getAllRecordsType(Record.holidayRecord)
            else -> null
        }
    }

    init {
        runBlocking(Dispatchers.IO) {
            appDatabase.recordDao.deleteAll()
        }
    }

    fun setSelectedRecords(index: Int) {
        Log.d(classTag, "From setSelectedRecords $selectedDate")
        selectedIndex.value = index
    }

    override fun getEmptyRecord(): RecordEntity {
        return RecordEntity(
            newID(),
            MyCalendar().dateToString(),
            "",
            "",
            "",
            "",
            ""
        )
    }

    override fun set(record: RecordEntity) {
        runBlocking(Dispatchers.IO) {
            appDatabase.recordDao.insert(record)
        }
    }

    override fun update(record: RecordEntity) {
        runBlocking(Dispatchers.IO) {
            appDatabase.recordDao.insert(record)
        }
    }

    override fun get(id: UUID): RecordEntity? {
        return runBlocking(Dispatchers.IO) {
            appDatabase.recordDao.getRecord(id)
        }
    }

    override fun setAll(records: Map<UUID, RecordEntity>) {
        return runBlocking(Dispatchers.IO) {
            appDatabase.recordDao.insertAll(records.values.toList())
        }
    }

    override fun updateUniversityRecords(records: Map<UUID, RecordEntity>) {
        runBlocking(Dispatchers.IO) {
            appDatabase.recordDao.deleteRecordsType(Record.universityRecord)
            appDatabase.recordDao.insertAll(records.values.toList())
        }
    }
}