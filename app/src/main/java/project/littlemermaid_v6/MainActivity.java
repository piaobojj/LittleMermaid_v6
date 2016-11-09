package project.littlemermaid_v6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Firebase aRef;
    private Button nextWordButton;

    /*Fix data string array, this will become the local database later on*/
    String[] foodTitle = { "Pie", "Apple", "Steak", "Bacon", "Banana", "Hot Dog","Ice Cream","Cookies","Candy","Orange"};
    ArrayList<String> buttonString = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Assign associate navigation ID to the button variable*/
        nextWordButton = (Button) findViewById(R.id.nextButton);

        aRef = new Firebase("https://littlemermaid.firebaseio.com/dictionary");//.child("5502");

        aRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Map<String, String> map = dataSnapshot.getValue(Map.class);
                String dataValue = map.get("NGram");
                System.out.println(dataValue);
                //buttonString.add(dataValue)-;
                Log.v("The Value", "-------------Data I got:  " + dataValue);
                //Log.v("The Value", "-------------How many  " + buttonString.size());
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

        //display
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
                    Button bb = new Button( this );
                    row.addView( bb, 300, 200 );
                    bb.setText(foodTitle[index]);
                    //bb.setText(buttonString.get(index));
                }
            }

        }catch (NullPointerException e){

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
    }


}
