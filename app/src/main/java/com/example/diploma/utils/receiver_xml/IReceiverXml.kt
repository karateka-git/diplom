package com.example.diploma.utils.receiver_xml

import java.io.InputStream

interface IReceiverXml {
    suspend fun getInputStream(): InputStream
}