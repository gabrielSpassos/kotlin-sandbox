package com.gabrielspassos

import java.util.UUID

data class OutsideObject(
    val total: Long,
    val innerList: List<InternalObject>
)

data class InternalObject(
    val id: UUID,
    val isSuccess: Boolean,
    val errors: List<String>
)
