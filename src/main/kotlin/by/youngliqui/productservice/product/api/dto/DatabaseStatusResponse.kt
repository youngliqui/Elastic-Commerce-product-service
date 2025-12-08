package by.youngliqui.productservice.product.api.dto

data class DatabaseStatusResponse(
    val totalProducts: Long,
    val totalBrands: Long,
    val totalCategories: Long,
    val categoryStats: Map<String, Long>
)