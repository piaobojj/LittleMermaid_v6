package project.littlemermaid_v6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Firebase aRef;
    private Button launchBtn;
    private Button educationBtn, historyBtn, miscBtn;
    private String toastMessage = "";

    /* Data Retrieve from DataBase*/
    ArrayList<CategoryData> arrayCate = new ArrayList<>();

    /* Data Retrieve from DataBase*/
    ArrayList<Integer> arrayEdu= new ArrayList<>();
    /* Data Retrieve from DataBase*/
    ArrayList<Integer> arrayHis= new ArrayList<>();
    /* Data Retrieve from DataBase*/
    ArrayList<Integer> arrayMisc= new ArrayList<>();

    /*Selection string array */
    ArrayList<ArrayList<Integer>> cateSelected = new ArrayList<ArrayList<Integer>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayCate = new ArrayList<CategoryData>();

        arrayEdu.add(0); arrayEdu.add(1); arrayEdu.add(2); arrayEdu.add(3);
        arrayHis.add(0); arrayHis.add(0); arrayHis.add(1); arrayHis.add(2);
        arrayMisc.add(1); arrayMisc.add(2); arrayMisc.add(3); arrayMisc.add(0);


        launchBtn = (Button) findViewById(R.id.launchButton);
        miscBtn = (Button) findViewById(R.id.miscBtn);
        educationBtn = (Button) findViewById(R.id.educationBtn);
        historyBtn = (Button) findViewById(R.id.historyBtn);


        aRef = new Firebase("https://littlemermaid.firebaseio.com/dictionary/");


        aRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                GenericTypeIndicator<Map<String, Object>> m = new GenericTypeIndicator<Map<String, Object>>(){};
                Map<String, Object> map = dataSnapshot.getValue(m);

                String lemma = (String) map.get("lemma");
                String NGram = (String) map.get("NGram");

                int Education = (int) map.get("ACAD:Education");
                int History = (int) map.get("ACAD:History");
                int Misc = (int) map.get("ACAD:Misc");

                //arrayCate.add(new CategoryData(lemma,NGram,Education,History,Misc));

                //Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                //int MAGChild = map.get("MAG:Children");
               // int MAGEntertain = map.get("MAG:Entertain");
                //int MAGFinancial = map.get("MAG:Financial");
                //arrayMag.add(new CategoryData(MAGAa,MAGChild,MAGEntertain,MAGFinancial));
                //System.out.println(arrayCate.toString());
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

        /*
        for(int i = 0 ; i < arrayEdu.size(); i++) {
            System.out.println("------" + arrayEdu.get(i));
        }*/

        educationBtn.setOnClickListener(this);
        historyBtn.setOnClickListener(this);
        miscBtn.setOnClickListener(this);

        launchBtn.setOnClickListener(this);

        /*
        magBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent i = new Intent(MainActivity.this, ReceiveActivity.class);
                i.putExtra("Magazine",arrayCate);
                startActivity(i);
            }
        });*/


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.educationBtn:
                cateSelected.add(arrayEdu);
                break;
            case R.id.historyBtn:
                cateSelected.add(arrayHis);
                break;
            case R.id.miscBtn:
                cateSelected.add(arrayMisc);
                break;
            case R.id.launchButton:

                if(cateSelected.size() == 0){

                    Toast.makeText(getApplicationContext(), "Please selected category", Toast.LENGTH_SHORT).show();

                }else {

                    //send the value to the receive
                    Intent intent = new Intent(MainActivity.this, ReceiveActivity.class);
                    intent.putExtra("CateKey",cateSelected);
                    //intent.putIntegerArrayListExtra("CateKey", cateSelected);
                    startActivity(intent);

                    //testing the index value
                    for(int i = 0 ; i < cateSelected.size(); i++) {
                        System.out.println("-Main activity--" + cateSelected.get(i));
                    }
                    //reset the string
                    cateSelected.clear();

                }

                break;
            default:
                break;
        }
    }


    /*
    private void categoryDisplay() {
        try{

            //create the 9 X 9 buttons on the table layout with correspond word
            for(int index = 0; index < 9; index++){
                TableLayout table = (TableLayout) findViewById( R.id.buttonLayout );

                int buttonsInRow = 0;
                int numRows = table.getChildCount();
                TableRow row = null;
                if( numRows > 0 ){
                    row = (TableRow) table.getChildAt( numRows - 1 );
                    buttonsInRow = row.getChildCount();
                }

                if( numRows == 0 || buttonsInRow == 3 ){
                    row = new TableRow( this );
                    table.addView( row );
                    buttonsInRow = 0;
                }
                if( buttonsInRow < 3 ){
                    ToggleButton bb = new ToggleButton( this );
                    row.addView( bb, 300, 200 );
                    //bb.setText(Category [index]);
                    bb.setText(Category [index]);
                    bb.setTextOff(Category [index]);
                    bb.setTextOn(Category [index]);
                    //bb.setText(buttonString.get(index));
                }
            }

        }catch (NullPointerException e){

        }
    }
*/

}


            /*
            aRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Map<String, String>map = dataSnapshot.getValue(Map.class);
                    String name = map.get("lemma");
                    Log.v("The Value", "-------------Data I got:  " + name);
                    //print all the object in the database
                    //Log.v("The Value", "Data: " + dataSnapshot.getValue());
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });
            */