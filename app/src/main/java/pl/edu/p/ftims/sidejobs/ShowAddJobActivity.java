package pl.edu.p.ftims.sidejobs;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import pl.edu.p.ftims.sidejobs.model.JobOffer;


public class ShowAddJobActivity extends AppCompatActivity {
    private Button addJobButton;
    private EditText jobNameTextView, jobDescTextView,requiredAgeTextView,requiredExperienceTextView,hourlyPayTextView,numberOfPlacesTextView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_details_employer);
        jobNameTextView = findViewById(R.id.etJobName);
        jobDescTextView = findViewById(R.id.etJobDesc);
        requiredAgeTextView = findViewById(R.id.etRequiredAge);
        requiredExperienceTextView = findViewById(R.id.etRequiredExperience);
        hourlyPayTextView = findViewById(R.id.etHourlyRate);
        numberOfPlacesTextView = findViewById(R.id.etNumberofAvailablePlaces);
        addJobButton = findViewById(R.id.jobAddButton);
        addJobButton.setOnClickListener(new View.OnClickListener() {
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
                } else {
                    saveJobOffer();
                }
            }
        });
    }
    public void saveJobOffer(){
        validateData( jobNameTextView.getText().toString()
                ,jobDescTextView.getText().toString()
                ,requiredAgeTextView.getText().toString()
                ,requiredExperienceTextView.getText().toString()
                ,hourlyPayTextView.getText().toString(),
                numberOfPlacesTextView.getText().toString());
        String jobName = jobNameTextView.getText().toString();
        String jobDesc = jobDescTextView.getText().toString();
        String  requiredExperience = requiredExperienceTextView.getText().toString();
        int requiredAge = Integer.parseInt(requiredAgeTextView.getText().toString());
        int hourlyPay = Integer.parseInt(hourlyPayTextView.getText().toString());
        int numberOfAvailablePlaces = Integer.parseInt(numberOfPlacesTextView.getText().toString());
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        JobOffer jobOffer = new JobOffer(jobName,jobDesc,requiredAge,requiredExperience,hourlyPay,userID.toString(),numberOfAvailablePlaces);
        jobOffer.setJobId(UUID.randomUUID().toString());
        // Convert JobOffer to a Map
        Map<String, Object> jobOfferMap = new HashMap<>();
        jobOfferMap.put("jobID", jobOffer.getJobId());
        jobOfferMap.put("jobName", jobOffer.getJobName());
        jobOfferMap.put("jobDesc", jobOffer.getJobDesc());
        jobOfferMap.put("requiredAge", jobOffer.getRequiredAge());
        jobOfferMap.put("requiredExperience", jobOffer.getRequiredExperience());
        jobOfferMap.put("hourlyPay", jobOffer.getHourlyPay());
        jobOfferMap.put("userID", jobOffer.getUserID());
        jobOfferMap.put("numberOfAvailablePlaces", jobOffer.getNumberOfPlaces());
        // Save the Map to Firestore
        CollectionReference jobOffersCollection = db.collection("job_offers");
        jobOffersCollection.document(jobOffer.getJobId()).set(jobOfferMap)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getApplicationContext(),
                                    "Zapisano!",
                                    Toast.LENGTH_LONG)
                            .show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getApplicationContext(),
                                    "Error in saving to database",
                                    Toast.LENGTH_LONG)
                            .show();
                    Log.e("FirestoreError", "Error in saving to database", e);
                });
    }
     void validateData(String jobName,String jobDesc, String  requiredAge,String requiredExperience, String  hourlyPay, String  numberOfAvailablePlaces){
         if (TextUtils.isEmpty(jobName)) {
             Toast.makeText(getApplicationContext(),
                             "Wprowadz nazwe pracy!!",
                             Toast.LENGTH_LONG)
                     .show();
             return;
         }
         try {
             int age = Integer.parseInt(requiredAge);
             // Additional checks on age if needed
         } catch (NumberFormatException e) {
             Toast.makeText(getApplicationContext(),
                             "Wprowadz wymagany wiek!!",
                             Toast.LENGTH_LONG)
                     .show();
             return;
         }
         if (TextUtils.isEmpty(jobDesc)) {
             Toast.makeText(getApplicationContext(),
                             "Wprowadz opis pracy!",
                             Toast.LENGTH_LONG)
                     .show();
             return;
         }
         if (TextUtils.isEmpty(requiredExperience)) {
             Toast.makeText(getApplicationContext(),
                             "Wprowadz wymagane doswiadczenie!!",
                             Toast.LENGTH_LONG)
                     .show();
             return;
         }
         try {
             int pay = Integer.parseInt(hourlyPay);
             // Additional checks on hourlyPay if needed
         } catch (NumberFormatException e) {
             Toast.makeText(getApplicationContext(),
                             "Wprowadz poprawnie place !!",
                             Toast.LENGTH_LONG)
                     .show();
             return;
         }

         try {
             int number = Integer.parseInt(numberOfAvailablePlaces);
             // Additional checks on numberOfAvailablePlaces if needed
         } catch (NumberFormatException e) {
                 Toast.makeText(getApplicationContext(),
                                 "Wprowadz poprawna liczbe dostepnych miejsc!",
                                 Toast.LENGTH_LONG)
                         .show();
             return;
         }
     }
}
