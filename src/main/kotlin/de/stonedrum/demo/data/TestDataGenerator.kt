package de.stonedrum.demo.data

import de.stonedrum.demo.model.Car
import de.stonedrum.demo.model.CarType
import de.stonedrum.demo.model.Owner
import de.stonedrum.demo.repository.CarRepository
import de.stonedrum.demo.repository.OwnerRepository
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*

@Component
//@Profile("dev")
class TestDataGenerator(
    private val carRepository: CarRepository,
    private val ownerRepository: OwnerRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @EventListener(ApplicationReadyEvent::class)
    @Transactional
    fun createTestData() {
        val owner1 = Owner(
            id = UUID.randomUUID().toString(),
            firstName = "James",
            lastName = "Dean"
        )
        val owner2 = Owner(
            id = UUID.randomUUID().toString(),
            firstName = "Marilyn",
            lastName = "Monroe"
        )

        ownerRepository.save(owner1)
        ownerRepository.save(owner2)
        logger.debug("Created test owner {} and {}", owner1, owner2)
        
        val car1 = Car(
            id = UUID.randomUUID().toString(),
            name = "Chevrolet Camaro",
            value = 25000,
            builtAt = LocalDate.of(1060, 1, 1),
            type = CarType.SPORTS,
            oldtimer = true,
            owners = mutableListOf(owner1, owner2)
        )
        
        val car2 = Car(
            id = UUID.randomUUID().toString(),
            name = "Ford Thunderbird",
            value = 40000,
            builtAt = LocalDate.of(1961, 1, 1),
            type = CarType.CABRIOLET,
            oldtimer = true,
            owners = mutableListOf(owner2)
        )
        carRepository.save(car1)
        carRepository.save(car2)
        logger.debug("Created test car {} and {}", car1, car2)
    }
}