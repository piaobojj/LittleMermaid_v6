package project.littlemermaid_v6;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static android.R.attr.x;
import static android.R.id.list;

public class ReceiveActivity extends Activity implements View.OnClickListener {

    private Button nextBtn, returnBtn;
    private TextView test;

    private boolean nextTrigger;

    ArrayList<Integer> cateSelected = new ArrayList<Integer>();
    ArrayList<String> word = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        nextTrigger = false;

        word.add("unpronounceable"); word.add("trash-talking"); word.add("gymnasium"); word.add("faithfull");

        test = (TextView) findViewById(R.id.test);

        Intent intent = getIntent();
        cateSelected = intent.getIntegerArrayListExtra("CateKey");
        //System.out.println("-From other activity--" + cateSelected);

        nextBtn = (Button) findViewById(R.id.nextBtn);
        returnBtn = (Button) findViewById(R.id.returnBtn);
        nextBtn.setOnClickListener(this);
        returnBtn.setOnClickListener(this);


        // TODO Need more task
        for(int i = 0 ; i < cateSelected.size(); i++) {
            // System.out.println("-From other activity--" + cateSelected.get(i));
            ArrayList<Integer> list = new ArrayList<>();
            list.add(cateSelected.get(i));
        }

        int wordSize = 8; //word.size(); //4
        //btn display

        if( wordSize > 4){

            System.out.println("Going in");
            int tempTrack = 0;

            //dynamicBtnDisplay(0,word.size());

            if(nextTrigger = true && wordSize > 4){
                System.out.println("Going to this next");
                TableLayout table = (TableLayout) findViewById( R.id.btnLayout);
                table.removeAllViews();

                for(int index = tempTrack; index < wordSize; index++){
                    //TableLayout table = (TableLayout) findViewById( R.id.btnLayout);
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
                        ToggleButton bb = new ToggleButton( this );
                        row.addView( bb, 500, 200 );
                        //bb.setText(word.get(index));
                    }
                }

            }



        }else{
            //display if no more than 4 word in category
            dynamicBtnDisplay(0,word.size());
        }


    }

    private void dynamicBtnDisplay(int i, int size) {
        for(int index = i; index < size; index++){
            TableLayout table = (TableLayout) findViewById( R.id.btnLayout);

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
                bb.setText(word.get(index));
            }
        }
    }


    @Override
    public void onClick(View view) {
        //define the button that has been clicked and perform the corresponding user selected
        switch (view.getId()){
            //close return the launch the activity
            case R.id.nextBtn:
                if(word.size() > 4) {
                    nextTrigger = true;
                    System.out.println("-From other activity-nexttrigger-" + nextTrigger);
                }else{
                    Toast.makeText(getApplicationContext(), "No more word", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.returnBtn:
                finish();
                break;
            default:
                break;
        }
    }









}




