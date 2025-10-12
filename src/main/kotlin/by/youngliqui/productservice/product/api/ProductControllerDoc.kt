package by.youngliqui.productservice.product.api

import by.youngliqui.productservice.product.api.dto.ProductCreateRequest
import by.youngliqui.productservice.product.api.dto.ProductResponse
import by.youngliqui.productservice.product.api.dto.ProductUpdateRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import java.util.UUID

@Tag(name = "Product API", description = "API для управления товарами")
interface ProductControllerDoc {

    @Operation(summary = "Получить список всех товаров")
    @ApiResponse(responseCode = "200", description = "Список товаров получен успешно")
    fun getAllProducts(): List<ProductResponse>

    @Operation(summary = "Получить товар по ID")
    @ApiResponse(responseCode = "200", description = "Товар найден")
    @ApiResponse(responseCode = "400", description = "Товар не найден")
    fun getProductById(id: UUID): ProductResponse

    @Operation(summary = "Создать новый товар")
    @ApiResponse(responseCode = "201", description = "Товар успешно создан")
    @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    fun createProduct(request: ProductCreateRequest): ProductResponse

    @Operation(summary = "Обновить существующий товар")
    @ApiResponse(responseCode = "200", description = "Товар успешно обновлен")
    @ApiResponse(responseCode = "400", description = "Товар не найден")
    fun updateProduct(id: UUID, request: ProductUpdateRequest): ProductResponse

    @Operation(summary = "Удалить товар")
    @ApiResponse(responseCode = "204", description = "Товар успешно удален")
    @ApiResponse(responseCode = "400", description = "Товар не найден")
    fun deleteProduct(id: UUID)
}