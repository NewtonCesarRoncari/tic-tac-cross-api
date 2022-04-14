package com.nroncari.tictaccrossapi.controller.dto

import java.util.*

class StandardError(
    val message: String,
    val timestamp: Long = Calendar.getInstance().timeInMillis
)