package io.maaaae.panama_canal_gateway.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "dynamic_route_config")
data class DynamicRouteConfig(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val uri: String,
    val predicates: String,
    val filters: String,
    val routeOrder: Int
)