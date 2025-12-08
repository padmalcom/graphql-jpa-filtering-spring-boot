package de.stonedrum.demo.specification

import org.springframework.data.domain.Sort

enum class SortOrder {
    ASC, DESC
}

data class SortParameter(
    val name: String,
    val order: SortOrder
) {
    companion object {
        fun toSort(sortParameters: List<SortParameter>): Sort {
            val orders = sortParameters.map { sp ->
                val direction = when (sp.order){
                    SortOrder.ASC -> Sort.Direction.ASC
                    SortOrder.DESC -> Sort.Direction.DESC
                }
                Sort.Order(direction, sp.name)
            }

            return Sort.by(orders)
        }
    }
}