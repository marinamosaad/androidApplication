package com.example.luluchef.planner.Presenter;

import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;

import java.util.Date;

public interface PlanPresenterInterface {

    public void AddtoPlannedTable(PlanedMeal meal , Date date) ;
    public void addToFavourite(Meal meal);


}
