package com.night.monitoring.views

import android.Manifest
import android.app.DatePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.night.monitoring.R
import com.night.monitoring.api.BaseRetrofit
import com.night.monitoring.databinding.FragmentMembershipBinding
import com.night.monitoring.model.member.MemberResponsePost
import com.night.monitoring.views.PaketBerlanggananFragment.Companion.TIPE_BERLANGGANAN
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MembershipFragment : Fragment() {

    private lateinit var locationManager: LocationManager
    private lateinit var geocoder: Geocoder
    private val api by lazy { BaseRetrofit().endpoint }

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
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etJenis.setText(TIPE_BERLANGGANAN)
        binding.etTanggalMulai.setOnClickListener {
            showDatePicker()
        }

        with(binding) {


            binding.konfirmasi.setOnClickListener {
                // Get user input from EditText
                val namamember = etName.text.toString()
                val alamat = etAddressCoordinate.text.toString()
                val nohp = etNohp.text.toString()
                val noktp = etKtp.text.toString()
                val pekerjaan = etPekerjaan.text.toString()
                val guna = etGuna.text.toString()
                val pembayaran = etDp.text.toString()
                val tglberlangganan = etTanggalMulai.text.toString()
                val jumlahunit = etJumlahUnit.text.toString()

                if (namamember.isEmpty() || alamat.isEmpty() || nohp.isEmpty() || noktp.isEmpty() ||
                    pekerjaan.isEmpty() || guna.isEmpty() || pembayaran.isEmpty() ||
                    tglberlangganan.isEmpty() || jumlahunit.isEmpty()
                ) {
                    Toast.makeText(
                        requireContext(),
                        "Please fill in all fields",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val userid = LoginFragment.sessionManager.getString("USER_ID")
                    val userID = userid

                    api.membership(
                        alamat,
                        userID!!,
                        namamember,
                        nohp,
                        noktp,
                        pekerjaan,
                        PaketBerlanggananFragment.TIPE_BERLANGGANAN,
                        guna,
                        pembayaran,
                        tglberlangganan,
                        jumlahunit,
                        "N",
                        "N",
                        ""
                    ).enqueue(object : Callback<MemberResponsePost> {
                        override fun onResponse(
                            call: Call<MemberResponsePost>,
                            response: Response<MemberResponsePost>
                        ) {
                            Log.d("Success", response.toString())
                            Toast.makeText(
                                activity?.applicationContext,
                                "Pengajuaan Anda telah di kirim",
                                Toast.LENGTH_SHORT
                            ).show()
                            findNavController().navigate(R.id.action_membershipFragment_to_menuFragment)
                        }

                        override fun onFailure(call: Call<MemberResponsePost>, t: Throwable) {
                            Log.e("Error", t.toString())
                        }
                    })

                }
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = formatDate(year, monthOfYear, dayOfMonth)
                binding.etTanggalMulai.setText(selectedDate)
            },
            year,
            month,
            day
        )



        datePickerDialog.show()
    }

    private fun formatDate(year: Int, month: Int, day: Int): String {
        // Adjust month to 1-based index (DatePicker gives month from 0 to 11)
        val formattedMonth = month + 1
        return "$day/$formattedMonth/$year"
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



