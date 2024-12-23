package com.gabrielspassos

fun main() {
    println("Hello World!")
    println(Singleton.name)
    println(Animal("Dog"))
    println(Person("Gabriel", "Passos"))

    val gameService = GameService()
    val updatedGame = gameService.updateGameByOwner("Gabriel", "Lucas")
    println(updatedGame)
}