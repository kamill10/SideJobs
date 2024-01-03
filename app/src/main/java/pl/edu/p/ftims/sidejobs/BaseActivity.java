package pl.edu.p.ftims.sidejobs;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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
            if(FirebaseAuth.getInstance().getCurrentUser() == null){
                menu.findItem(R.id.action_sign_out).setVisible(false);
                menu.findItem(R.id.sign_in_menu_main).setVisible(true);
                menu.findItem(R.id.sign_in_menu_main).setTitle("Zaloguj się/Zarejestruj");
            }
            else{
                menu.findItem(R.id.action_sign_out).setVisible(true);
                menu.findItem(R.id.action_sign_out).setTitle(Html.fromHtml(FirebaseAuth.getInstance().getCurrentUser().getEmail() + "<br>Wyloguj się"));
                menu.findItem(R.id.sign_in_menu_main).setVisible(false);
            }

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
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
                return true;
            }
        //if user logged in
        if (id == R.id.sign_in_menu_main) {
                Intent intent = new Intent(getApplicationContext(), RegisterOrLoginActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
