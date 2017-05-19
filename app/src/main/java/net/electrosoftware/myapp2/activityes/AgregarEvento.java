package net.electrosoftware.myapp2.activityes;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import net.electrosoftware.myapp2.R;

import java.util.Calendar;

import fr.ganfra.materialspinner.MaterialSpinner;

public class AgregarEvento extends AppCompatActivity {

    ArrayAdapter adapterCategorias, adapterPatrocinador;
    MaterialSpinner spinner_evento_categoria, spinner_evento_patrocinador;
    EditText et_evento_nombre, et_evento_telefono, et_evento_fechainicio, et_evento_fechafin, et_evento_horainicio, et_evento_horafin, et_evento_descripcion;
    ImageButton btn_evento_fechainicio, btn_evento_fechafin, btn_evento_horainicio, btn_evento_horafin;
    Button btn_evento_cancelar, btn_evento_continuar;

    Calendar calendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_agregar_evento);

        et_evento_nombre = (EditText) findViewById(R.id.et_evento_nombre);
        et_evento_telefono = (EditText) findViewById(R.id.et_evento_telefono);

        calendar = Calendar.getInstance();

        adapterCategorias = ArrayAdapter.createFromResource(AgregarEvento.this, R.array.Categorias, android.R.layout.simple_spinner_item);
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterPatrocinador = ArrayAdapter.createFromResource(AgregarEvento.this, R.array.Patrocinadores, android.R.layout.simple_spinner_item);
        adapterPatrocinador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_evento_categoria = (MaterialSpinner) findViewById(R.id.spinner_evento_categoria);
        spinner_evento_categoria.setAdapter(adapterCategorias);

        spinner_evento_patrocinador = (MaterialSpinner) findViewById(R.id.spinner_evento_patrocinador);
        spinner_evento_patrocinador.setAdapter(adapterPatrocinador);

        et_evento_fechainicio = (EditText) findViewById(R.id.et_evento_fechainicio);
        btn_evento_fechainicio = (ImageButton) findViewById(R.id.btn_evento_fechainicio);
        btn_evento_fechainicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = new DatePickerDialog(AgregarEvento.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                et_evento_fechainicio.setText(new StringBuilder().append(monthOfYear + 1).append("/").append(dayOfMonth).append("/").append(year).append(" "));
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dpd.show();
            }
        });

        et_evento_fechafin = (EditText) findViewById(R.id.et_evento_fechafin);
        btn_evento_fechafin = (ImageButton) findViewById(R.id.btn_evento_fechafin);
        btn_evento_fechafin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = new DatePickerDialog(AgregarEvento.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                et_evento_fechafin.setText(new StringBuilder().append(monthOfYear + 1).append("/").append(dayOfMonth).append("/").append(year).append(" "));
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dpd.show();
            }
        });

        et_evento_horainicio = (EditText) findViewById(R.id.et_evento_horainicio);
        btn_evento_horainicio = (ImageButton) findViewById(R.id.btn_evento_horainicio);
        btn_evento_horainicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(AgregarEvento.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String AM_PM = "";
                                String hourString = "";

                                if (hourOfDay < 12) {
                                    AM_PM = "am";
                                    hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
                                } else {
                                    AM_PM = "pm";
                                    hourString = (hourOfDay - 12) < 10 ? "0" + (hourOfDay - 12) : "" + (hourOfDay - 12);
                                }
                                String minuteString = minute < 10 ? "0" + minute : "" + minute;

                                if (hourString.equalsIgnoreCase("00")) {
                                    hourString = "12";
                                }

                                String time = hourString + ":" + minuteString + " " + AM_PM;

                                et_evento_horainicio.setText(time);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
                timePickerDialog.show();
            }
        });

        et_evento_horafin = (EditText) findViewById(R.id.et_evento_horafin);
        btn_evento_horafin = (ImageButton) findViewById(R.id.btn_evento_horafin);
        btn_evento_horafin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(AgregarEvento.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String AM_PM = "";
                                String hourString = "";

                                if (hourOfDay < 12) {
                                    AM_PM = "am";
                                    hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
                                } else {
                                    AM_PM = "pm";
                                    hourString = (hourOfDay - 12) < 10 ? "0" + (hourOfDay - 12) : "" + (hourOfDay - 12);
                                }
                                String minuteString = minute < 10 ? "0" + minute : "" + minute;
                                String time = hourString + ":" + minuteString + " " + AM_PM;

                                et_evento_horafin.setText(time);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
                timePickerDialog.show();
            }
        });

        btn_evento_cancelar = (Button) findViewById(R.id.btn_evento_cancelar);
        btn_evento_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_evento_continuar = (Button) findViewById(R.id.btn_evento_continuar);
        btn_evento_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AgregarEvento.this, AgregarEventoMapa.class));
            }
        });

        super.onCreate(savedInstanceState);
    }
}
