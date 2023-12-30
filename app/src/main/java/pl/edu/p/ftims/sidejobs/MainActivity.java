package pl.edu.p.ftims.sidejobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {
    private Button employerButton;
    private Button employeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employerButton = findViewById(R.id.btnEmployer);
        employeeButton = findViewById(R.id.btnEmployee);

        employerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowAddJobActivity.class);
                startActivity(intent);
            }
        });

        employeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowJobOffersActivity.class);
                startActivity(intent);
            }
        });
    }
}
