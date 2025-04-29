package com.example.myapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    // Private mutable state flows
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    // Using String? for error message, null means no error
    private val _errorState = MutableStateFlow<String?>(null)

    // Public immutable state flows
    val email: StateFlow<String> = _email.asStateFlow()
    val password: StateFlow<String> = _password.asStateFlow()
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _email.update { newEmail }
        // Optionally clear error when user types
        if (_errorState.value != null) {
            validateInput()
        }
    }

    fun onPasswordChange(newPassword: String) {
        _password.update { newPassword }
        // Optionally clear error when user types
         if (_errorState.value != null) {
            validateInput()
        }
    }

    // Validation logic
    private fun validateInput(): Boolean {
        val currentEmail = _email.value
        val currentPassword = _password.value
        var isValid = true

        if (currentEmail.isBlank()) {
            _errorState.update { "Email cannot be empty" }
            isValid = false
        } else if (currentPassword.isBlank()) {
             // Check password only if email is not blank
            _errorState.update { "Password cannot be empty" }
             isValid = false
        } else {
            // Both are non-blank, clear error
            _errorState.update { null }
        }
        return isValid
    }

    // Example function to trigger login (which includes validation)
    // Accepts a callback to execute on successful validation/login attempt
    fun attemptLogin(onSuccess: () -> Unit) {
        if (validateInput()) {
            // Proceed with login logic (e.g., call repository)
            viewModelScope.launch {
                // Placeholder for actual login network call
                println("Login attempt: Email=${_email.value}, Password=${_password.value}")
                // Handle success/failure from repository call here

                // *** Simulate successful login for now ***
                // In a real app, this would be called after successful API response
                onSuccess()
            }
        }
    }
}
