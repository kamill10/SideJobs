package pl.edu.p.ftims.cudownaapkaczw1030;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import pl.edu.p.ftims.cudownaapkaczw1030.model.Contact;

public class MainActivity extends AppCompatActivity {
    EditText etPersonName;
    EditText etPhone;
    EditText etResult;
    EditText etDocID;
    SeekBar sbKwota;
    TextView tvKwota;
    Button bOK, bEdit, bShow;
    double kwota;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        CollectionReference collectionRef = db.collection("contacts");
        DocumentReference docRef = collectionRef.document("kontakt1");
        etPersonName = findViewById( R.id.etPersonName );
        etPhone = findViewById( R.id.etPhone );
        etResult = findViewById( R.id.etResult );
        tvKwota = findViewById(R.id.tvKwota);
        sbKwota = findViewById(R.id.sbKwota);
        etDocID = findViewById(R.id.etDocID);

        int progress = sbKwota.getProgress();
         kwota = 20+ 10*progress;
        tvKwota.setText( String.valueOf(kwota) );
        bOK = findViewById( R.id.bOK);
        bEdit   = findViewById( R.id.bEdit);
        bShow   = findViewById( R.id.bShow);
        bShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , ShowContactActivity.class);
                startActivity(intent);
            }
        });
        sbKwota.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //    int progress = sbKwota.getProgress();
                 kwota = 20+ 10*progress;
                tvKwota.setText( String.valueOf(kwota) );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtName= etPersonName.getText().toString().trim();
                String txtPhone = etPhone.getText().toString().trim();
                String txtdocID = etDocID.getText().toString().trim();

                etResult.setText( txtName  + "  \n" +txtPhone );
                showToast(txtName , Toast.LENGTH_LONG);
                DocumentReference docRef= db.collection("kontakty").document(txtdocID);
                Contact contact = new Contact(txtName, kwota, txtPhone);
                docRef.set(contact).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        showToast("Sukces", Toast.LENGTH_LONG);
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                showToast("Porażka", Toast.LENGTH_SHORT);
                            }
                        })
                ;


            }
        });
        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtdocID = etDocID.getText().toString().trim();
                DocumentReference docRef= db.collection("kontakty").document(txtdocID);
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {//  documentSnapshot not null
                        Contact contact = documentSnapshot.toObject(Contact.class);
                        if(contact!=null){
                            etPersonName.setText( contact.getFirstName() );
                            etPhone.setText( contact.getPhone() );
                            kwota = contact.getKwota();
                            tvKwota.setText(  String.valueOf(kwota) );
                            //kwota = 20+ 10*progress;
                            int progress = (int) ((kwota-20)/10);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                sbKwota.setProgress(progress, true);
                            }//ver
                            else {
                                sbKwota.setProgress(progress);
                            }
                        }else{
                            showToast("Contact null", Toast.LENGTH_LONG);
                        }
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                showToast("Fail Edit", Toast.LENGTH_SHORT);
                            }
                        });
            }
        });


    }//onCreate

    public void showToast(String txt, int lenght){
        Toast toast = Toast.makeText(getApplicationContext(),txt, lenght);
        toast.show();
    }//showToast

    public EditText getEtPersonName() {
        return etPersonName;
    }

    public EditText getEtPhone() {
        return etPhone;
    }

    public EditText getEtResult() {
        return etResult;
    }


    public Button getbOK() {
        return bOK;
    }
}