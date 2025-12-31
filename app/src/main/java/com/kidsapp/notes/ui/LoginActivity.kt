package com.kidsapp.notes.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.kidsapp.notes.R
import com.kidsapp.notes.databinding.ActivityLoginBinding
import com.kidsapp.notes.utils.BiometricAuthManager

class LoginActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityLoginBinding
    private lateinit var biometricAuthManager: BiometricAuthManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        biometricAuthManager = BiometricAuthManager(this)
        
        if (!biometricAuthManager.canAuthenticate()) {
            Snackbar.make(
                binding.root,
                "Biometric authentication not available on this device",
                Snackbar.LENGTH_LONG
            ).show()
            binding.authenticateButton.isEnabled = false
            return
        }
        
        binding.authenticateButton.setOnClickListener {
            authenticateUser()
        }
    }
    
    private fun authenticateUser() {
        biometricAuthManager.authenticate(
            onSuccess = {
                Toast.makeText(this, "Authentication successful!", Toast.LENGTH_SHORT).show()
                navigateToMainActivity()
            },
            onError = { errorMessage ->
                Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_SHORT).show()
            },
            onFailed = {
                Snackbar.make(binding.root, "Authentication failed", Snackbar.LENGTH_SHORT).show()
            }
        )
    }
    
    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
