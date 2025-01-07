package com.gabrielspassos

object Feeder {

    private const val MAX_VALUE = 100

    fun feedIntCollection(collection: List<Int>, feedMaxValue: Int = MAX_VALUE): Collection<Int> {
        val mutableList = collection.toMutableList()
        for (i in 1..feedMaxValue) {
            mutableList.add(i)
        }
        return mutableList
    }

    fun feedIntCollection(collection: Set<Int>, feedMaxValue: Int = MAX_VALUE): Collection<Int> {
        val mutableList = collection.toMutableSet()
        for (i in 1..feedMaxValue) {
            mutableList.add(i)
        }
        return mutableList
    }

    fun feedStringCollection(collection: List<String>, feedMaxValue: Int = MAX_VALUE): Collection<String> {
        val mutableList = collection.toMutableList()
        for (i in 1..feedMaxValue) {
            val randomString = randomString()
            mutableList.add(randomString)
        }
        return mutableList
    }

    fun feedStringCollection(collection: Set<String>, feedMaxValue: Int = MAX_VALUE): Collection<String> {
        val mutableList = collection.toMutableSet()
        for (i in 1..feedMaxValue) {
            val randomString = randomString()
            mutableList.add(randomString)
        }
        return mutableList
    }

    private fun randomString(): String {
        val hexChars = "0123456789abcdefghijklmnopqrstuwvxyzABCDEFGHIJKLMNOPQRSTUWVXYZ:!@#$%ˆ&*()~"
        return (1..6)
            .map { hexChars.random() }
            .joinToString("")
    }

}