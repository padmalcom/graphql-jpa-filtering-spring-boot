package de.stonedrum.demo.specification

data class FilterParameter(
    val name: String,
    val comparator: FilterOperation,
    val value: Any
)
