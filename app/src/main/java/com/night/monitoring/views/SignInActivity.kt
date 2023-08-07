package com.night.monitoring.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.night.monitoring.MainActivity
import com.night.monitoring.R
import com.night.monitoring.api.BaseRetrofit
import com.night.monitoring.model.LoginResponse
import com.night.monitoring.utils.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private val api by lazy { BaseRetrofit().endpoint }

    companion object {
        lateinit var sessionManager: SessionManager
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        sessionManager = SessionManager(this)

        val loginStatus = sessionManager.getBoolean("LOGIN_STATUS")
        if (loginStatus) {
            val moveIntent = Intent(this@SignInActivity, MainActivity::class.java)
            startActivity(moveIntent)
            finish()
        }

        val btnLogin = findViewById<Button>(R.id.btnSignIn)
        val txtEmail = findViewById<EditText>(R.id.etEmail)
        val txtPassword = findViewById<EditText>(R.id.etPassword)

        btnLogin.setOnClickListener {
            Toast.makeText(this, "Proses Login", Toast.LENGTH_SHORT).show()

            api.login(txtEmail.text.toString(), txtPassword.text.toString()).enqueue(object :
                Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val correct = response.body()!!.success
                    if (correct) {
                        //untuk menyimpan session dengan mendapatkan data token dari response body
                        val token = response.body()!!.data.token
                        sessionManager.saveString("TOKEN", "Bearer " + token)
                        sessionManager.saveBoolean("LOGIN_STATUS", true)
                        sessionManager.saveInteger(
                            "ADMIN_ID",
                            response.body()!!.data.user.id.toInt()
                        )
                         sessionManager.saveString(
                             "USERNAME",
                             response.body()!!.data.user.nama
                         )

                        Toast.makeText(applicationContext, "Password Benar", Toast.LENGTH_LONG)
                            .show()
                        val moveIntent = Intent(this@SignInActivity, MainActivity::class.java)
                        startActivity(moveIntent)
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Password salah", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("LoginError", t.toString())
                }

            })
        }

    }
}