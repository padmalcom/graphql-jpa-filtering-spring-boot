package de.stonedrum.demo.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.time.LocalDateTime

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
    var builtAt: LocalDateTime = LocalDateTime.now(),

    @Column
    @Enumerated(EnumType.STRING)
    var type: CarType,

    @Column
    val oldtimer: Boolean = false,

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    @CollectionTable(schema = "demo")
    @JsonManagedReference
    var owners: MutableList<Owner> = mutableListOf()
    )