package io.maaaae.panama_canal_gateway.config

import io.maaaae.panama_canal_gateway.domain.DynamicRouteConfig
import io.maaaae.panama_canal_gateway.domain.RouteConfigRepository
import jakarta.transaction.Transactional
import org.springframework.cloud.gateway.filter.FilterDefinition
import org.springframework.cloud.gateway.route.RouteDefinition
import org.springframework.cloud.gateway.route.RouteDefinitionLocator
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.net.URI

@Service
class DynamicRouteService(private val routeConfigRepository: RouteConfigRepository) : RouteDefinitionLocator {

    @Transactional
    override fun getRouteDefinitions(): Flux<RouteDefinition> {
        val routes = routeConfigRepository.findAll()
        val routeDefinitions = routes.map { routeConfig ->
            RouteDefinition().apply {
                id = routeConfig.id.toString()
                uri = URI.create(routeConfig.uri)
                predicates = listOf(org.springframework.cloud.gateway.handler.predicate.PredicateDefinition().apply {
                    name = "Path"
                    args = mapOf("pattern" to routeConfig.predicates)
                })
                filters = routeConfig.filterConfigs.map {
                    FilterDefinition().apply {
                        name = it.filterName
                        args = mapOf("Name" to it.param, "Value" to it.value)
                    }
                }
            }
        }
        return Flux.fromIterable(routeDefinitions)
    }
}

@Configuration
class GatewayConfig {

    @Bean
    fun customRouteLocator(builder: RouteLocatorBuilder, dynamicRouteService: DynamicRouteService): RouteLocatorBuilder.Builder {
        val routes = dynamicRouteService.routeDefinitions.collectList().block()
        val routeBuilder = builder.routes()
        routes?.forEach { routeDefinition ->
            routeBuilder.route(routeDefinition.id) {
                it.path(routeDefinition.predicates.first().args["pattern"]!!)
                    .uri(routeDefinition.uri)
            }
        }
        return routeBuilder
    }
}