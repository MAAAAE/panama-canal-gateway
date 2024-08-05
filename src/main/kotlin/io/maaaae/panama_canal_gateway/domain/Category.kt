package io.maaaae.panama_canal_gateway.domain

import jakarta.persistence.*

@Entity
@Table(name = "category")
data class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val categoryId: Long = 0,
    @Column(unique = true)
    var name: String,
    var description: String?,
    @Column(nullable = false)
    var domain: String,
    @Column(nullable = false)
    var secret: String,

    ) {

}