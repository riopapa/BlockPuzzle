package com.urrecliner.blockpuzzle.game

import com.urrecliner.blockpuzzle.block.BlockTypes
import com.urrecliner.blockpuzzle.game.place.IPlaceAction
import com.urrecliner.blockpuzzle.gamedefinition.OldGameDefinition
import com.urrecliner.blockpuzzle.gamepiece.Holders
import com.urrecliner.blockpuzzle.gamepiece.INextGamePiece
import com.urrecliner.blockpuzzle.gamestate.GameState
import com.urrecliner.blockpuzzle.playingfield.PlayingField
import com.urrecliner.blockpuzzle.playingfield.gravitation.GravitationData

/**
 * Game engine model
 */
data class GameEngineModel(
    // Immutable properties only!
    val blocks: Int,
    val blockTypes: BlockTypes,
    val view: IGameView,
    val gs: GameState,
    val definition: OldGameDefinition,
    val playingField: PlayingField,
    val holders: Holders,
    val placeActions: List<IPlaceAction>,
    val gravitation: GravitationData,
    val nextGamePiece: INextGamePiece
) {

    fun save() {
        val ss = gs.get()
        playingField.save(ss)
        gravitation.save(ss)
        holders.save(ss)
        gs.save()
    }
}