package by.youngliqui.productservice.product.api.dto

data class ProductListResponse(
    val totalCount: Int,
    val items: List<ProductResponse>
)