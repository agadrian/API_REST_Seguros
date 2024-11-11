package com.example.unsecuredseguros.model

import jakarta.persistence.*
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "seguros")
data class Seguro(

    @Id
    val idSeguro: Int,

    @Column(nullable = false, length = 10)
    var nif: String,

    @Column(nullable = false, length = 100)
    var nombre: String,

    @Column(nullable = false, length = 100)
    var ape1: String,

    @Column(length = 100)
    var ape2: String?,

    @Column(nullable = false, length = 3)
    var edad: Int,

    @Column(nullable = false, length = 2)
    var numHijos: Int,

    @Column(nullable = false)
    var fechaCreacion: Date = Timestamp.valueOf(LocalDateTime.now()),

    @Column(nullable = false, length = 10)
    var sexo: String,

    @Column(nullable = false)
    var casado: Boolean = false,

    @Column(nullable = false)
    var embarazada: Boolean = false
)
