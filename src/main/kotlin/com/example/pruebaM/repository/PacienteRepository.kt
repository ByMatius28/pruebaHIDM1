package com.example.pruebaM.repository

import com.example.pruebaM.model.Paciente
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PacienteRepository : JpaRepository<Paciente, Long>