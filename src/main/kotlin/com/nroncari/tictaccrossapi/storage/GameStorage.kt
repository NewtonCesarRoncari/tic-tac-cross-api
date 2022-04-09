package com.nroncari.tictaccrossapi.storage

import com.nroncari.tictaccrossapi.model.Game
import java.util.*

object GameStorage {

    private val games: MutableMap<String, Game> = HashMap<String, Game>()

    fun getGames(): Map<String, Game> = games

    fun setGame(game: Game) = games.put(game.id, game)

}