package com.gabrielspassos.poc.api.response

data class PersonResponse(
    val id: String,
    val name: String,
    val children: List<PersonResponse> = emptyList(),
    val createdDate: String,
    val modifiedDate: String,
)
