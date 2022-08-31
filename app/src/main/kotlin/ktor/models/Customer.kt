package ktor.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val id: String,
    private val firstName: String,
    private val lastName: String,
    private val email: String
)

val customerStorage = mutableListOf<Customer>()
