package projectc4.c4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import static projectc4.c4.util.C4Constants.*;
import projectc4.c4.client.ClientController;


public class MatchmakingActivity extends Activity {
//    private ClientController clientController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
        ClientController.getInstance().setMatchmakingActivity(this);

        Intent intentThatStartedThisActivity = getIntent();
//        this.clientController = (ClientController)intentThatStartedThisActivity.getSerializableExtra("clientController");


        Button buttonFindOpponent = (Button)findViewById(R.id.buttonFindOpponent);
        buttonFindOpponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientController.getInstance().requestGame(MATCHMAKING);
            }
        });
    }

    public void startGameUI() {
        ClientController.getInstance().setGameMode(MATCHMAKING);
        Intent intent = new Intent(MatchmakingActivity.this, GameActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multiplayer, menu);
        return true;
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