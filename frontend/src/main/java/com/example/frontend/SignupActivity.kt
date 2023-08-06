package com.example.frontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.frontend.databinding.ActivitySignupBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupButton.setOnClickListener {
            val id = binding.signupId.text.toString()
            val password = binding.signupPassword.text.toString()
            val email = binding.signupEmail.text.toString()
            val phone = binding.signupPhone.text.toString()

            MainScope().launch {
                try {
                    val apiService = ApiClient.getApiService()
                    val response = withContext(Dispatchers.IO) {
                        apiService.signupUser(id, password, email, phone)
                    }

                    if (response.isSuccessful) {
                        val message = response.body()?.message
                        // 회원가입 성공 처리
                    } else {
                        val errorMessage = response.errorBody()?.string()
                        // 회원가입 실패 처리
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    // 서버 통신 에러 처리
                }
            }
        }
    }
}