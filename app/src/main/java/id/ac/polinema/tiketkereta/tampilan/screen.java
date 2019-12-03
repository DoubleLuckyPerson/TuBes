package id.ac.polinema.tiketkereta.tampilan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import id.ac.polinema.tiketkereta.R;

public class screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(screen.this, Login.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}
