package by.youngliqui.productservice.product.api.dto

import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class ProductUpdateRequest(
    @field:Size(max = 255, message = "Длина названия не должна превышать 255 символов")
    val name: String?,

    @field:Size(max = 2000, message = "Длина описания не должна превышать 2000 символов")
    val description: String?,

    @field:Positive(message = "Цена должна быть положительным числом")
    val price: Double?,

    val brand: String?,
    val category: String?
)