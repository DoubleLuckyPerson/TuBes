package id.ac.polinema.tiketkereta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.ac.polinema.tiketkereta.fragment.Pemesanan;
import id.ac.polinema.tiketkereta.fragment.ResultFragment;
import id.ac.polinema.tiketkereta.fragment.information;
import id.ac.polinema.tiketkereta.fragment.schedule;
import id.ac.polinema.tiketkereta.tampilan.Login;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, Pemesanan.OnFragmentInteractionListener
            , ResultFragment.OnFragmentInteractionListener {


    private ResultFragment resultFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new Pemesanan());
        resultFragment = new ResultFragment();
    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.action_ticket:
                fragment = new Pemesanan();
                break;
            case R.id.action_schedule:
                fragment = new schedule();
                break;
            case R.id.action_info:
                fragment = new information();
                break;
                default:fragment=null;
        }
        return loadFragment(fragment);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void onSubmitClicked(int penumpang,int total){
        String simpan = String.valueOf(total);
        String jumlah = String.valueOf(penumpang);
        resultFragment.setInfo("Silakan Lakukan Pembayaran sebesar : "+simpan+"\nJumlah Penumpang : "+jumlah);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, resultFragment)
                .commit();
    }

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//
//    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
}
