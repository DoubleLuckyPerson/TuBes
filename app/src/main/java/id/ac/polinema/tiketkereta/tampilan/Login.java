package id.ac.polinema.tiketkereta.tampilan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.polinema.tiketkereta.MainActivity;
import id.ac.polinema.tiketkereta.R;

public class Login extends AppCompatActivity {

    private EditText user,pass;
    private Button log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        log = findViewById(R.id.btnlogin);
    }

    public void authenticateLogin(View view) {
        if(user.getText().toString().equals("Fadila") && pass.getText().toString().equals("fadila")){
            Toast.makeText(getApplicationContext(), "Login Sukses!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
//            finish();
        } else{
            Toast.makeText(getApplicationContext(), "Username/Password Anda Salah", Toast.LENGTH_SHORT).show();
            user.setText("");
            pass.setText("");
        }
    }
}
