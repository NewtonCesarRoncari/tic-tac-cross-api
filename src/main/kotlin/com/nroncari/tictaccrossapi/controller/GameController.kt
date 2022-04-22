package com.nroncari.tictaccrossapi.controller

import com.nroncari.tictaccrossapi.controller.dto.ConnectRequest
import com.nroncari.tictaccrossapi.model.Game
import com.nroncari.tictaccrossapi.model.GamePlay
import com.nroncari.tictaccrossapi.service.GameService
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/game")
class GameController(private val gameService: GameService, private val simpleMessaging: SimpMessagingTemplate) {

    @GetMapping("/start")
    fun start(): ResponseEntity<Game> {
        return ResponseEntity.ok(gameService.createGame())
    }

    @PostMapping("/connect")
    fun connect(@RequestBody request: ConnectRequest): ResponseEntity<Game> {
        val game = gameService.connectToGame(request.gameId!!)
        simpleMessaging.convertAndSend("/topic/game-progress/connected", game)
        return ResponseEntity.ok(game)
    }

    @PostMapping("/gameplay")
    fun gamePlay(@RequestBody request: GamePlay): ResponseEntity<Game> {
        val game = gameService.gamePlay(request)
        simpleMessaging.convertAndSend("/topic/game-progress/${game.id.substring(0,8)}", game)
        return ResponseEntity.ok(game)
    }

    @PostMapping("/playagain")
    fun playAgain(@RequestBody request: GamePlay): ResponseEntity<Game> {
        val game = gameService.playAgain(request)
        simpleMessaging.convertAndSend("/topic/game-progress/${game.id.substring(0,8)}", game)
        return ResponseEntity.ok(game)
    }
}