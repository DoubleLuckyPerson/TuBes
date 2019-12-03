package id.ac.polinema.tiketkereta.tampilan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import org.w3c.dom.Text;

import java.util.Calendar;

import id.ac.polinema.tiketkereta.R;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savaedInstanceState){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        TextView tv = (TextView) getActivity().findViewById(R.id.tv);
//        tv.setText("Date changed...");
//        tv.setText(tv.getText() + "\nYear: " + year);
//        tv.setText(tv.getText() + "\nMonth: " + month);
//        tv.setText(tv.getText() + "\nDay of Month: " + day);
        String stringOfDate = day + "/" + month + "/" + year;
        tv.setText(tv.getText() + "\n\nTanggal Pesan: " + stringOfDate);

    }
}
