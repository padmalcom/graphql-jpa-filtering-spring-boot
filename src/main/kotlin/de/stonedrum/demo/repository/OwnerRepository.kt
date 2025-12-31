package de.stonedrum.demo.repository

import de.stonedrum.demo.model.Owner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface OwnerRepository : JpaRepository<Owner, String>, JpaSpecificationExecutor<Owner>