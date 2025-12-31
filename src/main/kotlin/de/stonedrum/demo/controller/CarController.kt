package de.stonedrum.demo.controller

import de.stonedrum.demo.model.Car
import de.stonedrum.demo.service.CarService
import de.stonedrum.demo.specification.CustomSpecificationBuilder
import de.stonedrum.demo.specification.FilterParameter
import de.stonedrum.demo.specification.SortParameter
import org.springframework.data.domain.PageRequest
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller


@Controller
class CarController(
    private val carService: CarService
) {
    @SchemaMapping(typeName = "Query", field = "allCars")
    fun allCars(
        @Argument page: Int,
        @Argument size: Int,
        @Argument filter: List<FilterParameter>?,
        @Argument sort: List<SortParameter>?
    ): List<Car> {
        val specificationBuilder = CustomSpecificationBuilder<Car>()
        filter?.let {
            it.forEach { f ->
                specificationBuilder.with(f)
            }
        }

        var pageRequest = PageRequest.of(page, size)
        sort?.let {
            pageRequest = pageRequest.withSort(SortParameter.toSort(sort))
        }

        val specification = specificationBuilder.build()
        specification?.let {
            return carService.getAllCars(pageRequest, it)
        }
        return carService.getAllCars(pageRequest)
    }
}