package by.youngliqui.productservice.product.service

import by.youngliqui.productservice.product.api.dto.ProductCreateRequest
import by.youngliqui.productservice.product.api.dto.ProductResponse
import by.youngliqui.productservice.product.api.dto.ProductUpdateRequest
import by.youngliqui.productservice.product.event.ProductCreatedEvent
import by.youngliqui.productservice.product.event.ProductDeletedEvent
import by.youngliqui.productservice.product.event.ProductUpdatedEvent
import by.youngliqui.productservice.product.exception.ProductNotFoundException
import by.youngliqui.productservice.product.mapper.toEntity
import by.youngliqui.productservice.product.mapper.toResponse
import by.youngliqui.productservice.product.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val producerService: RabbitMQProducerService
) : ProductService {

    @Transactional(readOnly = true)
    override fun findById(id: UUID): ProductResponse {
        return productRepository.findByIdOrNull(id)
            ?.toResponse()
            ?: throw ProductNotFoundException(id)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<ProductResponse> {
        return productRepository.findAll().map { it.toResponse() }
    }

    @Transactional
    override fun create(request: ProductCreateRequest): ProductResponse {
        val product = request.toEntity()
        val savedProduct = productRepository.save(product)

        val response = savedProduct.toResponse()
        producerService.sendProductCreated(ProductCreatedEvent(response))

        return response
    }

    @Transactional
    override fun update(id: UUID, request: ProductUpdateRequest): ProductResponse {
        val existingProduct = productRepository.findByIdOrNull(id)
            ?: throw ProductNotFoundException(id)

        existingProduct.apply {
            name = request.name ?: name
            description = request.description ?: description
            price = request.price ?: price
            brand = request.brand ?: brand
            category = request.category ?: category
        }

        val updatedProduct = productRepository.save(existingProduct)

        val response = updatedProduct.toResponse()
        producerService.sendProductUpdated(ProductUpdatedEvent(response))

        return response
    }

    @Transactional
    override fun delete(id: UUID) {
        if (!productRepository.existsById(id)) {
            throw ProductNotFoundException(id)
        }
        productRepository.deleteById(id)

        producerService.sendProductDeleted(ProductDeletedEvent(id))
    }
}