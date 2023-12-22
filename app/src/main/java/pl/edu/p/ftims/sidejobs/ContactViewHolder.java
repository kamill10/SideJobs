package pl.edu.p.ftims.sidejobs;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    //jest powiązany z contact_item.xml
    ImageView ivItem;
    TextView tvFirstNameItem;
    TextView tvPhoneItem;
    TextView tvValueItem;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        ivItem = itemView.findViewById(R.id.ivItem);
        tvFirstNameItem = itemView.findViewById(R.id.tvFirstNameItem);
        tvPhoneItem = itemView.findViewById(R.id.tvPhoneItem);
        tvValueItem = itemView.findViewById(R.id.tvValueItem);

    }
}

