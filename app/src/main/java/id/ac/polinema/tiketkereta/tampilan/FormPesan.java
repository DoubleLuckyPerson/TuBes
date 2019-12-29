package id.ac.polinema.tiketkereta.tampilan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.ac.polinema.tiketkereta.Models.Penumpang;
import id.ac.polinema.tiketkereta.R;

import static android.text.TextUtils.isEmpty;

public class FormPesan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatabaseReference databaseReference;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private Button btSimpan, btHitung;
    private EditText etNama;
    private EditText etAlamat;
    private EditText etNotlp;
    private EditText etjp;
    private TextView tglb, total;
    private Button btDatePicker;
    private Spinner asal,tujuan;
    private RadioGroup radiokereta;
    private RadioButton radiok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pesan);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        etNama = findViewById(R.id.nama);
        etAlamat = findViewById(R.id.alamat);
        etNotlp = findViewById(R.id.notlp);
        etjp = findViewById(R.id.jp);
        tglb = findViewById(R.id.tgl_brgkt);
        btDatePicker = findViewById(R.id.bt_datepicker);
        asal = findViewById(R.id.spinner_asal);
        tujuan = findViewById(R.id.spinner_tujuan);
        radiokereta = findViewById(R.id.radioGroupKA);
        radiok = findViewById(radiokereta.getCheckedRadioButtonId());
        total = findViewById(R.id.totBayar);
        btHitung = findViewById(R.id.buttonHitung);
        btSimpan = findViewById(R.id.buttonSubmit);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.kota, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        asal.setAdapter(adapter);
        asal.setOnItemSelectedListener(this);
        tujuan.setAdapter(adapter);
        tujuan.setOnItemSelectedListener(this);



        btHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jumlah = etjp.getText().toString();
                final int jumlahpenumpang = Integer.parseInt(jumlah);
                int selected = radiokereta.getCheckedRadioButtonId();
                radiok = findViewById(selected);
                int harga;
                if(radiok.getText().equals("Majapahit")){
                    harga = jumlahpenumpang * 300000;
                } else{
                    harga = jumlahpenumpang * 110000;
                }
                String output = String.valueOf(harga);
                total.setText(output);
            }
        });

        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                //date picker dialog
                datePickerDialog = new DatePickerDialog(FormPesan.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                tglb.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });





        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String krt = radiok.getText().toString().trim();
                String jumlah = etjp.getText().toString();
                final int jumlahpenumpang = Integer.parseInt(jumlah);

                if(!isEmpty(etNama.getText().toString()) && !isEmpty(etAlamat.getText().toString())
                && !isEmpty(etNotlp.getText().toString()) && !isEmpty(etjp.getText().toString())){
                    submitPesan(new Penumpang(etNama.getText().toString(), etAlamat.getText().toString(),
                            etNotlp.getText().toString(), jumlahpenumpang, tglb.getText().toString(),
                            asal.getSelectedItem().toString(), tujuan.getSelectedItem().toString(), krt, total.getText().toString()));
                    Intent intent = new Intent(FormPesan.this, Awal.class);
                    startActivity(intent);
                    finish();}

                else
                    Snackbar.make(findViewById(R.id.buttonSubmit), "Data barang tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                    InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                        etNama.getWindowToken(), 0);
            }
        });
    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void submitPesan(Penumpang penumpang) {
        databaseReference.child("Pemesan").push()
                .setValue(penumpang)
                .addOnSuccessListener(FormPesan.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        etNama.setText("");
                        etAlamat.setText("");
                        etNotlp.setText("");
                        etjp.setText("");
                        tglb.setText("");
                        total.setText("");
                        Snackbar.make(findViewById(R.id.buttonSubmit), "Data Berhasil ditambahkan",
                                Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent(this, Awal.class);
        startActivity(intent);
        finish();
    }
}
