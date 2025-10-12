package by.youngliqui.productservice.product.api.dto

import java.util.*

data class ProductResponse(
    val id: UUID?,
    val name: String,
    val description: String?,
    val price: Double,
    val brand: String,
    val category: String
)