package project.littlemermaid_v6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.core.view.View;

import java.util.ArrayList;
import java.util.Map;

import static android.R.attr.onClick;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity{

    private Firebase aRef;
    private Button launchBtn;
    private Button magBtn, newsBtn;
    private String toastMessage = "";

    ArrayList<CategoryData> arrayMag = new ArrayList<CategoryData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launchBtn = (Button) findViewById(R.id.nextButton);

        magBtn = (Button) findViewById(R.id.magBtn);
        newsBtn = (Button) findViewById(R.id.newsBtn);

        aRef = new Firebase("https://littlemermaid.firebaseio.com/dictionary/");

        aRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Map<String, Integer> map = dataSnapshot.getValue(Map.class);

                int MAGAa= map.get("MAG:Afric-Amer");
                int MAGChild = map.get("MAG:Children");
                int MAGEntertain = map.get("MAG:Entertain");
                int MAGFinancial = map.get("MAG:Financial");

                arrayMag.add(new CategoryData(MAGAa,MAGChild,MAGEntertain,MAGFinancial));

                //System.out.println(arrayMag.size());
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

        magBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent i = new Intent(MainActivity.this, ReceiveActivity.class);
                i.putExtra("Magazine",arrayMag);
                startActivity(i);
            }
        });



        launchBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                if(toastMessage == ""){
                    Toast.makeText(getApplicationContext(), "No Category", Toast.LENGTH_SHORT).show();
                }else{

                }


            }
        });


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