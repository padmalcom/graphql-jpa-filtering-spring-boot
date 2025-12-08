package de.stonedrum.demo.config

import graphql.language.StringValue
import graphql.schema.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


object LocalDateTimeScalar {
    private val FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    fun createLocalDateTimeScalar(): GraphQLScalarType {
        return GraphQLScalarType.newScalar()
            .name("LocalDateTime")
            .description("Custom scalar for handling LocalDateTime in format 'yyyy-MM-dd HH:mm'")
            .coercing(object : Coercing<LocalDateTime, String> {
                override fun serialize(dataFetcherResult: Any): String {
                    if (dataFetcherResult is LocalDateTime) {
                        return FORMATTER.format(dataFetcherResult)
                    }
                    throw CoercingSerializeException("Expected a LocalDateTime object.")
                }

                override fun parseValue(input: Any): LocalDateTime {
                    if (input is String) {
                        try {
                            return LocalDateTime.parse(input, FORMATTER)
                        } catch (e: DateTimeParseException) {
                            throw CoercingParseValueException(
                                "Invalid LocalDateTime format. Expected 'yyyy-MM-dd HH:mm'.", e
                            )
                        }
                    }
                    throw CoercingParseValueException("Expected a String value for LocalDateTime.")
                }

                override fun parseLiteral(input: Any): LocalDateTime {
                    if (input is StringValue) {
                        try {
                            return LocalDateTime.parse(input.value, FORMATTER)
                        } catch (e: DateTimeParseException) {
                            throw CoercingParseLiteralException(
                                "Invalid LocalDateTime literal. Expected 'yyyy-MM-dd HH:mm'.", e
                            )
                        }
                    }
                    throw CoercingParseLiteralException("Expected a StringValue for LocalDateTime literal.")
                }
            }).build()
    }
}