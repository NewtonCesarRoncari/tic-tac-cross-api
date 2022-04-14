package com.nroncari.tictaccrossapi.exception

class NumberPlayersException : Exception(MESSAGE) {

    companion object Constant {
        const val MESSAGE = "number of players exceeded"
    }
}
