package com.example.android_test.retrofit

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val address: Address
)
data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String
) {
    override fun toString(): String = "$street, $city, $zipcode"
}