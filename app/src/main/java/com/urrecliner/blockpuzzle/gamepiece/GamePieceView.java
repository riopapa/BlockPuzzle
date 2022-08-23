package com.urrecliner.blockpuzzle.gamepiece;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.urrecliner.blockpuzzle.R;
import com.urrecliner.blockpuzzle.block.BlockDrawParameters;
import com.urrecliner.blockpuzzle.block.BlockTypes;
import com.urrecliner.blockpuzzle.block.ColorBlockDrawer;
import com.urrecliner.blockpuzzle.block.IBlockDrawer;
import com.urrecliner.blockpuzzle.game.GameEngineBuilder;
import com.urrecliner.blockpuzzle.playingfield.PlayingFieldView;

/**
 * 하단에 타일(또는 빈 타일)이 포함된 뷰 구성 요소.
 * 끌어서 놓기 작업은 부분 보기에서 수행됩니다.
 * 4번째 PartView는 일시적으로 부품을 떨어뜨릴 수 있는 주차 공간입니다.
 * 부분은 토큰의 이전 이름입니다. 따라서 PartView.
 **/

@SuppressLint("ViewConstructor")
public class GamePieceView extends View implements IGamePieceView {
    // basic data
    private final int index;
    private final boolean parking;
    private final BlockTypes blockTypes;
    private final BlockDrawParameters p = new BlockDrawParameters();

    // status
    private GamePiece gamePiece = null;
    /** grey wenn Teil nicht dem Quadrat hinzugefügt werden kann, weil kein Platz ist */
    private boolean grey = false; // braucht nicht zu persistiert werden
    private boolean dragMode = false; // wird nicht persistiert

    // Services
    private final IBlockDrawer greyBD;

    // Paints
    private final Paint p_parking = new Paint();

    public GamePieceView(Context context, int index, boolean parking) {
        super(context);
        this.index = index;
        this.parking = parking;

        initParkingAreaColor();

        blockTypes = new BlockTypes(this);
        greyBD = ColorBlockDrawer.byRColor(this, R.color.colorGreyHa, R.color.colorGreyHa, R.color.colorGreyHa);
    }

    @Override
    public void setGamePiece(GamePiece v) {
        endDragMode();
        grey = false;
        gamePiece = v;
        draw();
    }

    @Override
    public GamePiece getGamePiece() {
        return gamePiece;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setGrey(boolean v) {
        grey = v;
        draw();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        p.setCanvas(canvas);
        p.setDragMode(dragMode);
        final float f = getResources().getDisplayMetrics().density;
        p.setF(f);
        int br = PlayingFieldView.w / GameEngineBuilder.blocks; // 60px, auf Handy groß = 36
        if (!dragMode) {
            br /= 2;
        }
        p.setBr(br);
        if (parking && !dragMode) {
            canvas.drawRect(0, 0, br * GamePiece.max * f, br * GamePiece.max * f, p_parking);
        }
        if (gamePiece != null) {
            for (int x = 0; x < GamePiece.max; x++) {
                for (int y = 0; y < GamePiece.max; y++) {
                    int blockType = gamePiece.getBlockType(x, y);
                    if (blockType < 1) {
                        continue;
                    }
                    IBlockDrawer blockDrawer;
                    if (grey) {
                        blockDrawer = greyBD;
                    } else {
                        blockDrawer = blockTypes.getBlockDrawer(blockType);
                    }
                    blockDrawer.draw(x * br, y * br, p);
                }
            }
        }
        super.onDraw(canvas);
    }

    @Override
    public void draw() {
        invalidate();
        requestLayout();
    }

    @Override
    public void startDragMode() {
        dragMode = true;
        setVisibility(View.INVISIBLE);
    }

    @Override
    public void endDragMode() {
        dragMode = false;
        setVisibility(View.VISIBLE);
    }

    @SuppressWarnings("EmptyMethod")
    @Override
    public boolean performClick() {
        // because of warning in MainActivity.initClickListener()
        return super.performClick();
    }

    public void onDragEnter() {
        if (gamePiece == null) {
            p_parking.setColor(getResources().getColor(R.color.colorParkingHoverHa));
            draw();
        }
    }

    public void onDragLeave() {
        initParkingAreaColor();
        draw();
    }

    private void initParkingAreaColor() {
        p_parking.setColor(getResources().getColor(R.color.blue));
    }
}