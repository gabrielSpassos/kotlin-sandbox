package com.gabrielspassos

data class OutsideObject(
    val total: Long,
    val innerList: List<InternalObject>
)

data class InternalObject(
    val id: Long,
    val isSuccess: Boolean,
    val errors: List<String>
)
