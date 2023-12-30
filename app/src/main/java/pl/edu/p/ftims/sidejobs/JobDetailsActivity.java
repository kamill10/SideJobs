package pl.edu.p.ftims.sidejobs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JobDetailsActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_details_empleyee);

        // Retrieve job details from the intent
        String jobId = getIntent().getStringExtra("jobId");

        // TODO: Fetch job details using the jobId and update the UI
        // For example:
        // String jobName = ...;
        // String jobDescription = ...;
        // ...

        // Update UI elements with job details
        TextView etJobName = findViewById(R.id.etJobName);
        TextView etJobDesc = findViewById(R.id.etJobDesc);
        // TODO: Set text for other UI elements

        etJobName.setText("jobName");
        etJobDesc.setText("jobDescription");
        // TODO: Set text for other UI elements
    }
}