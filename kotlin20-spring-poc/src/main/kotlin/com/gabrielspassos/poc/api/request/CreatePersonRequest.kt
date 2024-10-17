package com.gabrielspassos.poc.api.request

data class CreatePersonRequest(
    val name: String,
    val parentId: String? = null
)
