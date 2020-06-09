package com.gzeinnumer.newmaterialdaterangepicker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btnDateRange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();

                Calendar f = Calendar.getInstance();
                f.add(Calendar.MONTH, 0);
                Calendar e = Calendar.getInstance();
                e.add(Calendar.MONTH,0);
                CalendarConstraints.Builder calendarConstraints = new CalendarConstraints.Builder();
                calendarConstraints.setStart(f.getTimeInMillis());
                calendarConstraints.setEnd(e.getTimeInMillis());

                builder.setCalendarConstraints(calendarConstraints.build());
                MaterialDatePicker<Pair<Long, Long>> picker = builder.build();
                picker.show(getChildFragmentManager(),"date_picker_tag");

                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
                    @Override
                    public void onPositiveButtonClick(Pair<Long, Long> selection) {
                        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String awal = formater.format(new Date(selection.first));
                        String akhir = formater.format(new Date(selection.second));
                        Toast.makeText(requireContext(), awal +" - "+akhir, Toast.LENGTH_SHORT).show();
                    }
                });
                picker.addOnNegativeButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(requireContext(), "Tidak jadi memilih", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        view.findViewById(R.id.btnDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                MaterialDatePicker<Long> picker = builder.build();
                picker.show(getChildFragmentManager(), "date_picker_tag");

                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String now = formater.format(new Date(selection));
                        Toast.makeText(requireContext(), now, Toast.LENGTH_SHORT).show();
                    }
                });

                picker.addOnNegativeButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(requireContext(), "Tidak jadi memilih", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}