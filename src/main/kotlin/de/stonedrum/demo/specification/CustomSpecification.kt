package de.stonedrum.demo.specification

import de.stonedrum.demo.specification.predicate.*
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import jakarta.persistence.metamodel.Bindable
import org.slf4j.LoggerFactory
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDate
import java.time.LocalDateTime


class CustomSpecification<T>(
    private val filterParameter: FilterParameter
) : Specification<T> {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun toPredicate(
        root: Root<T>,
        query: CriteriaQuery<*>?,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {

        val propertyPath = root.get<Any>(filterParameter.name)
        var propertyType = propertyPath.model.bindableJavaType

        if (propertyPath.model.bindableType == Bindable.BindableType.SINGULAR_ATTRIBUTE) {

            if (propertyType.isEnum) {
                propertyType = String::class.java as Class<Any>
            }

            when (propertyType) {
                Int::class.java -> {
                    logger.trace("Using IntPredicate")
                    return IntPredicate.createIntPredicate(
                        root,
                        criteriaBuilder,
                        filterParameter
                    )
                }

                Float::class.java -> {
                    logger.trace("Using FloatPredicate")
                    return FloatPredicate.createFloatPredicate(
                        root,
                        criteriaBuilder,
                        filterParameter
                    )
                }

                String::class.java -> {
                    logger.trace("Using StringPredicate")
                    return StringPredicate.createStringPredicate(
                        root,
                        criteriaBuilder,
                        filterParameter
                    )
                }

                LocalDateTime::class.java -> {
                    logger.trace("Using LocalDateTimePredicate")
                    return LocalDateTimePredicate.createLocalDateTimePredicate(
                        root,
                        criteriaBuilder,
                        filterParameter
                    )
                }

                LocalDate::class.java -> {
                    logger.trace("Using LocalDatePredicate")
                    return LocalDatePredicate.createLocalDatePredicate(
                        root,
                        criteriaBuilder,
                        filterParameter
                    )
                }
            }
            logger.trace("Not using any predicate for type {}.", propertyType)
            return null
        } else if (propertyPath.model.bindableType == Bindable.BindableType.PLURAL_ATTRIBUTE) {
            logger.trace("Using ListPredicate")
            return ListPredicate.createListPredicate(
                root,
                criteriaBuilder,
                filterParameter
            )
        }
        logger.trace("Not using any predicate for type {}.", propertyType)
        return null
    }
}