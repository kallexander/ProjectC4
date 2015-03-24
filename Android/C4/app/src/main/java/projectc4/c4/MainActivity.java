package projectc4.c4;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private GridLayout grd;
    private ArrayList<Button> buttonArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ClientController(this);
        initGraphics(); // get children at
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

   public void initGraphics() {
        grd = (GridLayout)findViewById(R.id.grid); //Länka mitt grid
        grd.getChildAt(7).setBackgroundColor(C4Color.RED); // get children at
        grd.getChildAt(8).setBackgroundColor(C4Color.YELLOW);
        grd.getChildAt(39).setBackgroundColor(C4Color.YELLOW);
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        relativeLayout.setBackgroundColor(C4Color.WHITE);

        Button button = (Button)findViewById(R.id.button);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        Button button6 = (Button)findViewById(R.id.button6);

        button.setBackgroundColor(C4Color.BLACK);
        button2.setBackgroundColor(C4Color.BLACK);
        button3.setBackgroundColor(C4Color.BLACK);
        button4.setBackgroundColor(C4Color.BLACK);
        button5.setBackgroundColor(C4Color.BLACK);
        button6.setBackgroundColor(C4Color.BLACK);

        buttonArrayList.add(button);
        buttonArrayList.add(button2);
        buttonArrayList.add(button3);
        buttonArrayList.add(button4);
        buttonArrayList.add(button5);
        buttonArrayList.add(button6);

    }

    public GridLayout getGrid() {
        return this.grd;
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
