package com.example.lplsample.data

interface Mapper<in From, out To> {
    fun map(from: From): To
}