package com.example.sherry.ezpark;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

    TextView textViewTitle2, textViewSlogan, textViewEmail, textViewPassword;
    Button buttonLogin, buttonNewUser;
    EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        textViewTitle2 = (TextView) findViewById(R.id.textViewTitle2);
        textViewSlogan = (TextView) findViewById(R.id.textViewSlogan);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewPassword = (TextView) findViewById(R.id.textViewPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonNewUser = (Button) findViewById(R.id.buttonNewUser);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAccount(view);
            }
        });
        buttonNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newUserIntent = new Intent(LoginActivity.this, NewUserActivity.class);
                startActivity(newUserIntent);
            }
        });

    }

    public void checkAccount(View view) {
        String email = "viktorherald05@gmail.com";
        String password = "123456";

        //validation
        if (editTextEmail.getText() != null && editTextPassword.getText() != null) {
            if (editTextEmail.getText().toString().equals(email) && editTextPassword.getText().toString().equals(password)) {

                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.error)
                        .setMessage(R.string.error_message1)
                        .setPositiveButton(R.string.ok, null)
                        .setCancelable(true)
                        .create().show();
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
            }
        }

        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.error)
                    .setMessage(R.string.error_message2)
                    .setPositiveButton(R.string.ok, null)
                    .setCancelable(true)
                    .create().show();
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
        }
    }
}
