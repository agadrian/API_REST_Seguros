package com.example.unsecuredseguros.service

import com.example.unsecuredseguros.model.Seguro
import com.example.unsecuredseguros.repository.SeguroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SeguroService {

    @Autowired
    private lateinit var seguroRepository: SeguroRepository


    fun getById(id: String): Seguro?{
        // Implementar la logica de negocio
        var idL: Long = 0
        try {
            idL = id.toLong()
        }catch (e: Exception){
            e.printStackTrace()
            return null
        }

        // Comunicacion con repository
        val seguro: Seguro? = seguroRepository.findByIdOrNull(idL)
        return seguro
    }


    fun insert(seguro: Seguro): Seguro?{
        try {

            validarDatos(seguro)

            if (!seguro.casado) seguro.numHijos = 0
            if (seguro.embarazada) seguro.sexo = "Mujer"


        }catch (e: Exception){
            e.printStackTrace()
            return null
        }

        return seguroRepository.save(seguro)
    }


    fun getAll(): List<Seguro>{
        return seguroRepository.findAll()
    }


    fun updateById(id: String, datos: Seguro): Seguro? {
        try {
            val seguro = seguroRepository.findByIdOrNull(id.toLong()) ?: throw Exception("Seguro con id $id no existe")


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

        }catch (e: Exception){
            e.printStackTrace()
            return null
        }
    }


    fun validarDatos(datos: Seguro){
        if (datos.idSeguro <= 0) throw Exception("La id introducida no es válida")
        if (datos.nombre.isBlank()) throw Exception("El nombre no puede estar vacío")
        if (datos.ape1.isBlank()) throw Exception("El primer apellido no puede estar vacío")
        if (datos.edad !in 0..100) throw Exception("La edad debe estar en el rango 0-100")
        if (datos.edad in 0..17) throw Exception("No es posible crear seguro a menor de 18 años")
        if (datos.numHijos < 0) throw Exception("El numero de hijos no puede ser menor a 0")
        if (datos.sexo.isBlank()) throw Exception("El sexo no puede estar vacío")
    }

}