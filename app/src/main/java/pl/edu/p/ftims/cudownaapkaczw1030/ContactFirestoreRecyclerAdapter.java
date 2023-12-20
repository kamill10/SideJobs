package pl.edu.p.ftims.cudownaapkaczw1030;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import pl.edu.p.ftims.cudownaapkaczw1030.model.Contact;

public class ContactFirestoreRecyclerAdapter extends FirestoreRecyclerAdapter<Contact, ContactViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ContactFirestoreRecyclerAdapter(@NonNull FirestoreRecyclerOptions<Contact> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ContactViewHolder holder, int position, @NonNull Contact model) {
        String txtName = model.getFirstName();
        holder.tvFirstNameItem.setText(txtName);
        holder.tvPhoneItem.setText( model.getPhone() );
        holder.tvValueItem.setText( String.valueOf( model.getKwota() ) );
       // holder.ivItem.setImageDrawable(R.drawable.indeks);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);

        return new ContactViewHolder(view);
    }
}
