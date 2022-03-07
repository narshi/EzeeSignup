package com.ezeetapAssignment.ezeesignup.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ezeetapAssignment.ezeesignup.R
import com.ezeetapAssignment.ezeesignup.databinding.ActivityDisplayBinding
import com.ezeetapAssignment.ezeesignup.viewmodel.DisplayViewModel
import java.lang.StringBuilder

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDisplay = DataBindingUtil.setContentView<ActivityDisplayBinding>(this,R.layout.activity_display)
        val displayViewModel = ViewModelProvider(this).get(DisplayViewModel::class.java)


        val userName = intent.getStringExtra("User")
        val cityName =  intent.getStringExtra("City")
        if (userName.isNullOrEmpty()||cityName.isNullOrEmpty()){
            activityDisplay.textDisplay.text = "User Authentication Failed Please Sign up Again"
        }else{
            activityDisplay.textDisplay.text = "Welcome $userName from $cityName to Ezeetap."
        }

    }
}