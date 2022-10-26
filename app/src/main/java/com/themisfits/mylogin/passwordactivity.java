package com.themisfits.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class passwordactivity extends AppCompatActivity {
    EditText username;
    Button reset;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordactivity);

        username=(EditText) findViewById(R.id.edforgot);
        reset=(Button) findViewById(R.id.buttonreset);
        DB=new DBHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                Boolean checkuser=DB.checkusername(user);
                if(checkuser==true)
                {
                    Intent intent =new Intent(getApplicationContext(),ResetActivity.class);
                    intent.putExtra("username",user);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(passwordactivity.this,"user does not exist",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}