package id.ac.polinema.tiketkereta.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import id.ac.polinema.tiketkereta.R;
import id.ac.polinema.tiketkereta.Util.Pesan;

public class Pemesanan extends Fragment{

    private OnFragmentInteractionListener mListener;
    private ResultFragment resultFragment;

    public Pemesanan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pemesanan, container, false);
        final EditText name = view.findViewById(R.id.nameText);
        final EditText address = view.findViewById(R.id.addressText);
        final EditText tlp = view.findViewById(R.id.telp);
        final EditText jp = view.findViewById(R.id.jmlpenumpang);
        final RadioGroup keretagroup = view.findViewById(R.id.kereta);

        Button submit = view.findViewById(R.id.buttonsubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    String nm = name.getText().toString();
                    String ad = address.getText().toString();
                    String tl = tlp.getText().toString();
                    String penumpangString = jp.getText().toString();
                    int checkedId = keretagroup.getCheckedRadioButtonId();
                    int jumlah = Integer.parseInt(penumpangString);

                    if((checkedId != -1) && !TextUtils.isEmpty(penumpangString)){

                        int k = (checkedId == R.id.gajayana) ? Pesan.GAJAYANA : Pesan.MATARMAJA;
                        Pesan pesan = new Pesan(jumlah, k);
                        mListener.onSubmitClicked(pesan.getPenumpang(),pesan.getTotal());
                    }  else if(TextUtils.isEmpty(nm)){
                        Toast.makeText(getActivity(), "Nama Harus Diisi!", Toast.LENGTH_SHORT).show();
                    } else if(TextUtils.isEmpty(ad)){
                        Toast.makeText(getActivity(), "Alamat Harus Diisi!", Toast.LENGTH_SHORT).show();
                    } else if(TextUtils.isEmpty(tl)){
                        Toast.makeText(getActivity(), "Nomor Telepon Harus Diisi!", Toast.LENGTH_SHORT).show();
                    } else if(TextUtils.isEmpty(penumpangString)){
                        Toast.makeText(getActivity(), "Jumlah Penumpang Harus Diisi!", Toast.LENGTH_SHORT).show();
                    } else if(TextUtils.isEmpty(nm) && TextUtils.isEmpty(ad) && TextUtils.isEmpty(tl) && TextUtils.isEmpty(penumpangString) && checkedId == -1){
                        Toast.makeText(getActivity(), "Tidak ada data yang diisi", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Silakan Pilih Kereta!", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(getActivity(), "Tidak Ada data yang diisi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
        void onSubmitClicked(int penumpang,int total);
    }
}
