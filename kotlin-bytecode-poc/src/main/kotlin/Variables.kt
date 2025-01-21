package com.gabrielspassos

data class Input(val variables: Variables)

interface Variables

data class SingleVariable(val value: String): Variables

data class MultipleVariables(val values: List<String>): Variables

