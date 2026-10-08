package com.example.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etUsername, etPassword, etConfirmPassword;
    Button btnSignup;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignup = findViewById(R.id.btnSignup);
        tvResult = findViewById(R.id.tvResult);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if (username.isEmpty()) {
                    tvResult.setText("Username cannot be empty");
                }
                else if (password.isEmpty()) {
                    tvResult.setText("Password cannot be empty");
                }
                else if (password.length() < 6) {
                    tvResult.setText("Password must be at least 6 characters");
                }
                else if (!password.equals(confirmPassword)) {
                    tvResult.setText("Passwords do not match");
                }
                else {
                    tvResult.setText("Sign-Up Successful!");
                }
            }
        });
    }
}
