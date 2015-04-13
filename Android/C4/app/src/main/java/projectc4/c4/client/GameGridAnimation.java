package projectc4.c4.client;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import static projectc4.c4.util.C4Color.*;
import static projectc4.c4.util.C4Constants.*;

/**
 * @author Jimmy Maksymiw
 */
public class GameGridAnimation extends View {

//    private int colorPlayer1 = 0xFFF67E59, colorPlayer2 = 0xFFECD06C;


    private GameGridView gameGridView;

    private RectF rect = null;
    private boolean animateNewMove = false;
    private boolean animatePointer = false;
    private int offsetX;
    private int offsetY;
    private int sideOfTile;
    private int gridSpacing;
    private int col;
    private int player = PLAYER2;
    private int rowStop;
    private int currentPosY;
    private int pointerPos;

    public GameGridAnimation(Context context) {
        super(context);
    }

    public GameGridAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameGridAnimation(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addView(GameGridView gameGridView){
        this.gameGridView = gameGridView;
    }

    public void setSize(int offsetX, int offsetY, int sideOfTile, int  gridSpacing){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.sideOfTile = sideOfTile;
        this.gridSpacing = gridSpacing;
    }

    private void updateDisplay() {
        this.post(new Runnable() {
            public void run() {
                superInvalidate();
            }
        });
    }

    private void superInvalidate() {
        super.invalidate();
    }

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();

        if (animatePointer) {
            if (player == PLAYER1) {
                paint.setColor(YELLOW);
            } else if (player == PLAYER2) {
                paint.setColor(RED);
            }
            rect = new RectF(pointerPos-(sideOfTile/2), offsetY-sideOfTile, (sideOfTile + pointerPos)-(sideOfTile/2), offsetY);
            canvas.drawRoundRect(rect, 20, 20, paint);
            animatePointer = false;

        } else if (animateNewMove) {
            if (player == PLAYER1) {
                paint.setColor(RED);
            } else if (player == PLAYER2) {
                paint.setColor(YELLOW);
            }

            //Todo uträkning i temporära variabler istället
            rect = new RectF(col, currentPosY, (sideOfTile + col), (sideOfTile + currentPosY));
            canvas.drawRoundRect(rect, 20, 20, paint);

            if (currentPosY < rowStop*(sideOfTile+ gridSpacing) + offsetY  ){
                currentPosY+=25;
                if (currentPosY >= rowStop*(sideOfTile+ gridSpacing) + offsetY ){
                    currentPosY = rowStop*(sideOfTile+ gridSpacing)+ offsetY;
                    animateNewMove = false;
                    gameGridView.updateDisplay();
//                    gameGridView.setElement(rowStop, colStart, player);
                }
                updateDisplay();
            }
        }


    }

    public void animatePointer(int pointerPos){
        this.pointerPos = pointerPos;
        this.animatePointer = true;
        updateDisplay();
    }


    public void animateNewMove(int colStart, int rowStop, int player){
        this.col = ((colStart) * (sideOfTile + gridSpacing)) + offsetX;
        this.rowStop = rowStop;
        this.player = player;
        this.currentPosY = offsetY-(sideOfTile/2);
        this.animateNewMove = true;

        updateDisplay();
    }

    @Override
    protected void onMeasure(int widthMeasuredSpec, int heightMeasuredSpec) {
        int w = MeasureSpec.getSize(widthMeasuredSpec);
        int h = MeasureSpec.getSize(heightMeasuredSpec);
        System.out.println("ggAnimation - w: " + w + " h: " + h );
        setMeasuredDimension(w, h);
    }
}
