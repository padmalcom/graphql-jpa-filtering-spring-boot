package de.stonedrum.demo.repository

import de.stonedrum.demo.model.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface CarRepository : JpaRepository<Car, String>, JpaSpecificationExecutor<Car>