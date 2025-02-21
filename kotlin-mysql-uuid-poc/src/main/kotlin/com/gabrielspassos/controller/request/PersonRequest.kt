package com.gabrielspassos.controller.request

import java.util.UUID

data class PersonRequest(
    val externalId: UUID,
    val name: String
)
