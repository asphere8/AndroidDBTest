package com.test.kahdek.dbtest;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{

    UserData userdata;
    EditText unametxt = (EditText)findViewById(R.id.txt_nom);
    EditText uocctxt = (EditText)findViewById(R.id.txt_occ);

    @Override
    public View findViewById(@IdRes int id) {
        return super.findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit(){
        String uname = unametxt.getText().toString();
        String uocc = uocctxt.getText().toString();
        userdata.setUserName(uname);
        userdata.setUserOccupation(uocc);
        DatabaseHandler.getInstance(this).addUserData(userdata);
    }

    public void get(){

    }

}

