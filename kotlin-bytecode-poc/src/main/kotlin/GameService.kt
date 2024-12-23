package com.gabrielspassos

class GameService {

    fun getGame(owner: String?): Game? {
        return if (owner == "Gabriel") {
            Game("Game 1", owner)
        } else {
            null
        }
    }

    fun updateGameOwner(game: Game?, newOwner: String?): Game? {
        return game?.copy(owner = newOwner)
    }

    fun updateGameByOwner(oldOwner: String, newOwner: String): Game? {
        return getGame(oldOwner)?.let {
            updateGameOwner(it, newOwner)
        }
    }
}