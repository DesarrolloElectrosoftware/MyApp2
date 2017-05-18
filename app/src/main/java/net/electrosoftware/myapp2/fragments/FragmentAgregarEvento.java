package net.electrosoftware.myapp2.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import net.electrosoftware.myapp2.R;

import java.util.Calendar;

import fr.ganfra.materialspinner.MaterialSpinner;

public class FragmentAgregarEvento extends Fragment {

    private View view;
    ArrayAdapter adapterCategorias, adapterPatrocinador;
    MaterialSpinner spinner_evento_categoria, spinner_evento_patrocinador;
    EditText et_evento_nombre, et_evento_telefono, et_evento_fechainicio, et_evento_fechafin, et_evento_horainicio, et_evento_horafin, et_evento_descripcion;
    ImageButton btn_evento_fechainicio, btn_evento_fechafin, btn_evento_horainicio, btn_evento_horafin;
    Button btn_evento_cancelar, btn_evento_continuar;

    Calendar calendar;

    public FragmentAgregarEvento() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_agregar_evento, container, false);
        // Inflate the layout for this fragment

        et_evento_nombre = (EditText) view.findViewById(R.id.et_evento_nombre);
        et_evento_telefono = (EditText) view.findViewById(R.id.et_evento_telefono);

        calendar = Calendar.getInstance();

        adapterCategorias = ArrayAdapter.createFromResource(getActivity(), R.array.Categorias, android.R.layout.simple_spinner_item);
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterPatrocinador = ArrayAdapter.createFromResource(getActivity(), R.array.Patrocinadores, android.R.layout.simple_spinner_item);
        adapterPatrocinador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_evento_categoria = (MaterialSpinner) view.findViewById(R.id.spinner_evento_categoria);
        spinner_evento_categoria.setAdapter(adapterCategorias);

        spinner_evento_patrocinador = (MaterialSpinner) view.findViewById(R.id.spinner_evento_patrocinador);
        spinner_evento_patrocinador.setAdapter(adapterPatrocinador);

        et_evento_fechainicio = (EditText) view.findViewById(R.id.et_evento_fechainicio);
        btn_evento_fechainicio = (ImageButton) view.findViewById(R.id.btn_evento_fechainicio);
        btn_evento_fechainicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = new DatePickerDialog(getActivity(),
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

        et_evento_fechafin = (EditText) view.findViewById(R.id.et_evento_fechafin);
        btn_evento_fechafin = (ImageButton) view.findViewById(R.id.btn_evento_fechafin);
        btn_evento_fechafin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                et_evento_fechafin.setText(new StringBuilder().append(monthOfYear + 1).append("/").append(dayOfMonth).append("/").append(year).append(" "));
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dpd.show();
            }
        });

        et_evento_horainicio = (EditText) view.findViewById(R.id.et_evento_horainicio);
        btn_evento_horainicio = (ImageButton) view.findViewById(R.id.btn_evento_horainicio);
        btn_evento_horainicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
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

                                et_evento_horainicio.setText(time);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
                timePickerDialog.show();
            }
        });

        et_evento_horafin = (EditText) view.findViewById(R.id.et_evento_horafin);
        btn_evento_horafin = (ImageButton) view.findViewById(R.id.btn_evento_horafin);
        btn_evento_horafin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
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

        btn_evento_cancelar = (Button) view.findViewById(R.id.btn_evento_cancelar);
        btn_evento_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        btn_evento_continuar = (Button) view.findViewById(R.id.btn_evento_continuar);
        btn_evento_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

}
