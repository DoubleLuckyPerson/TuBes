package id.ac.polinema.tiketkereta.tampilan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import id.ac.polinema.tiketkereta.MainActivity;
import id.ac.polinema.tiketkereta.R;

public class Login extends AppCompatActivity {

    private EditText txtemail,txtpass;
    private TextView reg;
    private Button log;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtemail = findViewById(R.id.username);
        txtpass = findViewById(R.id.password);
        log = findViewById(R.id.btnlogin);
        reg = findViewById(R.id.txtregister);
        firebaseAuth = FirebaseAuth.getInstance();

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtemail.getText().toString().trim();
                String password = txtpass.getText().toString().trim();
                if(email.isEmpty()){
                    txtemail.setError("Masukkan Email");
                } else if(password.isEmpty()){
                    txtpass.setError("Masukkan Password");
                } else if( email.isEmpty() && password.isEmpty()){
                    txtemail.setError("Masukkan Email");
                    txtpass.setError("Masukkan Password");
                } else{
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else{
                                    Toast.makeText(Login.this, "Proses Login Gagal : " +task.getException(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
//                finish();
            }
        });
    }

//    public void authenticateLogin() {
//            if(user.getText().toString().equals("Fadila") && pass.getText().toString().equals("fadila")){
//                Toast.makeText(getApplicationContext(), "Login Sukses!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Login.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            } else{
//                Toast.makeText(getApplicationContext(), "Username/Password Anda Salah", Toast.LENGTH_SHORT).show();
//                user.setText("");
//                pass.setText("");
//        }
//            }
}
