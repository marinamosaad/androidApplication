package com.example.luluchef.home.view;

import com.example.luluchef.model.Meal;

import java.util.List;

public interface HomeView {
    void showMeals(List<Meal> meals);
    void showErr(String error);
}
