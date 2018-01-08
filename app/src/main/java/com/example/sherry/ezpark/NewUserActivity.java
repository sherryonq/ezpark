package com.example.sherry.ezpark;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewUserActivity extends AppCompatActivity {

    TextView textViewCreate, textView2, textView3, textView4, textView5, textView6, textView7;
    EditText editTextFirstName, editTextLastName, editTextEmail2, editTextCarPlate, editTextPassword2, editTextConfirmPassword;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        textViewCreate = (TextView) findViewById(R.id.textViewCreate);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextEmail2 = (EditText) findViewById(R.id.editTextEmail2);
        editTextCarPlate = (EditText) findViewById(R.id.editTextCarPlate);
        editTextPassword2 = (EditText) findViewById(R.id.editTextPassword2);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(view);
            }
        });

    }

    public void createAccount(View view) {
        if(editTextFirstName.getText() != null
                && editTextLastName.getText() != null
                && editTextEmail2.getText() != null
                && editTextCarPlate.getText() != null
                && editTextPassword2.getText() != null
                && editTextConfirmPassword.getText() != null) {
            if(editTextConfirmPassword.getText().toString().equals(editTextPassword2.getText().toString())) {
                //add account
                Intent loginIntent = new Intent(NewUserActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.error)
                        .setMessage(R.string.error_message3)
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


}
