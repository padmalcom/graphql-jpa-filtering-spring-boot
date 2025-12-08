package de.stonedrum.demo.config

import de.stonedrum.demo.config.LocalDateScalar.createLocalDateScalar
import de.stonedrum.demo.config.LocalDateTimeScalar.createLocalDateTimeScalar
import de.stonedrum.demo.config.LongScalar.createLongScalar
import de.stonedrum.demo.config.MultiTypeScalar.createMultiTypeScalar
import graphql.schema.idl.RuntimeWiring
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer


@Configuration
class GraphQLConfig {
    @Bean
    fun localDateTimeConfigurer(): RuntimeWiringConfigurer {
        return RuntimeWiringConfigurer { wiringBuilder: RuntimeWiring.Builder ->
            wiringBuilder
                .scalar(createLocalDateTimeScalar())
        }
    }

    @Bean
    fun localDateConfigurer(): RuntimeWiringConfigurer {
        return RuntimeWiringConfigurer { wiringBuilder: RuntimeWiring.Builder ->
            wiringBuilder
                .scalar(createLocalDateScalar())
        }
    }

    @Bean
    fun multiValueScalarConfigurer(): RuntimeWiringConfigurer {
        return RuntimeWiringConfigurer { wiringBuilder: RuntimeWiring.Builder ->
            wiringBuilder
                .scalar(createMultiTypeScalar())
        }
    }

    @Bean
    fun longScalarConfigurer(): RuntimeWiringConfigurer {
        return RuntimeWiringConfigurer { wiringBuilder: RuntimeWiring.Builder ->
            wiringBuilder
                .scalar(createLongScalar())
        }
    }
}