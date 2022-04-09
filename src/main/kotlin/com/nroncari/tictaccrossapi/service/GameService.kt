package com.nroncari.tictaccrossapi.service

import com.nroncari.tictaccrossapi.exception.InvalidGameException
import com.nroncari.tictaccrossapi.exception.NotFoundException
import com.nroncari.tictaccrossapi.model.Game
import com.nroncari.tictaccrossapi.model.GamePlay
import com.nroncari.tictaccrossapi.model.GameState
import com.nroncari.tictaccrossapi.model.TicToe
import com.nroncari.tictaccrossapi.storage.GameStorage
import org.springframework.stereotype.Service
import java.util.*


@Service
class GameService {

    fun createGame(): Game {
        val game = Game(
            id = UUID.randomUUID().toString().substring(0, 8),
            amountPlayers = 1,
            state = GameState.NEW,
        )
        GameStorage.setGame(game)
        return game
    }

    fun connectToGame(gameId: String): Game {
        if (!GameStorage.getGames().containsKey(gameId))
            throw IllegalArgumentException("Game with provided id doesn't exists")

        val game = GameStorage.getGames()[gameId]
        game!!.addPlayer()
        game.state = GameState.RUNNING
        GameStorage.setGame(game)

        return game
    }

    fun gamePlay(gamePlay: GamePlay): Game {
        if (!GameStorage.getGames().containsKey(gamePlay.gameId))
            throw NotFoundException("Game not found")

        val game = GameStorage.getGames()[gamePlay.gameId]
        if (game!!.state == GameState.FINISHED) throw InvalidGameException("Game is already finished")

        val board = game.board
        board[gamePlay.coordinateX][gamePlay.coordinateY] = gamePlay.type!!.value

        val oWinner = checkWinner(game.board, TicToe.O)
        val xWinner = checkWinner(game.board, TicToe.X)

        if (oWinner) game.winner = TicToe.O else if (xWinner) game.winner = TicToe.X

        GameStorage.setGame(game)
        return game
    }

    private fun checkWinner(board: Array<IntArray>, ticToe: TicToe) : Boolean {
        val boardArray = IntArray(9)
        var counterIndex = 0

        val winCombinations = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )

        board.forEachIndexed { x, ints ->
            ints.forEachIndexed { y, _ ->
                boardArray[counterIndex] = board[x][y]
                counterIndex++
            }
        }

        for (i in winCombinations.indices) {
            var counter = 0
            for (j in 0 until winCombinations[i].size) {
                if (boardArray[winCombinations[i][j]] == ticToe.value) {
                    counter++
                    if (counter == 3) {
                        return true
                    }
                }
            }
        }
        return false
    }
}