package pl.edu.p.ftims.sidejobs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ShowEmployerApplicationsActivity extends BaseActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_employer_applications);

        auth = FirebaseAuth.getInstance();

        // Fetch the current user
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser != null) {
            String currentUserId = currentUser.getUid();

            // Fetch data from Firestore based on the current user's employerId
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("job_applications")
                    .whereEqualTo("employerId", currentUserId)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                LinearLayout llApplications = findViewById(R.id.llApplications);
                                llApplications.removeAllViews(); // Clear existing views

                                for (QueryDocumentSnapshot document : querySnapshot) {
                                    // Retrieve employeeId and jobOfferId from Firestore
                                    String employeeMailFromFirestore = document.getString("employeeMail");
                                    String jobOfferId = document.getString("jobOfferId");

                                    // Create UI elements dynamically for each job application
                                    createApplicationUI(llApplications, employeeMailFromFirestore, jobOfferId);
                                }
                            } else {
                                // Handle failed data retrieval
                                System.out.println("No documents found for the current user's employerId.");
                            }
                        } else {
                            // Handle failed data retrieval
                            System.out.println("Failed to retrieve documents from Firestore.");
                        }
                    });
        } else {
            // Handle the case where the current user is null
            System.out.println("Current user is null.");
        }
    }

    // Create UI elements dynamically for each job application
    @SuppressLint("SetTextI18n")
    private void createApplicationUI(LinearLayout parentLayout, String employeeMail, String jobOfferId) {
        // Fetch job name from Firestore based on jobOfferId
        getJobNameFromFirestore(jobOfferId, (jobName) -> {
            // Create a TextView to display potential employee and job name
            TextView textViewApplication = new TextView(this);

            textViewApplication.setText("Potencjalny Pracownik: " + employeeMail + "\nNazwa Pracy: " + jobName);
            textViewApplication.setPadding(16, 8, 16, 8);

            // Add the TextView to the parent layout
            parentLayout.addView(textViewApplication);
            System.out.println("TextView added to the layout.");
        });
    }

    // Add a method to retrieve jobName based on jobOfferId from Firestore
    private void getJobNameFromFirestore(String jobOfferId, JobNameCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Query job_offers collection based on jobOfferId
        db.collection("job_offers")
                .whereEqualTo("jobID", jobOfferId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                            // Assuming there is only one document with the specified jobOfferId
                            QueryDocumentSnapshot document = (QueryDocumentSnapshot) querySnapshot.getDocuments().get(0);

                            // Retrieve jobName from Firestore
                            String jobName = document.getString("jobName");

                            // Invoke the callback with the retrieved jobName
                            callback.onJobNameReceived(jobName);
                        }
                    } else {
                        // Handle failed data retrieval
                    }
                });
    }

    // Callback interface to handle jobName retrieval
    private interface JobNameCallback {
        void onJobNameReceived(String jobName);
    }
}
