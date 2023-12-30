package pl.edu.p.ftims.sidejobs;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import pl.edu.p.ftims.sidejobs.R;

public class JobDetailsActivity extends BaseActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_details_empleyee);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Retrieve job details from the intent
        String jobId = getIntent().getStringExtra("jobId");

        // Fetch job details from Firestore
        if (jobId != null) {
            DocumentReference jobRef = db.collection("job_offers").document(jobId);
            jobRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        // Extract job details
                        String jobName = document.getString("jobName");
                        String jobDesc = document.getString("jobDesc");
                        String requiredAge = String.valueOf(document.getLong("requiredAge"));
                        String requiredExperience = document.getString("requiredExperience");
                        String hourlyPay = String.valueOf(document.getLong("hourlyPay"));

                        // Update UI elements with job details
                        TextView etJobName = findViewById(R.id.etJobName);
                        TextView etJobDesc = findViewById(R.id.etJobDesc);
                        TextView etRequiredAge = findViewById(R.id.etRequiredAge);
                        TextView etRequiredExperience = findViewById(R.id.etRequiredExperience);
                        TextView etHourlyRate = findViewById(R.id.etHourlyRate);

                        etJobName.setText(jobName);
                        etJobDesc.setText(jobDesc);
                        etRequiredAge.setText(requiredAge);
                        etRequiredExperience.setText(requiredExperience);
                        etHourlyRate.setText(hourlyPay);
                    } else {
                        Log.d("JobDetails", "No such document");
                    }
                } else {
                    Log.d("JobDetails", "get failed with ", task.getException());
                }
            });
        }
    }
}
