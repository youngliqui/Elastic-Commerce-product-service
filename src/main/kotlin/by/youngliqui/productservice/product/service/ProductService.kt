package by.youngliqui.productservice.product.service

import by.youngliqui.productservice.product.api.dto.ProductCreateRequest
import by.youngliqui.productservice.product.api.dto.ProductResponse
import by.youngliqui.productservice.product.api.dto.ProductUpdateRequest
import java.util.UUID

interface ProductService {
    fun findById(id: UUID): ProductResponse

    fun findAll(): List<ProductResponse>

    fun create(request: ProductCreateRequest): ProductResponse

    fun update(id: UUID, request: ProductUpdateRequest): ProductResponse

    fun delete(id: UUID)
}