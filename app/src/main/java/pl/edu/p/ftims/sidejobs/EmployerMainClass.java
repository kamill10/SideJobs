package pl.edu.p.ftims.sidejobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmployerMainClass extends BaseActivity {
    private Button addJobButton;
    private Button showApplicationsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_job_or_show_applications);
        addJobButton = findViewById(R.id.add_job_button);
        showApplicationsButton = findViewById(R.id.show_applications_button);
        addJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowAddJobActivity.class);
                startActivity(intent);
            }
        });
        showApplicationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user == null) {
                    Toast.makeText(getApplicationContext(),
                                    "Nie jestes zalogowany!!",
                                    Toast.LENGTH_LONG)
                            .show();
                    Intent intent = new Intent(getApplicationContext(), RegisterOrLoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), ShowEmployerApplicationsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
