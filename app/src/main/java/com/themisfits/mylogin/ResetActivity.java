package com.themisfits.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.themisfits.mylogin.ui.login.LoginActivity;

public class ResetActivity extends AppCompatActivity {

    TextView username;
    EditText pass , repass;
    Button confirm;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        username=(TextView) findViewById(R.id.resetuser);
        pass = (EditText) findViewById(R.id.newp);
        repass=(EditText) findViewById(R.id.rep);
        confirm=(Button) findViewById(R.id.bt);
        DB=new DBHelper(this);
        Intent intent=getIntent();
        username.setText(intent.getStringExtra("username"));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();

                if(password.equals(repassword)) {

                    Boolean checkpassorupdate = DB.updatepassword(user, password);

                    if (checkpassorupdate == true) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "password updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetActivity.this, "password not updated", Toast.LENGTH_SHORT).show();
                    }
                }
                 else
                {
                    Toast.makeText(ResetActivity.this, "passwords not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}