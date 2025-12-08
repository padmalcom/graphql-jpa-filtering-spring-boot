package de.stonedrum.demo.specification.predicate

import de.stonedrum.demo.specification.FilterOperation
import de.stonedrum.demo.specification.FilterParameter
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root

class StringPredicate {

    companion object {
        fun createStringPredicate(
            root: Root<*>,
            criteriaBuilder: CriteriaBuilder,
            filterParameter: FilterParameter
        ): Predicate? {
            val filterString = (filterParameter.value as String).lowercase()

            when (filterParameter.comparator) {
                FilterOperation.CONTAINS -> {
                    return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(filterParameter.name)),
                        "%$filterString%"
                    )
                }

                FilterOperation.DOES_NOT_CONTAIN -> {
                    return criteriaBuilder.notLike(
                        criteriaBuilder.lower(root.get(filterParameter.name)),
                        "%$filterString%"
                    )
                }

                FilterOperation.EQUAL -> {
                    return criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get(filterParameter.name)),
                        filterString
                    )
                }

                FilterOperation.NOT_EQUAL -> {
                    return criteriaBuilder.notEqual(
                        criteriaBuilder.lower(root.get(filterParameter.name)),
                        filterString
                    )
                }

                FilterOperation.BEGINS_WITH -> {
                    return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(filterParameter.name)),
                        "$filterString%"
                    )
                }

                FilterOperation.DOES_NOT_BEGIN_WITH -> {
                    return criteriaBuilder.notLike(
                        criteriaBuilder.lower(root.get(filterParameter.name)),
                        "$filterString%"
                    )
                }

                FilterOperation.ENDS_WITH -> {
                    return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(filterParameter.name)),
                        "%$filterString"
                    )
                }

                FilterOperation.DOES_NOT_END_WITH -> {
                    return criteriaBuilder.notLike(
                        criteriaBuilder.lower(root.get(filterParameter.name)),
                        "%$filterString"
                    )
                }

                FilterOperation.NUL -> {
                    return criteriaBuilder.isNull(
                        criteriaBuilder.lower(root.get(filterParameter.name))
                    )
                }

                FilterOperation.NOT_NULL -> {
                    return criteriaBuilder.isNotNull(
                        criteriaBuilder.lower(root.get(filterParameter.name))
                    )
                }

                FilterOperation.GREATER_THAN -> {
                    return criteriaBuilder.greaterThan(
                        criteriaBuilder.lower(root.get(filterParameter.name)),
                        filterString
                    )
                }

                FilterOperation.GREATER_THAN_EQUAL -> {
                    return criteriaBuilder.greaterThanOrEqualTo(
                        criteriaBuilder.lower(root.get(filterParameter.name)),
                        filterString
                    )
                }

                FilterOperation.LESS_THAN -> {
                    return criteriaBuilder.lessThan(
                        criteriaBuilder.lower(root.get(filterParameter.name)),
                        filterString
                    )
                }

                FilterOperation.LESS_THAN_EQUAL -> {
                    return criteriaBuilder.lessThanOrEqualTo(
                        criteriaBuilder.lower(root.get(filterParameter.name)),
                        filterString
                    )
                }

                else -> {
                    return null
                }
            }
        }
    }
}