package com.example.unsecuredseguros.exceptions

data class ErrorMessage(
    val status: Int,
    val message: String,
    val path: String
)