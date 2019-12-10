package id.ac.polinema.tiketkereta.tampilan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import id.ac.polinema.tiketkereta.R;

public class Register extends AppCompatActivity {

    private Button btnlogin, btnregister;
    private EditText Nama, Email, Password;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Nama = findViewById(R.id.reg_input_nama);
        Email = findViewById(R.id.reg_input_email);
        Password = findViewById(R.id.reg_input_pass);
        btnregister = findViewById(R.id.btn_reg);
        btnlogin = findViewById(R.id.btn_link_login);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nama = Nama.getText().toString().trim();
                final String email = Email.getText().toString().trim();
                final String pass = Password.getText().toString().trim();

                if(nama.isEmpty() || email.isEmpty() || pass.isEmpty()){
                    if(nama.isEmpty()){
                        Nama.setError("Masukkan Nama Anda");
                    } else if(email.isEmpty()){
                        Email.setError("Masukkan Email Anda");
                    } else if(pass.isEmpty()){
                        Password.setError("Masukkan Password Anda");
                    }
                }else if(pass.length() < 6){
                    Password.setError("Password Minimal 6 Karakter");
                }
                else{
                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        Toast.makeText(Register.this, "Register Berhasil!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Register.this, Login.class);
                                        startActivity(intent);
                                    } else{
                                        Toast.makeText(Register.this, "Register Gagal! " +task.getException().getMessage()
                                        , Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
