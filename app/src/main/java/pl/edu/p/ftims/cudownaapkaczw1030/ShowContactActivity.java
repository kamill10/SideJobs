package pl.edu.p.ftims.cudownaapkaczw1030;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import pl.edu.p.ftims.cudownaapkaczw1030.model.Contact;

public class ShowContactActivity extends AppCompatActivity {
FirebaseFirestore db;
    ContactFirestoreRecyclerAdapter adapter;
RecyclerView rvShowContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
        db = FirebaseFirestore.getInstance();
        rvShowContacts = findViewById(R.id.rvShowContacts);
        /*
        0. implementacja Firewbase UI (Firestore, Storage, Auth)
        1. Query
        2. Options
        3. Item xml, ustawiamy RecleclerView jako główny komponent
        4. ViewHolder podklasa
        5. Adapter
        6. ustawiamy layout na ReclerView
        7. Włączamy i wyłączamy nasłuch na zmiany w Firebase (dla adaptera)
         */
        Query query = db
                .collection("kontakty")
                .orderBy("phone")
                .limit(50);
        FirestoreRecyclerOptions<Contact> options = new FirestoreRecyclerOptions.Builder<Contact>()
                .setQuery(query, Contact.class)
                .build();
        adapter = new ContactFirestoreRecyclerAdapter(options);
        rvShowContacts.setLayoutManager(new LinearLayoutManager( getApplicationContext() ));
        rvShowContacts.setAdapter(adapter);
    }//onCreate

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}//