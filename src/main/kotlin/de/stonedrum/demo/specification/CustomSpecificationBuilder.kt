package de.stonedrum.demo.specification

import org.springframework.data.jpa.domain.Specification

class CustomSpecificationBuilder<T> {

    private val params: MutableList<FilterParameter> = mutableListOf()

    fun with(
        key: String,
        operation: FilterOperation,
        value: String
    ): CustomSpecificationBuilder<T> {
        params.add(
            FilterParameter(
                name = key,
                comparator = operation,
                value = value
            )
        )
        return this
    }

    fun with(filterParameters: List<FilterParameter>): CustomSpecificationBuilder<T> {
        params.addAll(filterParameters)
        return this
    }

    fun with(filterParameter: FilterParameter): CustomSpecificationBuilder<T> {
        params.add(filterParameter)
        return this
    }

    fun build(): Specification<T>? {
        if (params.isEmpty()) {
            return null
        }

        var result: Specification<T> = CustomSpecification(params[0])

        for (idx in 1..<params.size) {
            val criteria = params[idx]
            result.and(CustomSpecification(criteria))
        }
        return result
    }
}