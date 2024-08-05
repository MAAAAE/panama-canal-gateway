package io.maaaae.panama_canal_gateway.domain.api_info

import io.maaaae.panama_canal_gateway.common.constant.Method
import io.maaaae.panama_canal_gateway.domain.Category
import jakarta.persistence.*

@Entity
@Table(name = "api_info")
data class ApiInfo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var apiId: Long = 0,
    var name: String,
    var endpoint: String,
    @Enumerated(EnumType.STRING)
    var method: Method,
    var headers: String,
    @ManyToOne @JoinColumn(name = "category_id")
    var category: Category
) {
}