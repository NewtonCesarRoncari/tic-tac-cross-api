package com.nroncari.tictaccrossapi.model

class Game(
    val id: String,
    var amountPlayers: Int = 0,
    var lastTicToe: TicToe? = null,
    var state: GameState,
    var board: Array<IntArray> = Array(3) { IntArray(3) },
    var winner: TicToe? = null,
    var xScore: Int = 0,
    var oScore: Int = 0
) {
    fun addPlayer() {
        //if (amountPlayers >= AMOUNT_PLAYERS_AVAILABLE) throw NumberPlayersException()
        amountPlayers++
    }

    fun clearBoard() {
        board = Array(3) { IntArray(3) }
    }

    fun setWinnerGame(ticToe: TicToe) {
        winner = ticToe
        if (ticToe == TicToe.O) oScore++ else if (ticToe == TicToe.X) xScore++
    }

    private companion object CONSTANT {
        const val AMOUNT_PLAYERS_AVAILABLE = 2
    }
}