package com.urrecliner.blockpuzzle.game

import com.urrecliner.blockpuzzle.gamepiece.IGamePieceView
import com.urrecliner.blockpuzzle.global.messages.MessageFactory
import com.urrecliner.blockpuzzle.playingfield.Action
import com.urrecliner.blockpuzzle.playingfield.IPlayingFieldView

/**
 * Alle Zugriffe vom Game (Controller) auf die View Schicht
 */
interface IGameView {

    // init phase
    fun getPlayingFieldView(): IPlayingFieldView

    // init phase (and internal use)
    fun getGamePieceView(index: Int): IGamePieceView

    fun showScore(text: String)

    fun showMoves(text: String)

    fun showPlanetNumber(number: Int)

    fun showTerritoryName(resId: Int)

    fun shake()

    fun playSound(number: Int)

    fun getSpecialAction(specialState: Int): Action

    fun getMessages(): MessageFactory
}