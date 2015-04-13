package projectc4.c4.client;


import projectc4.c4.client.fragments.GameFragment;
import projectc4.c4.client.fragments.MatchmakingFragment;
import projectc4.c4.util.User;

import static projectc4.c4.util.C4Constants.*;

/**
 * @author Kalle Bornemark
 */
public class ClientController {
    private GameController gameController;
    private Client client;
    private int player = PLAYER1;
    private int gameMode;
    private GameFragment gameFragment;
    private MatchmakingFragment matchmakingFragment;

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public int getGameMode() {
        return this.gameMode;
    }


    public void setFragment(GameFragment gameFragment) {
        this.gameFragment = gameFragment;
    }

    public void setMatchmakingFragment(MatchmakingFragment matchmakingFragment) {
        this.matchmakingFragment = matchmakingFragment;
    }

    public void connect() {
        client = new Client(this);
//        client.connect("10.2.10.36", 3450);
//        client.connect("10.1.17.111", 3450);
        client.connect("192.168.1.57", 3450); // Kalles hemmadator
//        client.connect("10.1.3.0", 3450);     // Kalles laptop
//        client.connect("172.20.10.2", 3450);    // Kalles laptop #2
//        client.connect("10.2.25.13", 3450);

    }

    public void newOutgoingMove(int column) {
        client.newMove(column);
    }

    public void newIncomingMove(int column) {
        System.out.println("Clientcontrollerns newIncomingMove " + column);
        gameController.newMove(column, true);
    }

    public void startGameUI() {
        matchmakingFragment.startGameUI();
    }

    public void drawTile(int pos, int player) {
        System.out.println("Drawtile: drawTile(" + pos + "," + player + ")");
    }


    public void changeHighlightedPlayer(int player) {
        gameFragment.highlightPlayer(player);
    }

    public void highlightWinnerPlayerStar(int player) {
        gameFragment.highlightWinnerPlayerStar(player);
    }

    public void enableGameButton() {
        if (gameMode == MATCHMAKING) {
            gameFragment.promptRematch();
        } else if (gameMode == LOCAL) {
            gameFragment.setNewGame();
        }
    }

    public void draw() {
//        gameFragment.setTextViewWinner("It's a draw!");
        highlightWinnerPlayerStar(DRAW);
        changeHighlightedPlayer(DRAW);
        enableGameButton();
    }

    public void newGame(int gamemode) {
        gameController.newGame(gamemode);
    }

    public void requestRematch() {
        client.requestRematch();
    }

    public void requestGame(int gamemode) {
        client.requestGame(gamemode);
    }

    public int getOpponent() {
        int opponent;
        if (player == PLAYER1) {
            return PLAYER2;
        }
        return PLAYER1;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
        System.out.println("Player set to: " + this.player);
    }

    public void setPlayerTurn(int player) {
        gameController.setPlayerTurn(player);
    }

    public int getPlayerTurn() {
       return gameController.getPlayerTurn();
    }

    public GameController getGameController() {
        return gameController;
    }

    public void requestUsername(String username) {
        client.requestUsername(username);
    }

    public User getUser() {
        return client.getUser();
    }
}