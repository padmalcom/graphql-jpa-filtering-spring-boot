package de.stonedrum.demo.config

import graphql.GraphQLContext
import graphql.execution.CoercedVariables
import graphql.language.IntValue
import graphql.language.StringValue
import graphql.language.Value
import graphql.scalar.CoercingUtil.isNumberIsh
import graphql.scalar.CoercingUtil.typeName
import graphql.schema.Coercing
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.GraphQLScalarType
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*


object LongScalar {

    private val LONG_MAX: BigInteger = BigInteger.valueOf(Long.MAX_VALUE)
    private val LONG_MIN: BigInteger = BigInteger.valueOf(Long.MIN_VALUE)

    fun createLongScalar(): GraphQLScalarType {
        return GraphQLScalarType.newScalar()
            .name("Long")
            .description("A 64-bit signed integer")
            .coercing(object : Coercing<Any?, Any> {

                override fun serialize(dataFetcherResult: Any, graphQLContext: GraphQLContext, locale: Locale): Long {
                    return convert(dataFetcherResult)
                }

                override fun parseValue(input: Any, graphQLContext: GraphQLContext, locale: Locale): Long {
                    return convert(input)
                }

                override fun parseLiteral(
                    input: Value<*>,
                    variables: CoercedVariables,
                    graphQLContext: GraphQLContext,
                    locale: Locale
                ): Long {
                    if (input is StringValue) {
                        try {
                            return input.value.toLong()
                        } catch (e: java.lang.NumberFormatException) {
                            throw CoercingParseLiteralException(
                                "Expected value to be a Long but it was '$input'"
                            )
                        }
                    } else if (input is IntValue) {
                        val value: BigInteger = input.value
                        if (value !in LONG_MIN..LONG_MAX) {
                            throw CoercingParseLiteralException(
                                "Expected value to be in the Long range but it was '$value'"
                            )
                        }
                        return value.toLong()
                    }
                    throw CoercingParseLiteralException(
                        "Expected AST type 'IntValue' or 'StringValue' but was '" + typeName(input) + "'."
                    )
                }



                private fun convert(input: Any): Long {
                    if (input is Long) {
                        return input
                    } else if (isNumberIsh(input)) {
                        val value: BigDecimal
                        try {
                            value = BigDecimal(input.toString())
                        } catch (e: NumberFormatException) {
                            throw CoercingParseValueException("Invalid value for Long scalar type $input }")
                        }
                        return try {
                            value.longValueExact()
                        } catch (e: ArithmeticException) {
                            throw CoercingParseValueException("Invalid value for Long scalar type $input }")
                        }
                    } else {
                        throw CoercingParseValueException("Invalid value for Long scalar type $input }")
                    }
                }
            })
            .build()
    }
}