package io.maaaae.panama_canal_gateway.domain

import org.springframework.data.jpa.repository.JpaRepository

interface RouteConfigRepository: JpaRepository<DynamicRouteConfig, Long> {

}
