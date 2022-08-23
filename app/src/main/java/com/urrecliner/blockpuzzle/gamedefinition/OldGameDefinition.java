package com.urrecliner.blockpuzzle.gamedefinition;

import com.urrecliner.blockpuzzle.game.TopButtonMode;
import com.urrecliner.blockpuzzle.gamepiece.INextGamePiece;
import com.urrecliner.blockpuzzle.gamepiece.RandomGamePiece;
import com.urrecliner.blockpuzzle.gamestate.GameState;
import com.urrecliner.blockpuzzle.gamestate.Spielstand;

/**
 * Game definition for old game
 */
public class OldGameDefinition {

    public int getGravitationStartRow() {
        return 5;
    }

    public int getGamePieceBlocksScoreFactor() {
        return 1;
    }

    public int getHitsScoreFactor() {
        return 10;
    }

    public boolean isRowsAdditionalBonusEnabled() {
        return true;
    }

    public boolean gameGoesOnAfterWonGame() {
        return true;
    }

    public INextGamePiece createNextGamePieceGenerator(GameState gs) {
        return new RandomGamePiece();
    }

    public boolean isWonAfterNoGamePieces(Spielstand ss) {
        return true;
    }

    public TopButtonMode getTopButtonMode() {
        return TopButtonMode.NEW_GAME;
    }

    public boolean isCrushAllowed() {
        return false;
    }
}