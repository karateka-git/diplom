package com.example.diploma.repository.time_tabling

import android.util.Log
import com.example.diploma.utils.CustomException
import com.example.diploma.utils.receiver_xml.IReceiverXml
import com.example.diploma.utils.receiver_xml.ReceiverXmlFromURL
import kotlinx.coroutines.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import javax.xml.parsers.DocumentBuilderFactory

class XmlTimeTablingRepository() : ITimeTablingRepository {
    private val receiverXML: IReceiverXml = ReceiverXmlFromURL()
    private val parser: XmlPullParser
    private lateinit var parserDOM: Document
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

    @Throws(CustomException::class)
    fun getTeacher(): Map<String, String> =
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
}