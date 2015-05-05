package projectc4.c4.client;

import java.util.ArrayList;

public class Powerup {
    private GameController gameController;
    private GameGridView gameGridView;


    public Powerup(GameController gameController, GameGridView gameGridView) {
        this.gameController = gameController;
        this.gameGridView = gameGridView;
    }

    public void powerupTime() {
       gameController.startTimer(10);
    }

    public void powerupColorblind() {
        gameGridView.setNoColor(true);
    }

    public void powerupExtraTurn() {
        gameController.setExtraTurn(true);
    }

    public void powerupBomb(int playedRow, int playedCol) {
        ArrayList<Integer> posList = new ArrayList<Integer>();

        for (int i = playedRow; i < gameController.getGameBoard().length; i++) {
            posList.add(i);
            gameController.getGameBoard()[i][playedCol] = 0;
            if(gameController.getColSize()[playedCol] > 0) {
                gameController.getColSize()[playedCol]--;
            }
        }
        gameGridView.setBombTiles(true, posList, playedCol);
    }
}
