package com.example.diplom.model

import java.io.Serializable
import java.util.*

data class Record (val id: UUID, val date: String, val title: String, val info: String) : Serializable