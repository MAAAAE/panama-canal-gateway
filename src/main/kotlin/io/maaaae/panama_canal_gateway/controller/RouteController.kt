package io.maaaae.panama_canal_gateway.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.gateway.event.RefreshRoutesEvent
import org.springframework.cloud.gateway.route.RouteDefinitionWriter
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/routes")
class RouteController(
    private val routeDefinitionWriter: RouteDefinitionWriter,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    @PostMapping("/refresh")
    fun refreshRoutes(): ResponseEntity<Any> {
        applicationEventPublisher.publishEvent(RefreshRoutesEvent(this))
        return ResponseEntity.status(HttpStatus.OK).build()
    }
}