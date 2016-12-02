package project.littlemermaid_v6;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class ButtonAdapter extends BaseAdapter{

    public Context mContext;

    public String [] fName = { "File 1", "File 2", "Roflcopters"};

    public ButtonAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Button btn;
        if (view == null) {      // if it's not recycled, initialize some attributes
            btn = new Button (mContext);
            btn.setLayoutParams (new GridView.LayoutParams (300, 200));
            btn.setPadding (1, 1, 1, 1);
        } else {
            btn = (Button) view;
        }
        btn.setHighlightColor(Color.GREEN);
        btn.setId (i);
        btn.setText(fName[i]);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return btn;
    }
}
