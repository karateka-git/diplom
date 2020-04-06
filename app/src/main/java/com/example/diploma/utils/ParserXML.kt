package com.example.diploma.utils

import android.content.Context
import android.util.Log
import com.example.diploma.R
import org.xmlpull.v1.XmlPullParser

class ParserXML(context: Context) {
    private val parser: XmlPullParser
    init {
        parser = context.resources.getXml(R.xml.schedule)
    }

    fun getParse() {
        var i = 0
        while (i<5 && parser.getEventType()!= XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() == XmlPullParser.START_TAG
                && parser.getName().equals("StringOfSchedule")) {
                Log.e("TEST", parser.getAttributeValue(null, "Discipline") + " "
                        + parser.getAttributeValue(null, "Group"))

                i++
            }
            parser.next();
        }
    }
}