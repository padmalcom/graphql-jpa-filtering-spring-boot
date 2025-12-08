package de.stonedrum.demo.service

import de.stonedrum.demo.model.Car
import de.stonedrum.demo.repository.CarRepository
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class CarService(
    private val carRepository: CarRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun getAllCars(
        pageable: Pageable,
        specification: Specification<Car>
        ): List<Car> {
        return carRepository.findAll(specification, pageable).toList()
    }

    @Transactional
    fun getAllCars(
        pageable: Pageable
    ): List<Car> {
        return carRepository.findAll(pageable).toList()
    }
}