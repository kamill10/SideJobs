package pl.edu.p.ftims.sidejobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterOrLoginActivity extends BaseActivity {
    private Button loginButton ;
    private Button registerButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register);
        loginButton = findViewById(R.id.btnEmployee);
        registerButton = findViewById(R.id.btnEmployer);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
