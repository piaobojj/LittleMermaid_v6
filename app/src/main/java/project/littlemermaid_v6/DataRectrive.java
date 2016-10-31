package project.littlemermaid_v6;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by user on 2016/10/31.
 */
public class DataRectrive extends Application {

    public void onCreate(){
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
