package com.example.diplom_DP.repository.time_tabling

import android.util.Log
import com.example.diplom_DP.Constants
import com.example.diplom_DP.MyApplication
import com.example.diplom_DP.db.entity.RecordEntity
import com.example.diplom_DP.model.Record
import com.example.diplom_DP.utils.DateAndTimeUtility
import com.example.diplom_DP.utils.exception.CustomException
import com.example.diplom_DP.utils.receiver_xml.IReceiverXml
import kotlinx.coroutines.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
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
                parserDOM.documentElement.normalize()
                use()
            }
    }

    override fun getTeachers(): Map<String, String> =
        setInput {
            val result = mutableMapOf<String, String>()
            val teacherList = parserDOM.getElementsByTagName(Constants.tagNameTeacher)
            val teacherMap = nodeListToMap(teacherList, Constants.attributeNameIndex)
            teacherMap.mapValues { it.value.getAttribute(Constants.attributeNameTeacherName) }
        }

    override fun getDate(): Map<Int, String> =
        setInput {
            val result = mutableMapOf<Int, String>()
            val universityTabling = parserDOM.getElementsByTagName(Constants.tagNameTimeTabling).item(0)
            if (universityTabling.nodeType == Node.ELEMENT_NODE) {
                val element = universityTabling as Element
                result[0] = element.getAttribute(Constants.attributeNameDate)
                Log.e(
                    "date", "${result[0]}"
                )
            }
            result
        }


    override fun getSchedule(teacherID: String): Map<UUID, RecordEntity> =
        setInput {
            val result = mutableMapOf<UUID, RecordEntity>()
            parserDOM.documentElement.normalize()
            val scheduleList = parserDOM.getElementsByTagName(Constants.tagNameStringOfSchedule)
            val timeWindows = nodeListToMap(
                parserDOM.getElementsByTagName(Constants.tagNameTimeWindow),
                Constants.attributeNameIndex)

            for (i in 0 until scheduleList.length) {
                val schedule = scheduleList.item(i)
                if (schedule.nodeType == Node.ELEMENT_NODE) {
                    val element = schedule as Element
                    val dateTime = getDateTime(timeWindows, element.getAttribute(Constants.attributeNameTimeWindow))
                    if (element.getAttribute(Constants.attributeNameTeacher) == teacherID) {
                        val record = RecordEntity(
                            UUID.randomUUID(),
                            DateAndTimeUtility.toMyDateFormat(dateTime[Constants.attributeNameDate]?:""),
                            dateTime[Constants.attributeNameTimeFrom]?:"",
                            dateTime[Constants.attributeNameTimeTo]?:"",
                            element.getAttribute(Constants.attributeNameGroup),
                            element.getAttribute(Constants.attributeNameDiscipline),
                            Record.universityRecord,
                            false
                        )
                        Log.d(
                            "schedule", "${record.date} ${record.info}"
                        )
                        result[record.uuid] = record
                    }

                }
            }
            result
        }

    private fun nodeListToMap(list: NodeList, index: String): Map<String, Element> {
        val result = mutableMapOf<String, Element>()
        for (i in 0 until list.length) {
            val item = list.item(i)
            if (item.nodeType == Node.ELEMENT_NODE) {
                val element = item as Element
                result[element.getAttribute(index)] = element
            }
        }
        return result
    }

    private fun getDateTime(timeWindows: Map<String, Element>, index: String): Map<String, String> {
        val element = timeWindows[index]
        val result = mutableMapOf<String, String>()
        if (element != null) {
            result[Constants.attributeNameDate] = element.getAttribute(Constants.attributeNameDate) //TODO add String Const
            result[Constants.attributeNameTimeFrom] = element.getAttribute(Constants.attributeNameTimeFrom)
            result[Constants.attributeNameTimeTo] = element.getAttribute(Constants.attributeNameTimeTo)
            return result
        }
        return result
    }
}