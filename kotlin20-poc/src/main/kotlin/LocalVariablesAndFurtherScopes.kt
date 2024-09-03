package com.gabrielspassos

class Cat {
    fun miau() {
        println("Miau!!")
    }
}

class Dog {
    fun bark() {
        println("Au Au!!")
    }
}

fun petAnimalCats(animal: Any) {
    val isCat = animal is Cat
    if (isCat) {
        // In Kotlin 2.0.0, the compiler can access
        // information about isCat, so it knows that
        // animal was smart-cast to the type Cat.
        // Therefore, the purr() function can be called.
        // In Kotlin 1.9.20, the compiler doesn't know
        // about the smart cast, so calling the purr()
        // function triggers an error.
        animal.miau()
    }
}

fun petAnimals(animal: Any) {
    when (animal) {
        is Cat -> animal.miau()
        is Dog -> animal.bark()
    }
}

fun main() {
    val kitty = Cat()
    val buddy = Dog()
    petAnimalCats(kitty)
    petAnimals(kitty)
    petAnimals(buddy)
}