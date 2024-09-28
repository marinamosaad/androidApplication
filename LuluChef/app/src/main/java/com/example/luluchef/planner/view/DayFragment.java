package com.example.luluchef.planner.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.luluchef.R;
import com.example.luluchef.database.LocalSource;
import com.example.luluchef.model.PlanedMeal;
import com.example.luluchef.model.Repo.MealRepository;
import com.example.luluchef.network.APIClient;
import com.example.luluchef.planner.Presenter.PlanPresenter;

import java.security.PrivateKey;
import java.util.Calendar;
import java.util.Date;

public class DayFragment extends DialogFragment implements PlanView {

    CalendarView calendarView ;
    private Button selectDateButton;
    private Date selectedDate;
    private PlanPresenter presenter;
    private MealRepository repo;
    private LocalSource localSource;
    private APIClient client;
    private String idMeal;


    public DayFragment(String idMeal) {

        this.idMeal = idMeal;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idMeal = getArguments().getString("mealId"); // Receive meal ID from arguments
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    //    return inflater.inflate(R.layout.fragment_day, container, false);

        View view = inflater.inflate(R.layout.fragment_day, container, false);

        calendarView = view.findViewById(R.id.calendarView);

        client = APIClient.getInstance();
        localSource = LocalSource.getInstance(view.getContext());
        repo = MealRepository.getInstance(localSource , client);
        presenter = new PlanPresenter(this , repo);
        // Set a listener for the calendar view to capture the selected date
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            selectedDate = calendar.getTime();
            saveMealWithDate();  // Save the meal with the selected date
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* calendarView = view.findViewById(R.id.calendarView);
        // Set a listener for the calendar view
        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            // Convert the selected date to a Date object
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            selectedDate = calendar.getTime();
            saveMealWithDate(); // Save the meal with the selected date

        });*/

    }

    private void saveMealWithDate() {
        if (selectedDate != null && idMeal != null) {
            PlanedMeal plannedMeal = new PlanedMeal(idMeal, selectedDate);
            presenter.AddtoPlannedTable(plannedMeal, selectedDate); // Save the planned meal in the database
            Toast.makeText(getContext(), "Meal saved for " + selectedDate, Toast.LENGTH_SHORT).show();
            // Optionally navigate back or close the fragment after saving
                dismiss();
        }
    }
}