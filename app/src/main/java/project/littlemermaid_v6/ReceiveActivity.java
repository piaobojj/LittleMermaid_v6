package project.littlemermaid_v6;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static android.R.id.list;

public class ReceiveActivity extends Activity implements View.OnClickListener {

    private Button nextBtn;
    //private ArrayList<Integer> myList = new ArrayList<>();

    //testing remove later
    public String[] ar = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    public int [] ir = {5,4,3,2,1,6,7,8,9,10,11,12};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        Intent intent = getIntent();

        nextBtn = (Button) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(this);

        /*
                try{
                    //myList.addAll(intent.getExtras().getIntegerArrayList("Magazine"));
                    //System.out.println("----------------------------------" + myList.get(0));
                    //ArrayList<CategoryData> list = (ArrayList<CategoryData>) intent.getSerializableExtra("Magazine");
                    //System.out.print("----------------------------------" + list.size());

                    displayBtn();


                }catch (NullPointerException e){

                }*/

        displayBtn();

    }

    public void displayBtn() {

        for (int index = 0;  index < 10 ;index++){
            TableLayout table = (TableLayout) findViewById( R.id.btnLayout );
            int buttonsInRow = 0;
            int numRows = table.getChildCount();
            TableRow row = null;
            if( numRows > 0 ){
                row = (TableRow) table.getChildAt( numRows - 1 );
                buttonsInRow = row.getChildCount();
            }

            if( numRows == 0 || buttonsInRow == 4){
                row = new TableRow( this );
                table.addView( row );
                buttonsInRow = 0;
            }
            if( buttonsInRow < 4 ){
                Button bb = new Button( this );
                row.addView( bb, 300, 200 );
                bb.setText(ar[index]);
                //bb.setText(myList.get(index));
                //bb.setText(ar[index]);
            }

        }
    }


    @Override
    public void onClick(View view) {
        //define the button that has been clicked and perform the corresponding user selected
        switch (view.getId()){
            //close return the launch the activity
            case R.id.nextBtn:
                break;
            default:
                break;
        }
    }









}




