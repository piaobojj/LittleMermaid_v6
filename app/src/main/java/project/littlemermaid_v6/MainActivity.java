package project.littlemermaid_v6;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TableLayout;
        import android.widget.TableRow;
        import android.widget.Toast;

        import com.firebase.client.ChildEventListener;
        import com.firebase.client.DataSnapshot;
        import com.firebase.client.Firebase;
        import com.firebase.client.FirebaseError;

        import java.io.BufferedReader;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.HashSet;
        import java.util.Iterator;
        import java.util.Set;

        import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //private Firebase aRef;
    private Button launchBtn;

    /*Display button topic*/
    ArrayList<String> btnDisplay;

    /*Button Selection */
    HashSet<String> setSelected;
    //ArrayList<String> setSelected;

    HashMap<String, Set<String>> topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hash map for topic
        topic = new HashMap<String, Set<String>>();

        //topic display on btn
        btnDisplay = new ArrayList<String>();

        //topic get selected
        setSelected = new HashSet<>();
        //setSelected = new ArrayList<>();

        //button get layout id
        launchBtn = (Button) findViewById(R.id.launchButton);

        //event handler
        launchBtn.setOnClickListener(this);

        //read file from csv
        String line;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("Dec1606Lexicons.csv")));
            while((line = br.readLine())!= null){

                String[] words = line.split(",");

                HashSet<String> set = new HashSet<>();
                for(int i = 1 ; i < words.length ; i++){
                    set.add(words[i]);
                }
                topic.put(words[0],set);

                //save all the topic
                //topic.put(words[0],set_name);
                System.out.println(topic);
                //System.out.println(words.length);
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ////////////////////////////////////////////Store the key////////////////////////
        String key = "";
        //Get the set of key from hashmap
        Set setOfKeys = topic.keySet();
        //get the iterator instance from set
        Iterator iterator = setOfKeys.iterator();
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            btnDisplay.add(key);
            System.out.println("Key: "+ btnDisplay);
        }
        //////////////////////////////////////////////////////////////////////////////////////////

        //display button
        categoryDisplay();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.launchButton:

                if(setSelected.size() == 0){
                    Toast.makeText(getApplicationContext(), "Please selected category", Toast.LENGTH_SHORT).show();
                }else {
                    //send the value to the receive
                    Intent intent = new Intent(MainActivity.this, ReceiveActivity.class);
                    intent.putExtra("Topic",setSelected);
                    //start send
                    startActivity(intent);

                    //reset the string
                    setSelected.clear();
                }

                break;

            default:
                break;
        }
    }

    //Topic display
    private void categoryDisplay() {

        try{
            //create the 9 X 9 buttons on the table layout with correspond word
            for(int index = 0; index < 9; index++){

                TableLayout table = (TableLayout) findViewById( R.id.topicLayout);

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
                    bb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Button button = (Button) view;
                            String selectedText = button.getText().toString();
                            //user selected the button add to the array list
                            if (topic.containsKey(selectedText)){
                                setSelected.addAll(topic.get(selectedText));
                                //System.out.println(topic.get(selectedText));
                                System.out.println(setSelected.toString());
                            }
                            //Toast.makeText(getApplicationContext(), selectedText, Toast.LENGTH_SHORT).show();
                        }
                    });
                    row.addView( bb, 400, 200 );
                    bb.setText(btnDisplay.get(index));
                }
            }

        }catch (NullPointerException e){

        }
    }


}