package com.example.diploma.utils.receiver_xml

import com.example.diploma.utils.CustomException
import java.io.InputStream

interface IReceiverXml {
    /**
     * @throws CustomException - custom exception::class for exception handling
     */
    @Throws(CustomException::class)
    suspend fun getInputStream(): InputStream
}