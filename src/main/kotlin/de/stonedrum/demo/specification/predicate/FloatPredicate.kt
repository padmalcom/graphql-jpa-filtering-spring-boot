package de.stonedrum.demo.specification.predicate

import de.stonedrum.demo.specification.FilterOperation
import de.stonedrum.demo.specification.FilterParameter
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root

class FloatPredicate {

    companion object {
        fun createFloatPredicate(
            root: Root<*>,
            criteriaBuilder: CriteriaBuilder,
            filterParameter: FilterParameter
        ): Predicate? {
            when (filterParameter.comparator) {

                FilterOperation.EQUAL -> {
                    return criteriaBuilder.equal(root.get<Float?>(filterParameter.name),
                        filterParameter.value as Float
                    )
                }

                FilterOperation.NOT_EQUAL -> {
                    return criteriaBuilder.notEqual(root.get<Float?>(filterParameter.name),
                        filterParameter.value as Float
                    )
                }

                FilterOperation.NUL -> {
                    return criteriaBuilder.isNull(root.get<Float?>(filterParameter.name))
                }

                FilterOperation.NOT_NULL -> {
                    return criteriaBuilder.isNotNull(root.get<Float?>(filterParameter.name))
                }

                FilterOperation.GREATER_THAN -> {
                    return criteriaBuilder.greaterThan(
                        root.get(filterParameter.name),
                        filterParameter.value as Float
                    )
                }

                FilterOperation.GREATER_THAN_EQUAL -> {
                    return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(filterParameter.name),
                        filterParameter.value as Float
                    )
                }

                FilterOperation.LESS_THAN -> {
                    return criteriaBuilder.lessThan(
                        root.get(filterParameter.name),
                        filterParameter.value as Float
                    )
                }

                FilterOperation.LESS_THAN_EQUAL -> {
                    return criteriaBuilder.lessThanOrEqualTo(
                        root.get(filterParameter.name),
                        filterParameter.value as Float
                    )
                }

                else -> {
                    return null
                }
            }
        }
    }
}