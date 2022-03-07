package com.ezeetapAssignment.ezeesignup.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.ezeetapAssignment.ezeesignup.R
import com.ezeetapAssignment.ezeesignup.databinding.ActivitySignUpBinding
import com.ezeetapAssignment.ezeesignup.model.LoginInfo
import com.ezeetapAssignment.ezeesignup.model.presenter.Presenter
import com.ezeetapAssignment.ezeesignup.network.RetrofitInstance
import com.ezeetapAssignment.ezeesignup.viewmodel.SignupViewModel
import com.ezeetapAssignment.utils.ConnectionLiveData
import com.ezeetapAssignment.utils.Constants

class SignUpActivity : AppCompatActivity() {
    lateinit var name: String
    lateinit var city: String
    lateinit var connectionTest: ConnectionLiveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activitySinup =
            DataBindingUtil.setContentView<ActivitySignUpBinding>(this, R.layout.activity_sign_up)
        val viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        connectionTest = ConnectionLiveData(this)
        connectionTest.observe(this) {
            when (it) {
                "WIFI" -> {
                    val getUserUiRest = RetrofitInstance()
                    getUserUiRest.getDynamicUI(Constants.SIGN_UP_URL) { response ->
                        if (response != null) {
                            Glide.with(this).load(response.logo).into(activitySinup.imgLogo)
                            if (response.uidata.isNotEmpty()) {
                                for (i in response.uidata) {
                                    if (i.key != null) {
                                        when (i.key) {
                                            "label_name" -> activitySinup.lyEtUserName.hint =
                                                i.value
                                            "text_name" -> activitySinup.etUsername.setText(i.hint)
                                            "label_phone" -> activitySinup.lyEtPhone.hint = i.value
                                            "text_phone" -> activitySinup.etPhoneNum.setText(i.hint)
                                            "label_city" -> activitySinup.lyEtCity.hint = i.value
                                            "text_city" -> activitySinup.etCityName.setText(i.hint)
                                        }
                                    } else {
                                        if (i.uitype == "button") {
                                            activitySinup.btnSubmit.text = i.value
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                "MOBILE" -> {
                    val getUserUiRest = RetrofitInstance()
                    getUserUiRest.getDynamicUI(Constants.SIGN_UP_URL) { response ->
                        if (response != null) {

                            if (response.uidata.isNotEmpty()) {
                                for (i in response.uidata) {
                                    when (i.key) {
                                        "label_name" -> activitySinup.lyEtUserName.hint = i.key
                                        "text_name" -> activitySinup.etUsername.setText(i.hint)
                                        "label_phone" -> activitySinup.lyEtPhone.hint = i.key
                                        "text_phone" -> activitySinup.etPhoneNum.setText(i.hint)
                                        "label_city" -> activitySinup.lyEtCity.hint = i.key
                                        "text_city" -> activitySinup.etCityName.setText(i.hint)
                                    }
                                    if (i.uitype == "button") {
                                        activitySinup.btnSubmit.text = i.value
                                    }
                                }
                            }
                        }
                    }
                }
                "NONE" -> {
                    activitySinup.imgLogo.visibility = View.GONE
                    activitySinup.btnSubmit.visibility = View.GONE
                    activitySinup.etUsername.visibility = View.GONE
                    activitySinup.lyEtUserName.visibility = View.GONE
                    activitySinup.etCityName.visibility = View.GONE
                    activitySinup.lyEtCity.visibility = View.GONE
                    activitySinup.lyEtPhone.visibility = View.GONE
                    activitySinup.etPhoneNum.visibility = View.GONE
                    activitySinup.txtIsConnected.visibility = View.VISIBLE
                }
                else -> {
                    activitySinup.imgLogo.visibility = View.GONE
                    activitySinup.btnSubmit.visibility = View.GONE
                    activitySinup.etUsername.visibility = View.GONE
                    activitySinup.lyEtUserName.visibility = View.GONE
                    activitySinup.etCityName.visibility = View.GONE
                    activitySinup.lyEtCity.visibility = View.GONE
                    activitySinup.lyEtPhone.visibility = View.GONE
                    activitySinup.etPhoneNum.visibility = View.GONE
                    activitySinup.txtIsConnected.visibility = View.VISIBLE
                }
            }
        }

        activitySinup.presenter = object : Presenter {
            override fun onSubmit() {
                val userName = activitySinup.etUsername.text.toString()
                val phoneNum = activitySinup.etPhoneNum.text.toString()
                val cityName = activitySinup.etCityName.text.toString()

                if (userName.isEmpty()) {
                    activitySinup.etUsername.error = "Please Enter the Name"
                } else if (!isValidPhone(phoneNum)) {
                    activitySinup.etPhoneNum.error = "Please Enter the Phone Number"
                } else if (cityName.isEmpty()) {
                    activitySinup.etCityName.error = "Please Enter the city"
                } else {
                    name = userName
                    city = cityName
                    val userDetails = LoginInfo(userName, phoneNum.toInt(), cityName)
                    viewModel.sumbitData(userDetails)
                }
            }
        }

        viewModel.getUserDetails().observe(this, Observer {
            if (it == "Success") {
                val intent = Intent(this, DisplayActivity::class.java)
                intent.putExtra("User",name)
                intent.putExtra("City",city)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        })


//      fun isNetNotAvailable(){
//
//        }
    }

    fun isValidPhone(pNo: String): Boolean {
        return android.util.Patterns.PHONE.matcher(pNo).matches()
    }

}