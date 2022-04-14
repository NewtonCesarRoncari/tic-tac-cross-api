package com.nroncari.tictaccrossapi.exception

class GameAlreadyFinishedException() : Exception(MESSAGE) {

    companion object Constant {
        const val MESSAGE = "Game is already finished"
    }
}