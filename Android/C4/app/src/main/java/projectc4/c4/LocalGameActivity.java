package projectc4.c4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.conn.ClientConnectionOperator;

import projectc4.c4.util.C4Color;
import projectc4.c4.client.ClientController;
import static projectc4.c4.util.C4Constants.*;

import java.util.ArrayList;


public class LocalGameActivity extends Activity {
    private GridLayout grd;
    private ArrayList<Button> buttonArrayList = new ArrayList<>();
    private int currentIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClientController.getInstance().setActivity(this);
        ClientController.getInstance().createClientUI();
        initGraphics();
        if(ClientController.getInstance().gameMode == 1) {
            ClientController.getInstance().newGame(MATCHMAKING);
        }else {
            ClientController.getInstance().newGame();
        }


        for (int i = 0; i < buttonArrayList.size(); i++) {
            currentIndex = i;
            buttonArrayList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button button = (Button) v;
                    int col = Integer.parseInt(button.getText().toString());
                    System.out.println("onClick i mainactivity: " + col);
                    ClientController.getInstance().newMove(col);
                }
            });
        }

        drawRoundedCorners();
    }

    public void drawRoundedCorners() {
        for (int i = 0; i < 42; i++) {
            grd.getChildAt(i).setBackground(getDrawable(R.drawable.transparenttile));
        }
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
                if(ClientController.getInstance().gameMode == 0) {
                    ClientController.getInstance().setCurrentPlayer(PLAYER1);
                    ClientController.getInstance().setPlayer(PLAYER1);
                }
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
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
        grd = (GridLayout)findViewById(R.id.gridLayoutCenter); //Länka mitt grid
        //grd.getChildAt(0).setBackgroundColor(C4Color.RED); // get children at
        //grd.getChildAt(1).setBackgroundColor(C4Color.YELLOW);
        //grd.getChildAt(39).setBackgroundColor(C4Color.YELLOW);
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        relativeLayout.setBackgroundColor(C4Color.WHITE);

        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        Button button6 = (Button)findViewById(R.id.button6);

        buttonArrayList.add(button1);
        buttonArrayList.add(button2);
        buttonArrayList.add(button3);
        buttonArrayList.add(button4);
        buttonArrayList.add(button5);
        buttonArrayList.add(button6);

        TextView textViewPlayer1 = (TextView)findViewById(R.id.textViewPlayer1);
        TextView textViewPlayer2 = (TextView)findViewById(R.id.textViewPlayer2);
        TextView textViewVs = (TextView)findViewById(R.id.textViewVs);

        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/msyi.ttf");
        textViewPlayer1.setTypeface(type, Typeface.BOLD);
        textViewPlayer2.setTypeface(type, Typeface.BOLD);

        textViewVs.setTextColor(C4Color.BLACK);
        highlightPlayer(ClientController.getInstance().getCurrentPlayer());
        textViewPlayer1.setTextColor(C4Color.WHITE);
        textViewPlayer2.setTextColor(C4Color.WHITE);

        Button buttonNewgame = (Button)findViewById(R.id.buttonNewGame);
        buttonNewgame.setBackgroundColor(C4Color.BLACK);
        buttonNewgame.setTextColor(C4Color.WHITE);


    }

    public GridLayout getGrid() {
        return this.grd;
    }

    public void setTextViewWinner(String winner) {
        TextView textViewWinner = (TextView)findViewById(R.id.textViewWinner);
        textViewWinner.setText(winner);
        setNewGame();
    }

    public void highlightPlayer(int  player) {
        TextView textViewPlayer1 = (TextView)findViewById(R.id.textViewPlayer1);
        TextView textViewPlayer2 = (TextView)findViewById(R.id.textViewPlayer2);

        if (player == PLAYER1) {
            textViewPlayer1.setBackground(getDrawable(R.drawable.colorred));
            textViewPlayer2.setBackground(getDrawable(R.drawable.coloryellowpressed));
        }
        else if (player == PLAYER2){
            textViewPlayer2.setBackground(getDrawable(R.drawable.coloryellow));
            textViewPlayer1.setBackground(getDrawable(R.drawable.colorredpressed));
        }
    }

    public void drawTile(int pos, int player) {
        if (player == PLAYER1) {
            TextView txt = (TextView)grd.getChildAt(pos);
            txt.setBackground(getDrawable(R.drawable.colorred));
        }else if (player == PLAYER2) {
            TextView txt = (TextView)grd.getChildAt(pos);
            txt.setBackground(getDrawable(R.drawable.coloryellow));
        }


    }

    public void highlightTiles(ArrayList<Integer> pos) {
        for (int i = 0; i < 42; i++) {
            if (grd.getChildAt(i).getBackground().getConstantState().equals(getDrawable(R.drawable.colorred).getConstantState())) {
                grd.getChildAt(i).setBackground(getDrawable(R.drawable.colorredpressed));
            } else if (grd.getChildAt(i).getBackground().getConstantState().equals(getDrawable(R.drawable.coloryellow).getConstantState())) {
                grd.getChildAt(i).setBackground(getDrawable(R.drawable.coloryellowpressed));
            }
        }
        for (int i = 0; i < pos.size(); i++) {
            if (grd.getChildAt(pos.get(i)).getBackground().getConstantState().equals(getDrawable(R.drawable.colorredpressed).getConstantState())) {
                grd.getChildAt(pos.get(i)).setBackground(getDrawable(R.drawable.colorred));
            } else if (grd.getChildAt(pos.get(i)).getBackground().getConstantState().equals(getDrawable(R.drawable.coloryellowpressed).getConstantState())) {
                grd.getChildAt(pos.get(i)).setBackground(getDrawable(R.drawable.coloryellow));
            }
        }
    }

    public void setNewGame() {
        final Button buttonNewGame = (Button)findViewById(R.id.buttonNewGame);
        buttonNewGame.setEnabled(true);
        buttonNewGame.setVisibility(View.VISIBLE);
                for (int i = 0; i < buttonArrayList.size(); i++) {
                    buttonArrayList.get(i).setEnabled(false);
                }
        RelativeLayout relativeLayoutPlayers = (RelativeLayout)findViewById(R.id.relativeLayoutPlayers);
        relativeLayoutPlayers.setVisibility(View.INVISIBLE);
        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientController.getInstance().newGame();
                buttonNewGame.setEnabled(false);
                buttonNewGame.setVisibility(View.INVISIBLE);
                TextView textViewWinner = (TextView)findViewById(R.id.textViewWinner);
                textViewWinner.setText("");
                ClientController.getInstance().newGame();
                RelativeLayout relativeLayoutPlayers = (RelativeLayout)findViewById(R.id.relativeLayoutPlayers);
                relativeLayoutPlayers.setVisibility(View.VISIBLE);
                highlightPlayer(PLAYER1);
                drawRoundedCorners();
            }
        });
    }

    public ArrayList<Button> getButtonArrayList() {
        return this.buttonArrayList;
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
