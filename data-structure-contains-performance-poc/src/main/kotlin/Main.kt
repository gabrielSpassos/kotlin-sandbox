package com.gabrielspassos


fun main() {
    //1000000 items - 1 million
    val oneMillion = 1000000
    val fiveMillion = 5000000
    val tenMillion = 10000000
    val itemsNumber = tenMillion

//    val intListItems = Feeder.feedIntCollection(listOf(), itemsNumber)
//    val intListItemsResult =
//        PerformanceMeasurer.measureContainsItemTimeTaken(intListItems.random(), intListItems)
//    println("Int list with ${intListItems.size} items result: found=${intListItemsResult.first}" +
//            " timeTaken=${intListItemsResult.second}ms")
//
//    val intMutableListItems = Feeder.feedIntCollection(mutableListOf(), itemsNumber)
//    val intMutableListItemsResult =
//        PerformanceMeasurer.measureContainsItemTimeTaken(intMutableListItems.random(), intMutableListItems)
//    println("Int mutable list with ${intMutableListItems.size} items result: " +
//            "found=${intMutableListItemsResult.first} timeTaken=${intMutableListItemsResult.second}ms")
//
//    val intSetItems = Feeder.feedIntCollection(setOf(), itemsNumber)
//    val intSetWithItemsResult =
//        PerformanceMeasurer.measureContainsItemTimeTaken(intSetItems.random(), intSetItems)
//    println("Int set with ${intSetItems.size} items result: found=${intSetWithItemsResult.first}" +
//            " timeTaken=${intSetWithItemsResult.second}ms")
//
//    val intMutableSetItems = Feeder.feedIntCollection(mutableSetOf(), itemsNumber)
//    val intMutableSetItemsResult =
//        PerformanceMeasurer.measureContainsItemTimeTaken(intMutableSetItems.random(), intMutableSetItems)
//    println("Int mutable set with ${intMutableSetItems.size} items result: " +
//            "found=${intMutableSetItemsResult.first} timeTaken=${intMutableSetItemsResult.second}ms")
//
//    val stringListItems = Feeder.feedStringCollection(listOf(), itemsNumber)
//    val stringListItemsResult =
//        PerformanceMeasurer.measureContainsItemTimeTaken(stringListItems.random(), stringListItems)
//    println("String list with ${stringListItems.size} items result: " +
//            "found=${stringListItemsResult.first} timeTaken=${stringListItemsResult.second}ms")
//
//    val stringMutableListItems = Feeder.feedStringCollection(mutableListOf(), itemsNumber)
//    val stringMutableListItemsResult =
//        PerformanceMeasurer.measureContainsItemTimeTaken(stringMutableListItems.random(), stringMutableListItems)
//    println("String mutable list with ${stringMutableListItems.size} items result: " +
//            "found=${stringMutableListItemsResult.first} timeTaken=${stringMutableListItemsResult.second}ms")

    val stringSetItems = Feeder.feedStringCollection(setOf(), itemsNumber)
    val stringSetItemsResult =
        PerformanceMeasurer.measureContainsItemTimeTaken(stringSetItems.random(), stringSetItems)
    println("String set with ${stringSetItems.size} items result: " +
            "found=${stringSetItemsResult.first} timeTaken=${stringSetItemsResult.second}ms")

    val stringMutableSetItems = Feeder.feedStringCollection(mutableSetOf(), itemsNumber)
    val stringMutableSetItemsResult =
        PerformanceMeasurer.measureContainsItemTimeTaken(stringMutableSetItems.random(), stringMutableSetItems)
    println("String mutable list with ${stringMutableSetItems.size} items result: " +
            "found=${stringMutableSetItemsResult.first} timeTaken=${stringMutableSetItemsResult.second}ms")
}