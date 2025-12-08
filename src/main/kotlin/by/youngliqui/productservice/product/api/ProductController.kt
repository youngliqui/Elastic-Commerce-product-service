package by.youngliqui.productservice.product.api

import by.youngliqui.productservice.product.api.dto.DatabaseStatusResponse
import by.youngliqui.productservice.product.api.dto.ProductCreateRequest
import by.youngliqui.productservice.product.api.dto.ProductListResponse
import by.youngliqui.productservice.product.api.dto.ProductResponse
import by.youngliqui.productservice.product.api.dto.ProductUpdateRequest
import by.youngliqui.productservice.product.service.ProductService
import java.util.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    val productService: ProductService
) : ProductControllerDoc {

    @GetMapping
    override fun getAllProducts(): ProductListResponse {
        return productService.findAll()
    }

    @GetMapping("/status")
    override fun getDatabaseStatus(): DatabaseStatusResponse {
        return productService.getDatabaseStatus()
    }

    @GetMapping("/{id}")
    override fun getProductById(@PathVariable id: UUID): ProductResponse {
        return productService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createProduct(@RequestBody request: ProductCreateRequest): ProductResponse {
        return productService.create(request)
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    override fun createProductsBatch(@RequestBody requests: List<ProductCreateRequest>): ProductListResponse {
        return productService.createBatch(requests)
    }

    @PutMapping("/{id}")
    override fun updateProduct(
        @PathVariable id: UUID,
        @RequestBody request: ProductUpdateRequest
    ): ProductResponse {
        return productService.update(id, request)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun deleteProduct(@PathVariable id: UUID) {
        productService.delete(id)
    }
}