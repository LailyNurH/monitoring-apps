package com.night.monitoring.views

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.night.monitoring.R
import com.night.monitoring.databinding.FragmentMembershipBinding
import java.util.*

class MembershipFragment : Fragment() {

    private lateinit var locationManager: LocationManager
    private lateinit var geocoder: Geocoder

    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    private var _binding: FragmentMembershipBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMembershipBinding.inflate(inflater, container, false)
        val rootView = binding.root

        // Inisialisasi LocationManager dan Geocoder
        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        geocoder = Geocoder(requireContext(), Locale.getDefault())

        binding.btnToMaps.setOnClickListener {
            if (checkLocationPermissions()) {
                getCurrentLocation()
            } else {
                requestLocationPermissions()
            }
        }

        return rootView
    }

    private fun checkLocationPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun getCurrentLocation() {
        if (checkLocationPermissions()) {
            try {
                val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                location?.let {
                    val latitude = it.latitude
                    val longitude = it.longitude

                    val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1) as List<Address>

                    if (addresses.isNotEmpty()) {
                        val address: Address = addresses[0]
                        val addressLine = address.getAddressLine(0)
                        val city = address.locality
                        val state = address.adminArea
                        val country = address.countryName
                        val postalCode = address.postalCode

                        val fullAddress = "$addressLine, $city, $state, $country, $postalCode"
                        showToast("Alamat Saat Ini: $fullAddress")
                        binding.etAddressCoordinate.setText(fullAddress)

                    }
                }
            } catch (e: SecurityException) {
                showToast("Izin lokasi ditolak oleh pengguna.")
            } catch (e: Exception) {
                showToast("Gagal mendapatkan alamat saat ini.")
            }
        } else {
            showToast("Izin lokasi tidak diberikan.")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}