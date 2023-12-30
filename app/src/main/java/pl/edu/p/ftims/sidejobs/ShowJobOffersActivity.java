package pl.edu.p.ftims.sidejobs;

// Import necessary packages
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

// Inside ShowJobOffersActivity class
public class ShowJobOffersActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private CollectionReference jobOffersCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobs_offers);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();
        jobOffersCollection = db.collection("job_offers");

        // Call method to fetch and display job offers
        fetchAndDisplayJobOffers();
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
                        addJobNameToLayout(jobName);
                    }
                }
            } else {
                // Handle failed data retrieval
            }
        });
    }

    private void addJobNameToLayout(String jobName) {
        TextView textViewJobName = new TextView(this);
        textViewJobName.setText(jobName);

        // Set the text size in scaled pixels (sp)
        float textSizeInSp = 20; // Adjust the value as needed
        textViewJobName.setTextSize(textSizeInSp);
        textViewJobName.setPadding(8, 0, 8, 24);
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

