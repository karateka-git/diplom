package com.example.diploma.model

import java.io.Serializable
import java.util.*

data class Record (val id: UUID,
                   var date: String,
                   var title: String,
                   var info: String,
                   var type: String = "default"
    ): Serializable