package de.stonedrum.demo.specification

import org.springframework.data.jpa.domain.Specification

class CustomSpecificationBuilder<T> {

    private val params: MutableList<FilterParameter> = mutableListOf()

    fun with(filterParameter: FilterParameter): CustomSpecificationBuilder<T> {
        params.add(filterParameter)
        return this
    }

    fun build(): Specification<T>? {
        if (params.isEmpty()) {
            return null
        }

        val result: Specification<T> = CustomSpecification(params[0])

        for (idx in 1..<params.size) {
            val criteria = params[idx]
            result.and(CustomSpecification(criteria))
        }
        return result
    }
}