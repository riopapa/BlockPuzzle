package com.urrecliner.blockpuzzle.playingfield.gravitation;

import com.urrecliner.blockpuzzle.game.GameEngineBuilder;
import com.urrecliner.blockpuzzle.game.GameEngineInterface;
import com.urrecliner.blockpuzzle.playingfield.Action;
import com.urrecliner.blockpuzzle.playingfield.PlayingField;
import com.urrecliner.blockpuzzle.playingfield.QPosition;

public class GravitationAction implements Action {
    private final GravitationData data;
    private final GameEngineInterface possibleMovesChecker;
    private final PlayingField playingField;
    private final int startRow;

    public GravitationAction(GravitationData gravitationData, GameEngineInterface possibleMovesChecker, PlayingField playingField, int startRow) {
        this.data = gravitationData;
        this.possibleMovesChecker = possibleMovesChecker;
        this.playingField = playingField;
        this.startRow = startRow;
    }

    @Override
    public void execute() {
        final int blocks = GameEngineBuilder.blocks;
        for (int i = startRow; i >= 1; i--) {
            if (hasToBeRemoved(i) && data.getRows().contains(blocks - i)) {
                // Row war voll und wurde geleert -> Gravitation auslösen
                playingField.gravitation(blocks - i, !data.isFirstGravitationPlayed());
                data.setFirstGravitationPlayed(true);
            }
        }
        data.clear(); // clear after use
        possibleMovesChecker.checkIfNoMoveIsPossible();
    }

    private boolean hasToBeRemoved(int i) {
        final int blocks = GameEngineBuilder.blocks;
        for (QPosition k : data.getExclusions()) {
            if (k.getY() == blocks - i) {
                return false;
            }
        }
        return true;
    }
}