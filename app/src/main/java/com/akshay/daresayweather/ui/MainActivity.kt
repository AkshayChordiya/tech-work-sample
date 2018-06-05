package com.akshay.daresayweather.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import com.akshay.daresayweather.R
import com.akshay.daresayweather.utils.getViewModel
import com.akshay.daresayweather.utils.observe
import com.akshay.daresayweather.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private val weatherViewModel by lazy { getViewModel<WeatherViewModel>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user.
                toast(R.string.explain_location_permission)
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        ACCESS_LOCATION_REQUEST)
            }
        } else {
            // Permission has already been granted
            observeWeather()
        }
    }

    private fun observeWeather() {
        current_time.text = DateFormat.getTimeFormat(this).format(Date())
        weatherViewModel.getLocation().observe(this) {
            it.errorMessage?.let {
                // Show error toast on failed to get location.
                toast(it)
            }
        }
        // Get current weather.
        weatherViewModel.getCurrentWeather().observe(this) {
            it.data?.apply {
                current_time.text = DateFormat.getTimeFormat(applicationContext).format(Date())
                city_name.text = name
                temperature.text = main.temp.toString()
                humidity.text = main.humidity.toString()
                weather_description.text = weather[0].description
                pressure.text = main.pressure.toString()
                weather_image.setImageResource(
                        when (weather[0].main) {
                            in "clouds" -> R.drawable.ic_cloudy
                            in "clear" -> R.drawable.ic_sunny
                            in "rain" -> R.drawable.ic_rain
                            else -> R.drawable.ic_sunny
                        }
                )
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            ACCESS_LOCATION_REQUEST -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission granted.
                    observeWeather()
                } else {
                    // Permission denied.
                    longToast(R.string.failed_location_permission)
                    finish()
                }
                return
            }
            else -> {
                // Other requests.
            }
        }
    }

    companion object {
        // Request ID for location permission callback
        private const val ACCESS_LOCATION_REQUEST: Int = 74
    }
}
