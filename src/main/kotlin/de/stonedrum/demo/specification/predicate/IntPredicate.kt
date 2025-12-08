package de.stonedrum.demo.specification.predicate

import de.stonedrum.demo.specification.FilterOperation
import de.stonedrum.demo.specification.FilterParameter
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root

class IntPredicate {

    companion object {
        fun createIntPredicate(
            root: Root<*>,
            criteriaBuilder: CriteriaBuilder,
            filterParameter: FilterParameter
        ): Predicate? {
            when (filterParameter.comparator) {

                FilterOperation.EQUAL -> {
                    return criteriaBuilder.equal(root.get<Int?>(filterParameter.name),
                        filterParameter.value as Int
                    )
                }

                FilterOperation.NOT_EQUAL -> {
                    return criteriaBuilder.notEqual(root.get<Int?>(filterParameter.name),
                        filterParameter.value as Int
                    )
                }

                FilterOperation.NUL -> {
                    return criteriaBuilder.isNull(root.get<Int?>(filterParameter.name))
                }

                FilterOperation.NOT_NULL -> {
                    return criteriaBuilder.isNotNull(root.get<Int?>(filterParameter.name))
                }

                FilterOperation.GREATER_THAN -> {
                    return criteriaBuilder.greaterThan(
                        root.get(filterParameter.name),
                        filterParameter.value as Int
                    )
                }

                FilterOperation.GREATER_THAN_EQUAL -> {
                    return criteriaBuilder.greaterThanOrEqualTo(
                        root.get(filterParameter.name),
                        filterParameter.value as Int
                    )
                }

                FilterOperation.LESS_THAN -> {
                    return criteriaBuilder.lessThan(
                        root.get(filterParameter.name),
                        filterParameter.value as Int
                    )
                }

                FilterOperation.LESS_THAN_EQUAL -> {
                    return criteriaBuilder.lessThanOrEqualTo(
                        root.get(filterParameter.name),
                        filterParameter.value as Int
                    )
                }

                else -> {
                    return null
                }
            }
        }
    }
}