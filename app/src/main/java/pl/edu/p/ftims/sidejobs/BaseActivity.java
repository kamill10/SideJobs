package pl.edu.p.ftims.sidejobs;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sign_out) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getApplicationContext(),
                            "Sing Out successful!!",
                            Toast.LENGTH_LONG)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
