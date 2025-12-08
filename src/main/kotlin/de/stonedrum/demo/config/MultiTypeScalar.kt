package de.stonedrum.demo.config

import graphql.language.*
import graphql.schema.Coercing
import graphql.schema.CoercingParseValueException
import graphql.schema.GraphQLScalarType

object MultiTypeScalar {
    fun createMultiTypeScalar(): GraphQLScalarType {
        return GraphQLScalarType.newScalar()
            .name("MultiValue")
            .description("Accepts String, Int, Float, LocalDate, or LocalDateTime")
            .coercing(object : Coercing<Any?, Any> {
                override fun serialize(dataFetcherResult: Any): Any {
                    return dataFetcherResult
                }

                override fun parseValue(input: Any): Any {
                    return convert(input)
                }

                override fun parseLiteral(input: Any): Any? {
                    if (input is Value<*>) {
                        return convert(input)
                    }
                    return null
                }

                private fun convert(input: Any): Any {
                    if (input is StringValue) return input.value
                    if (input is IntValue) return input.value
                    if (input is FloatValue) return input.value
                    if (input is String) return input
                    if (input is Int) return input
                    if (input is Float) return input

                    throw CoercingParseValueException("Invalid value for MultiValue scalar type $input }")
                }
            })
            .build()
    }
}