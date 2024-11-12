package com.example.unsecuredseguros.controller

import com.example.unsecuredseguros.exceptions.BadRequestException
import com.example.unsecuredseguros.exceptions.NotFoundException
import com.example.unsecuredseguros.model.Seguro
import com.example.unsecuredseguros.service.SeguroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/seguros")
class SeguroController {

    @Autowired // Inicializa automaticamente springboot, no hace falta hacerlo nosotros
    private lateinit var seguroService: SeguroService


    /**
     * Get ->  /seguros/{id}
     */
    @GetMapping("/{id}") // Uri de este metodo --> localhost:8080/{id}
    fun getById(
        @PathVariable id: String
    ): Seguro {

        if (id.isBlank()) throw BadRequestException("El ID no puede estar vacío")

        if (id.toLongOrNull() !is Long) throw BadRequestException("El ID debe ser un número válido")


        return seguroService.getById(id)
    }


    /**
     * Get --> /seguros
     */
    @GetMapping("/")
    fun getAll(): List<Seguro> {
        return seguroService.getAll()
    }


    /**
     * Post --> /seguros
     */
    @PostMapping("/")
    fun insert(
        @RequestBody seguro: Seguro?
    ): Seguro{

        if (seguro == null) throw BadRequestException("El cuerpo de la solicitud no puede estar vacío")


        println(seguro)
        return seguroService.insert(seguro)
    }


    /**
     * Put --> /seguros/{id}
     */
    @PutMapping("/{id}")
    fun updateByid(
        @PathVariable id: String,
        @RequestBody newSeguro: Seguro
    ): Seguro {
        if (id.isBlank()) throw BadRequestException("La ID no puede estar vacía")

        if (id.toLongOrNull() !is Long) throw BadRequestException("El ID debe ser un número válido")

        return seguroService.updateById(id, newSeguro)
    }


    /**
     * DELETE --> /seguros/{id }
     */
    @DeleteMapping("/{id}")
    fun deleteById(
        @PathVariable id: String
    ) {
        if (id.isBlank()) throw BadRequestException("La ID no puede estar vacía")

        if (id.toLongOrNull() !is Long) throw BadRequestException("El ID debe ser un número válido")


        seguroService.deleteById(id)
        println("Seguro eliminado correcatemene")
    }





}