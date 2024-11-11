package com.example.unsecuredseguros.controller

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
     * GetById
     */
    @GetMapping("/{id}") // Uri de este metodo --> localhost:8080/{id}
    fun getById(
        @PathVariable id: String
    ): Seguro? {

        // Comprobacion basica de los parametros
        if (id.isBlank()){
            return null
        }

        // Comunico el controller con el service
        return seguroService.getById(id)
    }


    @GetMapping("/")
    fun getAll(): List<Seguro> {

        return seguroService.getAll()
    }



    @PostMapping("/")
    fun insert(
        @RequestBody seguro: Seguro?
    ): Seguro?{

        if (seguro == null){
            return null
        }

        // Responder cliente
        println(seguro)
        return seguroService.insert(seguro)
    }

    @PutMapping("/{id}")
    fun updateByid(
        @PathVariable id: String,
        @RequestBody newSeguro: Seguro
    ): Seguro? {
        if (id.isBlank()){
            return null
        }

        return seguroService.updateById(id, newSeguro)

    }


}