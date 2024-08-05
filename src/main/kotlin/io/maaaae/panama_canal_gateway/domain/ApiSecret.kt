package io.maaaae.panama_canal_gateway.domain

import io.maaaae.panama_canal_gateway.domain.api_info.ApiInfo
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "api_secret")
data class ApiSecret(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val secretId: Long = 0,
    @ManyToOne @JoinColumn(name = "category_id")
    val category: Category,
    @Column(nullable = false)
    val secretKey: String,
    val description: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)