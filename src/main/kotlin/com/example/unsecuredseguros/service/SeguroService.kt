package com.example.unsecuredseguros.service

import com.example.unsecuredseguros.exceptions.BadRequestException
import com.example.unsecuredseguros.exceptions.NotFoundException
import com.example.unsecuredseguros.model.Seguro
import com.example.unsecuredseguros.repository.SeguroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SeguroService {

    @Autowired
    private lateinit var seguroRepository: SeguroRepository

    // Get /seguros/{id}
    fun getById(id: String): Seguro{

        val seguro: Seguro = seguroRepository.findByIdOrNull(id.toLong()) ?: throw NotFoundException("Seguro con ID $id no encontrado")
        return seguro
    }


    // Post /seguros
    fun insert(seguro: Seguro): Seguro{
        validarDatos(seguro)
        if (!seguro.casado) seguro.numHijos = 0
        if (seguro.embarazada) seguro.sexo = "Mujer"

        return seguroRepository.save(seguro)
    }


    // Get /seguros
    fun getAll(): List<Seguro>{
        return seguroRepository.findAll()
    }


    // Put
    fun updateById(id: String, datos: Seguro): Seguro {
        val seguro = seguroRepository.findByIdOrNull(id.toLong()) ?: throw NotFoundException("Seguro con ID $id no existe")

        validarDatos(datos)

        // Reasignar datos
        seguro.nif = datos.nif
        seguro.nombre = datos.nombre
        seguro.ape1 = datos.ape1
        seguro.ape2 = datos.ape2
        seguro.edad = datos.edad
        seguro.numHijos = if (!datos.casado) 0 else datos.numHijos
        seguro.sexo = if (datos.embarazada) "Mujer" else datos.sexo
        seguro.casado = datos.casado
        seguro.embarazada = datos.embarazada

        return seguroRepository.save(seguro)
    }


    // Delete
    fun deleteById(id: String){
        val seguro = seguroRepository.findByIdOrNull(id.toLong()) ?: throw NotFoundException("Seguro con id $id no existe")

        seguroRepository.delete(seguro)
    }



    fun validarDatos(datos: Seguro){
        if (datos.idSeguro <= 0) throw BadRequestException("La id introducida no es válida")
        if (datos.nombre.isBlank()) throw BadRequestException("El nombre no puede estar vacío")
        if (datos.ape1.isBlank()) throw BadRequestException("El primer apellido no puede estar vacío")
        if (datos.edad !in 0..100) throw BadRequestException("La edad debe estar en el rango 0-100")
        if (datos.edad in 0..17) throw BadRequestException("No es posible crear seguro a menor de 18 años")
        if (datos.numHijos < 0) throw BadRequestException("El numero de hijos no puede ser menor a 0")
        if (datos.sexo.isBlank()) throw BadRequestException("El sexo no puede estar vacío")
    }

}