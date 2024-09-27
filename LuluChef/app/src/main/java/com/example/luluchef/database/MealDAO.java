package com.example.luluchef.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.lifecycle.LiveData;

import com.example.luluchef.model.Meal;
import com.example.luluchef.model.PlanedMeal;

import java.util.List;

@Dao
public interface MealDAO {

   // @Query("SELECT * FROM meals_table")
   // LiveData<List<Meal>> getMeals();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(Meal meal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllFav(List<Meal> meals);

    @Delete
    void deleteMeal(Meal product);

    @Query("DELETE FROM meals_table")
    void deleteAllMeals();

    @Query("SELECT * FROM meals_table WHERE idMeal = :id LIMIT 1")
    Meal getMealById(String id);


    @Query("SELECT * FROM meals_table")
    LiveData<List<Meal>> getAllMeals();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPLannedMeal(PlanedMeal meal);

    @Delete
    void deletePlannedMeal(PlanedMeal meal);

    @Query("SELECT * From planMeal_table WHERE dayOfMeal = :day ")
    LiveData<List<PlanedMeal>> getMealsOfDay(String day);
}
