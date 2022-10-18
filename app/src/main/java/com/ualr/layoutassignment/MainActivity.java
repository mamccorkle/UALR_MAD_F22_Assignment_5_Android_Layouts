// https://github.com/UALR-MobileAppsDevelopmentCourse/assignment-05-mamccorkle
//
//  UALR - MAD - F22 - Assignment 5 - Android Layout
//
//  Project: UALR - Mobile Applications Development - Fall 2022 - Assignment 5 - Android Layout
//  Created by: Mark McCorkle on 20221012
//  Based on: Code Provided by Dr Ivan Rodriguez-Conde
//
//  IDE:
//     Android Studio Chipmunk | 2021.2.1 Patch 2
//     Build #AI-212.5712.43.2112.8815526, built on July 10, 2022
//     Runtime version: 11.0.12+0-b1504.28-7817840 x86_64
//     VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
//     macOS 11.5.2
//     GC: G1 Young Generation, G1 Old Generation
//     Memory: 2048M
//     Cores: 8
//     Registry: external.system.auto.import.disabled=true
//
package com.ualr.layoutassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.ualr.layoutassignment.databinding.ActivityMainBinding;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Add the binder:
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the discount to NO discount:
        binding.btnNoDiscount.setChecked(true);

        // Add button listener for inactive "clickables":
        binding.chipScan.setOnClickListener(this::onInactiveClicked);
        binding.chipTopUp.setOnClickListener(this::onInactiveClicked);
        binding.chipSend.setOnClickListener(this::onInactiveClicked);
        binding.chipRequest.setOnClickListener(this::onInactiveClicked);
        binding.btnBuyNow.setOnClickListener(this::onInactiveClicked);

        // Add button listener for the total calculations button:
        binding.btnCalculateTotal.setOnClickListener(this::calculateTotal);

//        // Set up listeners for when the user has moved from on product to another so calculations can be performed:
//        binding.tietProduct01.setOnFocusChangeListener((view, hasFocus) -> calculateTotal(view));
//        binding.tietProduct02.setOnFocusChangeListener((view, hasFocus) -> calculateTotal(view));
//        binding.tietProduct03.setOnFocusChangeListener((view, hasFocus) -> calculateTotal(view));
//        binding.tietProduct04.setOnFocusChangeListener((view, hasFocus) -> calculateTotal(view));
    }

    public void calculateTotal(View view) {
        // Define the discount:
        double discount = 25.0;

        // Set up a variable to return:
        double returnAmount = 0.0;

        // Get the value stored in each products cost textView:
        if(!Objects.requireNonNull(binding.tietProduct01.getText()).toString().isEmpty()) {
            // If the amount is entered, but not checked...check it:
            if (!binding.cbProduct01.isChecked())
                binding.cbProduct01.setChecked(true);

            // Add the amount to the running total:
            returnAmount += Double.parseDouble(binding.tietProduct01.getText().toString());
        }
        if(!Objects.requireNonNull(binding.tietProduct02.getText()).toString().isEmpty()) {
            // If the amount is entered, but not checked...check it:
            if (!binding.cbProduct02.isChecked())
                binding.cbProduct02.setChecked(true);

            // Add the amount to the running total:
            returnAmount += Double.parseDouble(binding.tietProduct02.getText().toString());
        }
        if(!Objects.requireNonNull(binding.tietProduct03.getText()).toString().isEmpty()) {
            // If the amount is entered, but not checked...check it:
            if (!binding.cbProduct03.isChecked())
                binding.cbProduct03.setChecked(true);

            // Add the amount to the running total:
            returnAmount += Double.parseDouble(binding.tietProduct03.getText().toString());
        }
        if(!Objects.requireNonNull(binding.tietProduct04.getText()).toString().isEmpty()) {
            // If the amount is entered, but not checked...check it:
            if (!binding.cbProduct04.isChecked())
                binding.cbProduct04.setChecked(true);

            // Add the amount to the running total:
            returnAmount += Double.parseDouble(binding.tietProduct04.getText().toString());
        }

        // If the value is zero, no product has been selected or no product values have been entered:
        if(returnAmount == 0) {
            // Show a warning to the user that non of the product costs have been entered and are empty:
            Snackbar snackbar = Snackbar.make(view, R.string.zero_value_warning, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
        else {
            // Format the amount to represent dollars:
            DecimalFormat formattedDollars = new DecimalFormat("$ ###,##0.00");

            // Calculate the discount:
            if(binding.btnDiscount.isChecked())
                returnAmount *= (100 - discount) / 100;

            // Set the textView to the total calculated amount:
            binding.tvSum.setText(formattedDollars.format(returnAmount));
        }
    }

    public void onInactiveClicked(View view) {
        // Create the Snackbar to be displayed to the user:
        Snackbar snackbar = Snackbar.make(view, R.string.inactive, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

}