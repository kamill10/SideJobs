package pl.edu.p.ftims.sidejobs;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
                        String numberOfPlaces = String.valueOf(document.getLong("numberOfAvailablePlaces"));

                        // Update UI elements with job details
                        TextView etJobName = findViewById(R.id.etJobName);
                        TextView etJobDesc = findViewById(R.id.etJobDesc);
                        TextView etRequiredAge = findViewById(R.id.etRequiredAge);
                        TextView etRequiredExperience = findViewById(R.id.etRequiredExperience);
                        TextView etHourlyRate = findViewById(R.id.etHourlyRate);
                        TextView etAvailablePlaces = findViewById(R.id.etAvailablePlaces);
                        Button jobAddButton = findViewById(R.id.jobAddButton);


                        etJobName.setText(jobName);
                        etJobDesc.setText(jobDesc);
                        etRequiredAge.setText(requiredAge);
                        etRequiredExperience.setText(requiredExperience);
                        etHourlyRate.setText(hourlyPay);
                        etAvailablePlaces.setText(numberOfPlaces);


// Set onClickListener for the Apply button
                        jobAddButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Check if numberOfAvailablePlaces is greater than 0
                                long numberOfAvailablePlaces = document.getLong("numberOfAvailablePlaces");
                                if (numberOfAvailablePlaces > 0) {
                                    // Show success message
                                    Toast.makeText(JobDetailsActivity.this,
                                            "Twoja aplikacja została wysłana", Toast.LENGTH_SHORT).show();

                                    // Update numberOfAvailablePlaces in the database (-1)
                                    jobRef.update("numberOfAvailablePlaces", numberOfAvailablePlaces - 1);

                                    // Fetch updated job details from Firestore
                                    jobRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                DocumentSnapshot updatedDocument = task.getResult();
                                                if (updatedDocument != null && updatedDocument.exists()) {
                                                    // Extract updated job details
                                                    String updatedJobName = updatedDocument.getString("jobName");
                                                    String updatedJobDesc = updatedDocument.getString("jobDesc");
                                                    String updatedRequiredAge = String.valueOf(updatedDocument.getLong("requiredAge"));
                                                    String updatedRequiredExperience = updatedDocument.getString("requiredExperience");
                                                    String updatedHourlyPay = String.valueOf(updatedDocument.getLong("hourlyPay"));
                                                    String updatedNumberOfPlaces = String.valueOf(updatedDocument.getLong("numberOfAvailablePlaces"));

                                                    // Update UI elements with updated job details
                                                    etJobName.setText(updatedJobName);
                                                    etJobDesc.setText(updatedJobDesc);
                                                    etRequiredAge.setText(updatedRequiredAge);
                                                    etRequiredExperience.setText(updatedRequiredExperience);
                                                    etHourlyRate.setText(updatedHourlyPay);
                                                    etAvailablePlaces.setText(updatedNumberOfPlaces);
                                                }
                                            }
                                        }
                                    });
                                } else {
                                    // Show error message
                                    Toast.makeText(JobDetailsActivity.this,
                                            "Przykro nam, nie ma już miejsc", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

// ...


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
