package com.example.pruebaM.controller

import com.example.pruebaM.model.Paciente
import com.example.pruebaM.repository.PacienteRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = ["*"])
class PacienteController(private val repository: PacienteRepository) {

    @PostMapping
    fun crearPaciente(@RequestBody paciente: Paciente): ResponseEntity<Map<String, Any>> {
        return try {
            val nuevoPaciente = repository.save(paciente)
            ResponseEntity(
                mapOf(
                    "status" to "success",
                    "message" to "Paciente creado exitosamente",
                    "data" to nuevoPaciente
                ),
                HttpStatus.CREATED
            )
        } catch (e: Exception) {
            ResponseEntity(
                mapOf(
                    "status" to "error",
                    "message" to (e.message ?: "Error al crear paciente")
                ),
                HttpStatus.BAD_REQUEST
            )
        }
    }

    @GetMapping
    fun listarPacientes(): ResponseEntity<Map<String, Any>> {
        val pacientes = repository.findAll()
        return ResponseEntity(
            mapOf(
                "status" to "success",
                "data" to pacientes
            ),
            HttpStatus.OK
        )
    }

    @GetMapping("/{id}")
    fun obtenerPaciente(@PathVariable id: Long): ResponseEntity<Map<String, Any>> {
        val paciente = repository.findById(id)
        return if (paciente.isPresent) {
            ResponseEntity(
                mapOf(
                    "status" to "success",
                    "data" to paciente.get()
                ),
                HttpStatus.OK
            )
        } else {
            ResponseEntity(
                mapOf(
                    "status" to "fail",
                    "message" to "Paciente no encontrado"
                ),
                HttpStatus.NOT_FOUND
            )
        }
    }

    @DeleteMapping("/{id}")
    fun eliminarPaciente(@PathVariable id: Long): ResponseEntity<Map<String, Any>> {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity(
                mapOf(
                    "status" to "success",
                    "message" to "Paciente eliminado exitosamente"
                ),
                HttpStatus.OK
            )
        } else {
            ResponseEntity(
                mapOf(
                    "status" to "fail",
                    "message" to "Paciente no encontrado"
                ),
                HttpStatus.NOT_FOUND
            )
        }
    }

    @PutMapping("/{id}")
    fun actualizarPaciente(
        @PathVariable id: Long,
        @RequestBody paciente: Paciente
    ): ResponseEntity<Map<String, Any>> {
        return if (repository.existsById(id)) {
            val pacienteActualizado = repository.save(
                paciente.copy(id = id)
            )
            ResponseEntity(
                mapOf(
                    "status" to "success",
                    "message" to "Paciente actualizado exitosamente",
                    "data" to pacienteActualizado
                ),
                HttpStatus.OK
            )
        } else {
            ResponseEntity(
                mapOf(
                    "status" to "fail",
                    "message" to "Paciente no encontrado"
                ),
                HttpStatus.NOT_FOUND
            )
        }
    }
}