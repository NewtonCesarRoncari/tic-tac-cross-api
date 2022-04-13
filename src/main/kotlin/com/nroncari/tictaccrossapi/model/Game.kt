package com.nroncari.tictaccrossapi.model

import com.nroncari.tictaccrossapi.exception.InvalidGameException

class Game(
    val id: String,
    var amountPlayers: Int = 0,
    var state: GameState,
    var board: Array<IntArray> = Array(3) { IntArray(3) },
    var winner: TicToe? = null
) {
    fun addPlayer() {
      //  if (amountPlayers >= AMOUNT_PLAYERS_AVAILABLE) throw InvalidGameException("Game is not valid anymore")
        amountPlayers++
    }

    fun clearBoard() {
        board = Array(3) { IntArray(3) }
    }

    private companion object CONSTANT {
        const val AMOUNT_PLAYERS_AVAILABLE = 2
    }
}