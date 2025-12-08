package by.youngliqui.productservice.product.service

import by.youngliqui.productservice.product.api.dto.DatabaseStatusResponse
import by.youngliqui.productservice.product.api.dto.ProductCreateRequest
import by.youngliqui.productservice.product.api.dto.ProductListResponse
import by.youngliqui.productservice.product.api.dto.ProductResponse
import by.youngliqui.productservice.product.api.dto.ProductUpdateRequest
import java.util.*

interface ProductService {
    fun findById(id: UUID): ProductResponse

    fun findAll(): ProductListResponse

    fun getDatabaseStatus(): DatabaseStatusResponse

    fun create(request: ProductCreateRequest): ProductResponse

    fun createBatch(requests: List<ProductCreateRequest>): ProductListResponse

    fun update(id: UUID, request: ProductUpdateRequest): ProductResponse

    fun delete(id: UUID)
}