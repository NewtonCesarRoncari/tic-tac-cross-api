package com.nroncari.tictaccrossapi.exception

class NotFoundException : Exception(MESSAGE) {

    companion object Constant {
        private const val MESSAGE = "Game not found"
    }
}
