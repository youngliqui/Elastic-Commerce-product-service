package by.youngliqui.productservice.product.exception

import java.time.Instant
import java.util.*

data class ErrorResponse(
    val timestamp: Instant = Instant.now(),
    val status: Int,
    val error: String,
    val message: String,
    val path: String?,
    val requestId: String = UUID.randomUUID().toString(),
    val details: Map<String, Any>? = null
)