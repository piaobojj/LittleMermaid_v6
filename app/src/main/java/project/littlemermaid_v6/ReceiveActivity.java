package project.littlemermaid_v6;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.R.attr.x;
import static android.R.id.list;
import static android.media.CamcorderProfile.get;

public class ReceiveActivity extends Activity implements View.OnClickListener {

    private Button nextBtn, returnBtn;
    private TextView test;
    private Firebase aRef;

    private static final int ROWS = 2;
    private static final int COLS = 3;

    ArrayList<Integer> cateSelected = new ArrayList<Integer>();

    // Format Store ---- < Lemma , Category : Key >
    HashMap<String, Integer> lemmaKey = new HashMap<String, Integer>();

    // Format Store ---- < Category , < Lemma , Category : Key > >
    HashMap<String, HashMap<String, Integer>> CateLemmaKey = new HashMap<String, HashMap<String, Integer>>();

    public String [] fName = { "File 1", "File 2", "Roflcopters","fun","show","File 4","File 5","File 6"};
    public int indexPoint = 0;
    GridView gv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        //gv = (GridView) findViewById(R.id.gv);
        nextBtn = (Button)findViewById(R.id.nextBtn);

        for(int index = 0; index < fName.length; index++){
            TableLayout table = (TableLayout) findViewById( R.id.btn);

            int buttonsInRow = 0;
            int numRows = table.getChildCount();
            TableRow row = null;
            if( numRows > 0 ){
                row = (TableRow) table.getChildAt( numRows - 1 );
                buttonsInRow = row.getChildCount();
            }

            if( numRows == 0 || buttonsInRow == 2 ){
                row = new TableRow( this );
                table.addView( row );
                buttonsInRow = 0;
            }
            if( buttonsInRow < 2 ){
                Button bb = new Button( this );
                row.addView( bb, 500, 200 );
                //bb.setText("0");
                bb.setText(fName[index]);
            }
        }





        //GridView gridview = (GridView) findViewById(R.id.gv);
        //gridview.setAdapter(new ButtonAdapter(this));


       // dynamicBtnDisplay();

        /*
        test = (TextView) findViewById(R.id.test);

        Intent intent = getIntent();
        cateSelected = intent.getIntegerArrayListExtra("CateKey");
        System.out.println("-From other activity---" + cateSelected);


        nextBtn = (Button) findViewById(R.id.nextBtn);
        returnBtn = (Button) findViewById(R.id.returnBtn);
        nextBtn.setOnClickListener(this);
        returnBtn.setOnClickListener(this);

        aRef = new Firebase("https://littlemermaid.firebaseio.com/dictionary/");

        aRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                GenericTypeIndicator<Map<String, Object>> m = new GenericTypeIndicator<Map<String, Object>>(){};
                Map<String, Object> map = dataSnapshot.getValue(m);

                //word
                String lemma = (String) map.get("lemma");

                //pull out the user selected
                for(int i = 0; i<cateSelected.size();i++){

                    int number = cateSelected.get(i);
                    if(number == 1){
                        int Education = (int) map.get("ACAD:Education");
                        //remove zero value
                        if(Education != 0){
                            lemmaKey.put(lemma,Education);
                            //CateLemmaKey.put("Education",lemmaKey);
                            //Log.v("Education---", lemmaKey.toString());
                        }
                    }else if(number == 2){
                        int History = (int) map.get("ACAD:History");
                        //remove zero value
                        if(History != 0){
                            lemmaKey.put(lemma,History);
                            //CateLemmaKey.put("History",lemmaKey);
                        }
                    }else if(number == 3){
                        int Misc = (int) map.get("ACAD:Misc");
                        //remove zero value
                        if(Misc != 0){
                            lemmaKey.put(lemma,Misc);
                            //CateLemmaKey.put("Misc",lemmaKey);
                        }
                    }
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
*/
    }

    /*
    private void dynamicBtnDisplay(){

        TableLayout table = (TableLayout) findViewById(R.id.btnLayout);
        for(int row = 0; row < ROWS ;row++){
            TableRow tableRow = new TableRow(this);

            table.addView(tableRow);
            for(int col = 0;col < COLS ;col++){
                Button button = new Button(this);

                button.setText(text.get(1));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        btnClick();
                    }
                });

                tableRow.addView(button);
            }

        }
    }

    private void btnClick(){

    }
*/

    /*
    private void dynamicBtnDisplay(int i, int size) {
        for(int index = i; index < size; index++){
            TableLayout table = (TableLayout) findViewById( R.id.btn);

            int buttonsInRow = 0;
            int numRows = table.getChildCount();
            TableRow row = null;
            if( numRows > 0 ){
                row = (TableRow) table.getChildAt( numRows - 1 );
                buttonsInRow = row.getChildCount();
            }

            if( numRows == 0 || buttonsInRow == 2 ){
                row = new TableRow( this );
                table.addView( row );
                buttonsInRow = 0;
            }
            if( buttonsInRow < 2 ){
                Button bb = new Button( this );
                row.addView( bb, 500, 200 );
                //bb.setText("0");
                //bb.setText(word.get(index));
            }
        }
    }*/


    @Override
    public void onClick(View view) {
        //define the button that has been clicked and perform the corresponding user selected
        switch (view.getId()){
            //close return the launch the activity
            case R.id.nextBtn:
                Toast.makeText(getApplicationContext(), "No more word", Toast.LENGTH_SHORT).show();
                break;
            case R.id.returnBtn:
                finish();
                break;
            default:
                break;
        }
    }









}




