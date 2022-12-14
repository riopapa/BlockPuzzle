package com.urrecliner.blockpuzzle.game.stonewars.deathstar;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.urrecliner.blockpuzzle.R;
import com.urrecliner.blockpuzzle.cluster.ClusterView;
import com.urrecliner.blockpuzzle.cluster.SpaceObjectStates;
import com.urrecliner.blockpuzzle.planet.AbstractSpaceObject;
import com.urrecliner.blockpuzzle.playingfield.QPosition;

/**
 * Weltraumnebel
 */
public class SpaceNebula extends AbstractSpaceObject {
    private final List<QPosition> dots = new ArrayList<>();
    public static Paint paint;

    public SpaceNebula(int number, int soX, int soY) {
        super(number, soX, soY);

        // Generate static nebula
        // TO-DO Naja, so wie ich das gemalt habe, sieht's eher nach einem Asteroidenfeld aus. Ich muss mich mal entscheiden, ob Nebel oder "AsteroidField".
        //      Die Frage ist, ob ich einen Nebel gezeichnet bekomme. Das muss wie mit Spraydose gemalt aussehen.
        //      Es ist typischer, dass ein Nebel so einen Sprung auslöst. Also irgendwann nen Nebel sprayen.
        //      https://stackoverflow.com/questions/11938632/creating-a-spray-effect-on-touch-draw-in-android
        final Random random = new Random();
        final int w = ClusterView.w * 6;
        final int h = ClusterView.w * 2;
        for (int i = 0; i < 100; i++) {
            dots.add(new QPosition(random.nextInt(w), random.nextInt(h)));
        }
    }

    @Override
    public String getId() {
        return "C" + getClusterNumber() + "_" + getNumber();
    }

    @Override
    public int getRadius() {
        return 20;
    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    @Override
    public boolean isDataExchangeRelevant() {
        return false;
    }

    @Override
    public boolean isShowCoordinates() {
        return false;
    }

    @Override
    public void draw(Canvas canvas, float f, SpaceObjectStates info) {
        for (QPosition dot : dots) {
            canvas.drawCircle(getX() * ClusterView.w * f + dot.getX(), getY() * ClusterView.w * f + dot.getY(), 3, paint);
        }
    }

    @Override
    public int getName() {
        return R.string.empty; // name not needed
    }
}