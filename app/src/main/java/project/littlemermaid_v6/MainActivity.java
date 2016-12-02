package project.littlemermaid_v6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.R.attr.id;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Firebase aRef;
    private Button launchBtn;
    private Button educationBtn, historyBtn, miscBtn;

    /*Selection*/
    ArrayList<Integer> cateSelect = new ArrayList<Integer>();

    public String [] fName = { "File 1", "File 2", "Roflcopters","hahah"};
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button get layout id
        launchBtn = (Button) findViewById(R.id.launchButton);
        miscBtn = (Button) findViewById(R.id.miscBtn);
        educationBtn = (Button) findViewById(R.id.educationBtn);
        historyBtn = (Button) findViewById(R.id.historyBtn);

        //event handler
        educationBtn.setOnClickListener(this);
        historyBtn.setOnClickListener(this);
        miscBtn.setOnClickListener(this);
        launchBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.educationBtn:
                cateSelect.add(1);
                break;
            case R.id.historyBtn:
                cateSelect.add(2);
                break;
            case R.id.miscBtn:
                cateSelect.add(3);
                break;
            case R.id.launchButton:

                if(cateSelect.size() == 0){

                    Toast.makeText(getApplicationContext(), "Please selected category", Toast.LENGTH_SHORT).show();

                }else {

                    //send the value to the receive
                    Intent intent = new Intent(MainActivity.this, ReceiveActivity.class);
                    intent.putExtra("CateKey",cateSelect);

                    //start send
                    startActivity(intent);

                    //testing the index value
                    for(int i = 0 ; i < cateSelect.size(); i++) {
                        System.out.println("-Main activity--" + cateSelect.get(i));
                    }
                    //reset the string
                    cateSelect.clear();

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