package com.test.kahdek.dbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.List;
import java.util.ListIterator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class MainActivity extends AppCompatActivity{

    UserData userdata;
    EditText unametxt = (EditText)findViewById(R.id.txt_nom);
    EditText uocctxt = (EditText)findViewById(R.id.txt_occ);

    @Override
    public View findViewById(int id) {
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
        List<UserData> a = DatabaseHandler.getInstance(this).getAllUserData();
        ListIterator<UserData> li = a.listIterator();

        while (li.hasNext()){
            String uname = li.next().getUserName();
            String uocc = li.previous().getUserOccupation(); //Zig-zag pattern!
            li.next();
            String msg = "User name: ";
            msg = msg.concat(uname);
            msg = msg.concat(". User occupation: ");
            msg = msg.concat(uocc);
            new AlertDialog.Builder(this).setTitle("User Data").setMessage(msg).setNeutralButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent back = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(back);
                }
            });

        }
    }
}