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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.R.attr.x;
import static android.R.id.list;
import static android.media.CamcorderProfile.get;

public class ReceiveActivity extends Activity implements View.OnClickListener {

    private Button nextBtn, returnBtn;
    private Firebase aRef;

    /*Button Selection from launch activity*/
    HashSet<String> setSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        nextBtn = (Button)findViewById(R.id.nextBtn);
        GridView gv = (GridView) findViewById(R.id.gv);

        //topic get selected
        setSelected = new HashSet<>();

        /* Get the user selected value from category page*/
        Intent intent = getIntent();
        setSelected = (HashSet<String>) intent.getSerializableExtra("Topic");
        System.out.println("-From other activity---" + setSelected);

        nextBtn = (Button) findViewById(R.id.nextBtn);
        returnBtn = (Button) findViewById(R.id.returnBtn);
        nextBtn.setOnClickListener(this);
        returnBtn.setOnClickListener(this);

        //connect to the Firebase
        aRef = new Firebase("https://littlemermaid.firebaseio.com/dictionary/");

        aRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                GenericTypeIndicator<Map<String, Object>> m = new GenericTypeIndicator<Map<String, Object>>(){};
                Map<String, Object> map = dataSnapshot.getValue(m);
                //word
                String lemma = (String) map.get(setSelected);
                System.out.println(lemma);
                //check each set of value
                /*
                                for (String setVal : setSelected) {
                                    System.out.println(setVal);
                                }*/
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

    }


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




