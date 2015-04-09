package projectc4.c4.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import projectc4.c4.R;
import projectc4.c4.client.GameController;
import projectc4.c4.client.GameGridAnimation;
import projectc4.c4.client.GameGridForeground;
import projectc4.c4.client.GameGridView;
import projectc4.c4.util.C4Color;
import projectc4.c4.client.ClientController;
import static projectc4.c4.util.C4Constants.*;


public class GameActivity extends Activity {
    private ClientController clientController;
    private int gameMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        clientController = ClientController.getInstance();

        GameGridForeground gameGridForeground = (GameGridForeground)findViewById(R.id.gameGridForeground);
        GameGridView gameGridView = (GameGridView)findViewById(R.id.gameGridView);
        gameGridView.setFocusable(true);
        gameGridView.addViews((GameGridAnimation)findViewById(R.id.gameGridAnimation),gameGridForeground);
        GameController gameController = new GameController(clientController, gameGridView);
        clientController.setGamecontroller(gameController);
        gameGridForeground.setGameController(gameController);



        clientController.setActivity(this);
        System.out.println(clientController.getPlayerTurn());
        gameMode = clientController.getGameMode();
        initGraphics(); // !! Viktigt att denna körs innan newGame() som kommer här under !!
        clientController.newGame(gameMode);

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void initGraphics() {
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        relativeLayout.setBackgroundColor(C4Color.WHITE);

        TextView textViewPlayer1 = (TextView)findViewById(R.id.textViewPlayer1);
        TextView textViewPlayer2 = (TextView)findViewById(R.id.textViewPlayer2);
        TextView textViewVs = (TextView)findViewById(R.id.textViewVs);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/msyi.ttf");
        textViewPlayer1.setTypeface(type, Typeface.BOLD);
        textViewPlayer2.setTypeface(type, Typeface.BOLD);

        textViewVs.setTextColor(C4Color.BLACK);
        highlightPlayer(clientController.getPlayerTurn());
        textViewPlayer1.setTextColor(C4Color.WHITE);
        textViewPlayer2.setTextColor(C4Color.WHITE);

        Button buttonNewgame = (Button)findViewById(R.id.buttonNewGame);
        buttonNewgame.setBackground(getDrawable(R.drawable.altbutton));
        buttonNewgame.setTypeface(type, Typeface.BOLD);
        buttonNewgame.setTextColor(C4Color.WHITE);

        Button buttonRematch = (Button)findViewById(R.id.buttonRematch);
        buttonNewgame.setBackground(getDrawable(R.drawable.altbutton));
        buttonNewgame.setTypeface(type, Typeface.BOLD);
        buttonRematch.setTextColor(C4Color.WHITE);
    }

    public void setTextViewWinner(final String winner) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                TextView textViewWinner = (TextView)findViewById(R.id.textViewWinner);
//                textViewWinner.setText(winner);

            }
        });
    }

    public void highlightPlayer(final int player) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView textViewPlayer1 = (TextView)findViewById(R.id.textViewPlayer1);
                TextView textViewPlayer2 = (TextView)findViewById(R.id.textViewPlayer2);

                if (player == PLAYER1) {
                    textViewPlayer1.setBackground(getDrawable(R.drawable.colorred));
                    textViewPlayer2.setBackground(getDrawable(R.drawable.coloryellowpressed));
                } else if (player == PLAYER2){
                    textViewPlayer2.setBackground(getDrawable(R.drawable.coloryellow));
                    textViewPlayer1.setBackground(getDrawable(R.drawable.colorredpressed));
                }
            }
        });
    }


    public void promptRematch() {
        final Button buttonRematch = (Button)findViewById(R.id.buttonRematch);
        buttonRematch.setEnabled(true);
        buttonRematch.setVisibility(View.VISIBLE);
        buttonRematch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientController.requestRematch();
            }
        });
    }

    public void newRematch () {
        final Button buttonRematch = (Button)findViewById(R.id.buttonRematch);
        clientController.newGame(MATCHMAKING);
        buttonRematch.setEnabled(false);
        buttonRematch.setVisibility(View.INVISIBLE);
//        TextView textViewWinner = (TextView)findViewById(R.id.textViewWinner);
//        textViewWinner.setText("");
        clientController.newGame(MATCHMAKING);
        RelativeLayout relativeLayoutPlayers = (RelativeLayout)findViewById(R.id.relativeLayoutPlayers);
        relativeLayoutPlayers.setVisibility(View.VISIBLE);
        highlightPlayer(clientController.getPlayerTurn());
    }

    public void setNewGame() {
        final Button buttonNewGame = (Button)findViewById(R.id.buttonNewGame);
        buttonNewGame.setEnabled(true);
        buttonNewGame.setVisibility(View.VISIBLE);
        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientController.newGame(LOCAL);
                buttonNewGame.setEnabled(false);
                buttonNewGame.setVisibility(View.INVISIBLE);
//                TextView textViewWinner = (TextView)findViewById(R.id.textViewWinner);
//                textViewWinner.setText("");
//                clientController.newGame(LOCAL);
                RelativeLayout relativeLayoutPlayers = (RelativeLayout)findViewById(R.id.relativeLayoutPlayers);
                relativeLayoutPlayers.setVisibility(View.VISIBLE);
                highlightPlayer(PLAYER1);
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
