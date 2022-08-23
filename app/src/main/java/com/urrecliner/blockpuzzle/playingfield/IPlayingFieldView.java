package com.urrecliner.blockpuzzle.playingfield;

import com.urrecliner.blockpuzzle.sound.SoundService;

public interface IPlayingFieldView {

    SoundService getSoundService();

    void setPlayingField(PlayingField playingField);

    void draw();

    void clearRows(FilledRows filledRows, Action action);

    void oneColor();

    void gravitation();
}