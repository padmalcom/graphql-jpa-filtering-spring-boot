package de.stonedrum.demo.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
class Owner(
    @Id
    var id: String,

    @Column
    var firstName: String,

    @Column
    var lastName: String
    )