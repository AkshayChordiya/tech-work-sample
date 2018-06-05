package com.akshay.daresayweather.models.live

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.content.systemService
import com.akshay.daresayweather.models.Resource

class LocationLiveData(context: Context) : LiveData<Resource<Location>>(), LocationListener {

    private var locationManager: LocationManager = context.systemService<LocationManager>()

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        // Permission is not granted
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10F, this)
        // Send last known location, till we get the exact current location.
        value = Resource.success(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER))
    }

    override fun onInactive() {
        super.onInactive()
        locationManager.removeUpdates(this)
    }

    override fun onLocationChanged(location: Location?) {
        location?.let { value = Resource.success(it) }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        value = Resource.error("Failed to fetch location")
    }

    override fun onProviderEnabled(provider: String?) {}

    override fun onProviderDisabled(provider: String?) {}
}