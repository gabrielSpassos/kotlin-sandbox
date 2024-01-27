package com.gabrielspassos

import kotlin.random.Random

fun main() {

    // immutable
    val value = 10
    // value = 11 -> this is a compilation error
    // mutable
    var name = "Kotlin"
    println("Hello, $name, value: $value")
    name = "Gabriel"
    println("Hello, $name, value + 2: ${value + 2}")

    variables()
    collections()
    conditions()
    println(sum(2, 3))
    log("Message Sample")

    val user = User("tester", 1)
    println(user.toString())

    val contact = Contact(1, "tester@test.com")
    contact.sendEmail()
    contact.email = "newTester@test.com"
    contact.sendEmail()

    nullSafety()
}

fun variables() {
    // Integer
    val a: Int = -10
    val b: Byte = -12
    val c: Short = -23
    val d: Long = -2839283

    // Unsigned
    val e: UByte = 12u
    val f: UShort = 23u
    val g: UInt = 10u
    val h: ULong = 2839283u

    // Float
    val i: Float = 23.32F
    val j: Double = 43.21

    // Boolean
    val k: Boolean = false
    val l: Boolean = true

    // Char
    val m: Char = '\n'

    // String
    val n: String = "Hello World!"
}

fun collections() {
    // LIST

    // immutable
    val states = listOf("RS", "SC", "PR")
    // states.add or states.remove -> this is a compilation error

    // mutable
    val numbers: MutableList<Int> = mutableListOf(1, 2, 3, 4)
    numbers.add(5)
    numbers.remove(2)

    val lockedNumbers: List<Int> = numbers
    // lockedNumbers.add or lockedNumbers.remove -> this is a compilation error

    println("First state ${states.first()}")
    println("Second state ${states[1]}")
    println("Last number ${numbers.last()}")
    println("Numbers count: ${numbers.count()}")

    // SET

    // immutable
    val fruits = setOf("banana", "apple", "orange", "banana", "grape")

    // mutable
    val mutableFruits = mutableSetOf("banana", "apple", "orange", "banana", "grape")
    mutableFruits.add("cherry")

    println("Fruits $fruits")
    println("Is cherry present? ${"cherry" in mutableFruits}")

    // MAP

    // immutable
    val productsMap = mapOf("t-shirt" to 39.5, "shoes" to 47.84, "pants" to 55.2)

    // mutable
    val mutableProductsMap: MutableMap<String, Double> = mutableMapOf("t-shirt" to 39.5, "shoes" to 47.84, "pants" to 55.2)
    mutableProductsMap.put("socks", 11.7)

    println("Shoes ${productsMap["shoes"]}")
    println("Products $mutableProductsMap")

}

fun conditions() {
    val nextInt = Random.nextInt(0, 10)

    print("$nextInt ")
    if (nextInt % 2 == 0) {
        println("eve")
    } else {
        println("odd")
    }

    val temp = Random.nextInt(-10, 25)
    val description = when {
        temp < 0 -> "very cold"
        temp < 10 -> "a bit cold"
        temp < 20 -> "warm"
        else -> "hot"
    }
    println(description)

    for (number in 1..4) {
        print("$number ")
    }
    print('\n')

    for (number in  1..<4) {
        print("$number ")
    }
    print('\n')

    for (number in 4 downTo 1) {
        print("$number ")
    }
    print('\n')

    for (number in 1..5 step 2) {
        print("$number ")
    }
    print('\n')

    var count = 0;
    while (count < 3) {
        println("Counter $count")
        count++
    }
}

fun sum (x: Int, y: Int): Int {
    return x + y
}

fun log(message:String, type: String = "Info") {
    println("[$type] $message")
}

fun nullSafety() {
    var neverNull: String = "This will never be null"
    //neverNull = null -> this is a compilation error

    var nullable: String? = "You can keep a null here"
    nullable = null

    //println(strLength(nullable)) -> this is a compilation error
    println(strLength(neverNull))
    println(nullable?.length ?: "Default message")
}

fun strLength(notNull: String): Int {
    return notNull.length
}