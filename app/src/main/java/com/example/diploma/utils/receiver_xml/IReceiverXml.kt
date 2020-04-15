package com.example.diploma.utils.receiver_xml

import com.example.diploma.utils.exception.CustomException
import com.example.diploma.utils.exception.network.NetworkException
import java.io.InputStream

interface IReceiverXml {
    /**
     * @throws CustomException - custom exception::class for exception handling
     * @throws NetworkException - custom exception::class for internet or server connection exception
     */
    @Throws(CustomException::class, NetworkException::class)
    suspend fun getInputStream(): InputStream
}