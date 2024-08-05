package io.maaaae.panama_canal_gateway.domain

import jakarta.persistence.*

@Entity
@Table(name = "filter_config")
data class FilterConfig(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    val dynamicRouteConfig: DynamicRouteConfig,
    val filterName: String,
    val param: String,
    val value: String
)