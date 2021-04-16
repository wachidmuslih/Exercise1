package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class Login_Activity extends AppCompatActivity {
    Button btnLogin;

    TextInputLayout EditEmail, EditPwd;

    String email, password;

    String emailAdmin = "admin@mail.com";

    String pwdAdmin = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        btnLogin = findViewById(R.id.loginBtn);

        EditEmail = findViewById(R.id.edtEmail);

        EditPwd = findViewById(R.id.edtPwd);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = EditEmail.getEditText().getText().toString();

                password = EditPwd.getEditText().getText().toString();

                if (email.equals(emailAdmin))
                {
                    if(password.equals(pwdAdmin))
                    {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);

                        startActivity(i);

                        EditEmail.setError(null);
                        EditPwd.setError(null);

                    }
                    else
                    {
                        EditPwd.setError("Password Anda Salah!");
                    }
                }
                else
                {
                    EditEmail.setError("Email Anda Salah!");
                }

                if (email.isEmpty())
                {
                    EditEmail.setError("Email harus diisi!");
                }
                if (password.isEmpty())
                {
                    EditPwd.setError("Password harus diisi!");
                }
            }

        });
    }
}