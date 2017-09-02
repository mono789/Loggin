package com.juan.loggin;


import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity{
    public TextView tDate, tInformacion;
    private Spinner sCiudades;
    private CheckBox cCine, cCaminar, cCantar, cBailar;
    private String hobbies="", ciudad, loggin, password, rpassword, mail, sexo, date;
    private Button bAceptar;
    private EditText eLoggin, ePassword, eRPassword, eMail;
    private RadioButton rMasculino, rFemenino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tDate= (TextView) findViewById(R.id.tDate);
        sCiudades= (Spinner) findViewById(R.id.sCiudades);
        cCine= (CheckBox) findViewById(R.id.cCine);
        cCaminar= (CheckBox) findViewById(R.id.cCaminar);
        cCantar= (CheckBox) findViewById(R.id.cCantar);
        cBailar= (CheckBox) findViewById(R.id.cBailar);
        eLoggin= (EditText) findViewById(R.id.eLoggin);
        ePassword= (EditText) findViewById(R.id.ePassword);
        eRPassword= (EditText) findViewById(R.id.eRPassword);
        eMail= (EditText) findViewById(R.id.eMail);
        tInformacion= (TextView) findViewById(R.id.tInfomacion);
        bAceptar= (Button) findViewById(R.id.bAceptar);
        rMasculino= (RadioButton) findViewById(R.id.rMasculino);
        rFemenino= (RadioButton) findViewById(R.id.rFemenino);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sCiudades.setAdapter(adapter);
        sCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudad= adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggin= eLoggin.getText().toString();
                password= ePassword.getText().toString();
                rpassword= eRPassword.getText().toString();
                mail= eMail.getText().toString();
                date= tDate.getText().toString();

                if(loggin.isEmpty() || password.isEmpty() || rpassword.isEmpty()||mail.isEmpty() || date.isEmpty() ){
                    tInformacion.setText("Fields are missing.");
                }
                else{
                    if(password.equals(rpassword)){
                        if (cCine.isChecked()){hobbies+="Movies ";}
                        if (cCantar.isChecked()){hobbies+="Singing ";}
                        if (cCaminar.isChecked()){hobbies+="Walking ";}
                        if (cBailar.isChecked()){hobbies+="Dancing ";}
                        if (rMasculino.isChecked()){
                            sexo= "Male";
                        }
                        else{
                            sexo= "Female";
                        }

                        tInformacion.setText("Loggin: "+loggin+"\nPassword: "+password+"\nE-mail: "+mail+"\nHobbies: "+hobbies+"\nSex: "+sexo+"\nBirth place: "+ciudad+"\nBirth date: "+date);
                        hobbies="";
                    }
                    else{
                        tInformacion.setText("The passwords are different.");
                    }
                }


            }
        });


    }
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            month+=1;
            TextView tDate= getActivity().findViewById(R.id.tDate);
            String date= month+"/"+day+"/"+year;
            tDate.setText(date);

        }
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }


}