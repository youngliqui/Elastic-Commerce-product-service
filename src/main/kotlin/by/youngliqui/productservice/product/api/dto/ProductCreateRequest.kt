package by.youngliqui.productservice.product.api.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class ProductCreateRequest(
    @field:NotBlank(message = "Название товара не может быть пустым")
    @field:Size(max = 255, message = "Длина названия не должна превышать 255 символов")
    val name: String,

    @field:Size(max = 2000, message = "Длина описания не должна превышать 2000 символов")
    val description: String?,

    @field:Positive(message = "Цена должна быть положительным числом")
    val price: Double,

    @field:NotBlank(message = "Бренд не может быть пустым")
    val brand: String,

    @field:NotBlank(message = "Категория не может быть пустой")
    val category: String
)
