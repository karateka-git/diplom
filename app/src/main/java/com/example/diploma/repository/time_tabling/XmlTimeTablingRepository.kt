package com.example.diploma.repository.time_tabling

import android.util.Log
import com.example.diploma.MyApplication
import com.example.diploma.model.Record
import com.example.diploma.repository.records.IRecordsRepository
import com.example.diploma.utils.exception.CustomException
import com.example.diploma.utils.receiver_xml.IReceiverXml
import kotlinx.coroutines.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.util.*
import javax.inject.Inject
import javax.xml.parsers.DocumentBuilderFactory

class XmlTimeTablingRepository(private val receiverXML: IReceiverXml) : ITimeTablingRepository {
    private val parser: XmlPullParser
    private lateinit var parserDOM: Document

    @Inject
    lateinit var application: MyApplication

    init {
        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        parser = factory.newPullParser()

    }

    @Throws(CustomException::class)
    private fun <K, V> setInput(use: () -> Map<K, V>): Map<K, V> =
        runBlocking(Dispatchers.IO) {
            receiverXML.getInputStream().use {
                parserDOM = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(it)
                use()
            }
    }

    override fun getTeachers(): Map<String, String> =
        setInput {
            val result = mutableMapOf<String, String>()
            parserDOM.documentElement.normalize()
            val teacherList = parserDOM.getElementsByTagName("Teacher")
            for (i in 0 until teacherList.length) {
                val teacher = teacherList.item(i)
                if (teacher.nodeType == Node.ELEMENT_NODE) {
                    val element = teacher as Element
                    val index = element.getAttribute("Index")
                    val name = element.getAttribute("Name")
                    Log.e(
                        "Teacher", "$index - $name"
                    )
                    result[index] = name
                }
            }
            result
        }

    override fun getDate(): Map<Int, String> =
        setInput {
            val result = mutableMapOf<Int, String>()
            val universityTabling = parserDOM.getElementsByTagName("UniversityTimeTabling").item(0)
            if (universityTabling.nodeType == Node.ELEMENT_NODE) {
                val element = universityTabling as Element
                result[0] = element.getAttribute("Date")
                Log.e(
                    "date", "${result[0]}"
                )
            }
            result
        }


    override fun getSchedule(teacherID: String): Map<UUID, Record> =
        setInput {
            val result = mutableMapOf<UUID, Record>()
            parserDOM.documentElement.normalize()
            val scheduleList = parserDOM.getElementsByTagName("StringOfSchedule")
            for (i in 0 until scheduleList.length) {
                val schedule = scheduleList.item(i)
                if (schedule.nodeType == Node.ELEMENT_NODE) {
                    val element = schedule as Element
                    if (element.getAttribute("Teacher") == teacherID) {
                        val record = Record(
                            UUID.randomUUID(),
                            "",
                            element.getAttribute("Group"),
                            element.getAttribute("Discipline"),
                            IRecordsRepository.universityRecordsRepository
                        )
                        Log.e(
                            "schedule", "${record.title} ${record.info}"
                        )
                        result[record.uuid] = record
                    }

                }
            }
            result
        }
}