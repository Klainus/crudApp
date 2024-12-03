package com.example.crudapp.entity

import jakarta.persistence.*

@Entity
@Table(name = "persons")
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var email: String
)
