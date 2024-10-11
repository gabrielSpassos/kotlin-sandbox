package com.gabrielspassos.a

interface Animal

class Cat: Animal {
    fun miau() {
        println("Miau!!")
    }
}

class Dog: Animal {
    fun bark() {
        println("Au Au!!")
    }
}

class Car {
    fun start() {
        println("Runnn!!")
    }
}

fun doSomething(thing: Any) {
    when (thing) {
        is Cat -> thing.miau() // smart-cast improvement
        is Dog -> thing.bark() // smart-cast improvement
        is Car -> thing.start() // smart-cast improvement
    }
}

fun main() {
    val kitty = Cat()
    val buddy = Dog()
    val car = Car()
    doSomething(kitty)
    doSomething(buddy)
    doSomething(car)
}