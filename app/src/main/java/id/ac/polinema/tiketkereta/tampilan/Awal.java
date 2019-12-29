package id.ac.polinema.tiketkereta.tampilan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.ac.polinema.tiketkereta.Main2Activity;
import id.ac.polinema.tiketkereta.MainActivity;
import id.ac.polinema.tiketkereta.R;

public class Awal extends AppCompatActivity {
    Button btTambah;
    Button btjadwal;
    Button btinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);

        btTambah = findViewById(R.id.btCreate);
        btjadwal = findViewById(R.id.btJadwal);
        btinfo = findViewById(R.id.btInfo);

        btTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Awal.this, FormPesan.class);
                startActivity(intent);
                finish();
            }
        });

        btjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Awal.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Awal.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
