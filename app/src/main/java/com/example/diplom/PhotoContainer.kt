package com.example.diplom

class PhotoContainer {
    val set: MutableSet<String> = mutableSetOf()
    fun add(path: String) {
        set.add(path)
    }
}