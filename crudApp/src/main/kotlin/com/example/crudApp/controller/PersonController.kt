package com.example.crudapp.controller

import com.example.crudapp.entity.Person
import com.example.crudapp.repository.PersonRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/persons")
class PersonController(private val personRepository: PersonRepository) {

    @GetMapping
    fun getAllPersons(): List<Person> = personRepository.findAll()

    @PostMapping
    fun createPerson(@RequestBody person: Person): Person = personRepository.save(person)

    @PutMapping("/{id}")
    fun updatePerson(
        @PathVariable id: Long,
        @RequestBody updatedPerson: Person
    ): ResponseEntity<Person> {
        val person = personRepository.findById(id)
        return if (person.isPresent) {
            val newPerson = person.get().copy(
                name = updatedPerson.name,
                email = updatedPerson.email
            )
            ResponseEntity.ok(personRepository.save(newPerson))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable id: Long): ResponseEntity<Void> {
        return if (personRepository.existsById(id)) {
            personRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
