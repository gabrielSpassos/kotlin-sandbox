package com.gabrielspassos.client.response

data class Country(
    val name: CountryName,
    val cca2: String,
    val ccn3: String,
    val cca3: String,
    val cioc: String,
    val independent: Boolean,
    val status: String,
    val unMember: Boolean,
    val currencies: Map<String, String>,
    val capital: List<String>
)

data class CountryName(
    val country: String,
    val official: String,
    val nativeName: Map<String, String>
)