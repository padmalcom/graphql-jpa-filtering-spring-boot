package de.stonedrum.demo.specification.predicate

import de.stonedrum.demo.specification.FilterOperation
import de.stonedrum.demo.specification.FilterParameter
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root

class ListPredicate {

    companion object {
        fun createListPredicate(
            root: Root<*>,
            criteriaBuilder: CriteriaBuilder,
            filterParameter: FilterParameter
        ): Predicate? {
            when (filterParameter.comparator) {

                FilterOperation.DOES_NOT_CONTAIN -> {
                    return criteriaBuilder.isNotMember(filterParameter.value,
                        root.get(filterParameter.name))
                }

                FilterOperation.CONTAINS -> {
                    return criteriaBuilder.isMember(filterParameter.value,
                        root.get(filterParameter.name))
                }

                FilterOperation.IS_EMPTY -> {
                    return criteriaBuilder.isEmpty(root.get(filterParameter.name))
                }

                FilterOperation.IS_NOT_EMPTY -> {
                    return criteriaBuilder.isNotEmpty(root.get(filterParameter.name))
                }

                else -> {
                    return null
                }
            }
        }
    }
}