package by.youngliqui.productservice.product.event

import by.youngliqui.productservice.product.api.dto.ProductResponse
import java.util.*

sealed interface ProductEvent

data class ProductCreatedEvent(
    val product: ProductResponse
) : ProductEvent

data class ProductUpdatedEvent(
    val product: ProductResponse
) : ProductEvent

data class ProductDeletedEvent(
    val id: UUID
)