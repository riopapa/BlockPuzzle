package com.urrecliner.blockpuzzle.game.stonewars.deathstar;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import com.urrecliner.blockpuzzle.game.GameEngineModel;
import com.urrecliner.blockpuzzle.game.place.DoNothingPlaceAction;
import com.urrecliner.blockpuzzle.game.place.IPlaceAction;
import com.urrecliner.blockpuzzle.game.stonewars.StoneWarsGameEngineBuilder;
import com.urrecliner.blockpuzzle.game.stonewars.deathstar.place.DeathStarCheck4VictoryPlaceAction;
import com.urrecliner.blockpuzzle.game.stonewars.place.Check4VictoryPlaceAction;
import com.urrecliner.blockpuzzle.gamestate.SpielstandDAO;

/**
 * Initialization of DeathStarGameEngine
 */
public class DeathStarGameEngineBuilder extends StoneWarsGameEngineBuilder {
    private static final SpielstandDAO dao = new SpielstandDAO();

    // create data ----

    @NonNull
    @Override
    protected IPlaceAction getDetectOneColorAreaAction() {
        // no OneColor bonus
        return new DoNothingPlaceAction();
    }

    @NotNull
    @Override
    protected Check4VictoryPlaceAction getCheck4VictoryPlaceAction() {
        return new DeathStarCheck4VictoryPlaceAction(); // Bei Sieg die Holders clearen.
    }

    // create game engine ----

    @NotNull
    @Override
    protected DeathStarGameEngine createGameEngine(GameEngineModel model) {
        return new DeathStarGameEngine(model);
    }

    // new game ----

    @Override
    protected void doNewGame() {
        super.doNewGame();
        gravitation.setFirstGravitationPlayed(true);
    }

    @Override
    protected void initNextGamePieceForNewGame() {
        nextGamePiece.load();
    }

    // load game ----

    @Override
    protected void loadGame() {
        super.loadGame();
        if (holders.is123Empty()) {
            gameEngine.offer(true/*hier ausnahmsweise true!*/);
        }
        gameEngine.checkGame(); // checkGame must be after offer
    }

    @Override
    protected void checkGameAfterLoad() { //
    }
}