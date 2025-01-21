package com.gabrielspassos

fun main() {
    println("Hello World!")
    println(Singleton.name)
    println(Animal("Dog"))
    println(Person("Gabriel", "Passos"))

    val gameService = GameService()
    val updatedGame = gameService.updateGameByOwner("Gabriel", "Lucas")
    println(updatedGame)

    val singleInput = Input(SingleVariable("single-test"))
    val multipleInput = Input(MultipleVariables(listOf("multi-test1", "multi-test2")))
    println(singleInput)
    println(multipleInput)
}