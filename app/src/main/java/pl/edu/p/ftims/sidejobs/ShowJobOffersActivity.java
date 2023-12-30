package pl.edu.p.ftims.sidejobs;// Import necessary packages
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ShowJobOffersActivity extends BaseActivity {
    private FirebaseFirestore db;
    private CollectionReference jobOffersCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobs_offers);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();
        jobOffersCollection = db.collection("job_offers");

        Button btnSortAlphabetically = findViewById(R.id.btnSortAlphabetically);

        // Set an OnClickListener to handle sorting
        btnSortAlphabetically.setOnClickListener(v -> {
            // Call method to fetch and display job offers with alphabetical sorting
            onSortAlphabeticallyClick();
        });

        // Call method to fetch and display job offers initially
        fetchAndDisplayJobOffers();
    }

    private void onSortAlphabeticallyClick() {
        jobOffersCollection.orderBy("jobName").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Handle successful data retrieval
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    LinearLayout llShowJobs = findViewById(R.id.llShowJobs);
                    llShowJobs.removeAllViews(); // Clear existing views

                    for (QueryDocumentSnapshot document : querySnapshot) {
                        String jobName = document.getString("jobName");
                        addJobNameToLayout(jobName, document.getId());
                    }
                }
            } else {
                // Handle failed data retrieval
            }
        });
    }

    private void fetchAndDisplayJobOffers() {
        jobOffersCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Handle successful data retrieval
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    LinearLayout llShowJobs = findViewById(R.id.llShowJobs);
                    llShowJobs.removeAllViews(); // Clear existing views

                    for (QueryDocumentSnapshot document : querySnapshot) {
                        // Access jobName field and add it to the LinearLayout
                        String jobName = document.getString("jobName");
                        addJobNameToLayout(jobName, document.getId()); // Pass job document ID
                    }
                }
            } else {
                // Handle failed data retrieval
            }
        });
    }

    private void addJobNameToLayout(String jobName, String jobId) {
        TextView textViewJobName = new TextView(this);
        textViewJobName.setText(jobName);

        // Set the text size in scaled pixels (sp)
        float textSizeInSp = 20; // Adjust the value as needed
        textViewJobName.setTextSize(textSizeInSp);
        textViewJobName.setPadding(8, 0, 8, 24);

        // Set OnClickListener to navigate to job details
        textViewJobName.setOnClickListener(v -> {
            // Create an Intent to start JobDetailsEmployeeActivity
            Intent intent = new Intent(ShowJobOffersActivity.this, JobDetailsActivity.class);
            // Pass the job document ID as an extra
            intent.putExtra("jobId", jobId);
            startActivity(intent);
        });

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 8);
        textViewJobName.setLayoutParams(params);

        LinearLayout llShowJobs = findViewById(R.id.llShowJobs);
        llShowJobs.addView(textViewJobName);
    }
}
