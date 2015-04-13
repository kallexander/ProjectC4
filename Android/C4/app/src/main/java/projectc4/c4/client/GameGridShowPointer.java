package projectc4.c4.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import projectc4.c4.R;

import static projectc4.c4.util.C4Constants.*;

/**
 * @author Kalle Bornemark, Jimmy Maksymiw, Erik Sandgren, Emil Sandgren.
 */
public class GameGridShowPointer extends View {

    private GameController gameController;

    private int pointerPos = -1;
    private int offsetX;
    private int offsetY;
    private int sideOfTile;
    private int player;
    private Paint paint;
    private Bitmap pointerRed;
    private Bitmap pointerYellow;

    public GameGridShowPointer(Context context) {
        super(context);
        init();
    }

    public GameGridShowPointer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameGridShowPointer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init(){
        paint = new Paint();
        pointerRed = BitmapFactory.decodeResource(getResources(), R.drawable.c4_arrow_red);
        pointerYellow = BitmapFactory.decodeResource(getResources(), R.drawable.c4_arrow_yellow);
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
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

    public void changePointerPos(int pointerCol) {
        if (pointerCol == -1){
            this.pointerPos = -1;
        } else {
            this.pointerPos = offsetX + (pointerCol * (GRIDSPACING + sideOfTile)) + ((sideOfTile / 2) - (pointerRed.getWidth() / 2));
            player = gameController.getPlayerTurn();
        }
        updateDisplay();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (gameController != null && pointerPos != -1){
            canvas.save();
            if (player == PLAYER1){
                canvas.drawBitmap(pointerRed, pointerPos, offsetY - pointerRed.getHeight()-GRIDSPACING, paint);
            } else if (player == PLAYER2) {
                canvas.drawBitmap(pointerYellow, pointerPos, offsetY - pointerRed.getHeight()-GRIDSPACING, paint);
            }
            canvas.restore();
        }
    }

    @Override
    protected void onMeasure(int widthMeasuredSpec, int heightMeasuredSpec) {
        int width = MeasureSpec.getSize(widthMeasuredSpec);
        int height = MeasureSpec.getSize(heightMeasuredSpec);
        if (gameController != null) {
            // Räkna ut passande storlek för brickan
            sideOfTile = Math.min((((width - GRIDSPACING) / gameController.getBoardWidth()) - GRIDSPACING),
                    (((height - GRIDSPACING) / gameController.getBoardHeight()) - GRIDSPACING));

            // Rita gameBoard mitt i canvasen i x-led
            offsetX = (width - (gameController.getBoardWidth() * (sideOfTile + GRIDSPACING) - GRIDSPACING)) / 2;

            // Rita gameBoard längst ner på canvasen i y-led
            offsetY = (height - (gameController.getBoardHeight() * (sideOfTile + GRIDSPACING)));
        }
//        System.out.println("GGA - width: " + width + " height: " + height + "\nsideOfTile: " + sideOfTile);
        setMeasuredDimension(width, height);
    }
}