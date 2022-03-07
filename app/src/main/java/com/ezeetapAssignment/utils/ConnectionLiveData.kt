package com.ezeetapAssignment.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.LiveData
import java.lang.Exception

class ConnectionLiveData(private val context: Context) : LiveData<String>(){

    var isWifi: String = ""
    var noConnection: Boolean = false

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            postValue(context?.isConnected)
        }
    }

    override fun onActive() {
        super.onActive()
        context.registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onInactive() {
        super.onInactive()
        try {
            context.unregisterReceiver(networkReceiver)
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    val Context.isConnected: String?
        get() {
            isWifi = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                when {
                    (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).getNetworkCapabilities(
                        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork
                    )
                        ?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) != null && (getSystemService(
                        Context.CONNECTIVITY_SERVICE) as ConnectivityManager).getNetworkCapabilities(
                        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork
                    )?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> {
                        Toast.makeText(context, "Wifi Connection", Toast.LENGTH_SHORT).show()
                        "WIFI"
                    }
                    (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).getNetworkCapabilities(
                        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork
                    )
                        ?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) != null && (getSystemService(
                        Context.CONNECTIVITY_SERVICE) as ConnectivityManager).getNetworkCapabilities(
                        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork
                    )?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> {
                        Toast.makeText(context, "Cellular Connection", Toast.LENGTH_SHORT).show()
                        "MOBILE"
                    }
                    else -> {
                        Toast.makeText(context, "No Connection", Toast.LENGTH_SHORT).show()
                        "NONE"
                    }
                }
            } else ({
                (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.run {
                    isWifi = when (type) {
                        ConnectivityManager.TYPE_WIFI -> "WIFI"
                        ConnectivityManager.TYPE_MOBILE -> "MOBILE"
                        else -> "NONE"
                    }
                }
            }).toString()

            return isWifi
        }
}