package com.example.pruebaM.model

import jakarta.persistence.*

@Entity
@Table(name = "pacientes")
data class Paciente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val nombre: String = "",

    @Column(nullable = false, unique = true)
    val email: String = "",

    val telefono: String? = null,

    @Column(name = "fecha_registro")
    val fechaRegistro: String = java.time.LocalDate.now().toString()
)