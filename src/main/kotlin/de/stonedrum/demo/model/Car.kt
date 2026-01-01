package de.stonedrum.demo.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(schema = "demo")
class Car(
    @Id
    var id: String,

    @Column
    var name: String,

    @Column
    var value: Int,

    @Column
    var builtAt: LocalDate,

    @Column
    @Enumerated(EnumType.STRING)
    var type: CarType,

    @Column
    val oldtimer: Boolean = false,

    @ManyToMany(cascade = [], fetch = FetchType.LAZY)
    @CollectionTable(schema = "demo")
    var owners: MutableSet<Owner> = mutableSetOf()
    )