package de.stonedrum.demo.specification.predicate

import de.stonedrum.demo.specification.FilterOperation
import de.stonedrum.demo.specification.FilterParameter
import graphql.schema.CoercingParseValueException
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class LocalDateTimePredicate {
    companion object {

        private val FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

        fun createLocalDateTimePredicate(
            root: Root<*>,
            criteriaBuilder: CriteriaBuilder,
            filterParameter: FilterParameter
        ): Predicate? {

            val dateString = filterParameter.value as String
            val date = try {
                LocalDateTime.parse(dateString, FORMATTER)
            } catch (e: DateTimeParseException) {
                throw CoercingParseValueException(
                    "Invalid LocalDateTime format. Expected 'yyyy-MM-dd HH:mm'.", e
                )
            }

            when (filterParameter.comparator) {

                FilterOperation.EQUAL -> {
                    return criteriaBuilder.equal(
                        root.get<LocalDateTime>(filterParameter.name),
                        date
                    )
                }

                FilterOperation.NOT_EQUAL -> {
                    return criteriaBuilder.notEqual(
                        root.get<LocalDateTime>(filterParameter.name),
                        date
                    )
                }

                FilterOperation.NUL -> {
                    return criteriaBuilder.isNull(root.get<LocalDateTime?>(filterParameter.name))
                }

                FilterOperation.NOT_NULL -> {
                    return criteriaBuilder.isNotNull(root.get<LocalDateTime?>(filterParameter.name))
                }

                FilterOperation.GREATER_THAN -> {
                    return criteriaBuilder.greaterThan(root.get(filterParameter.name), date)
                }

                FilterOperation.GREATER_THAN_EQUAL -> {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get(filterParameter.name), date)
                }

                FilterOperation.LESS_THAN -> {
                    return criteriaBuilder.lessThan(root.get(filterParameter.name), date)
                }

                FilterOperation.LESS_THAN_EQUAL -> {
                    return criteriaBuilder.lessThanOrEqualTo(root.get(filterParameter.name), date)
                }

                else -> {
                    return null
                }
            }
        }
    }
}