package by.youngliqui.productservice.product.api

import by.youngliqui.productservice.product.api.dto.ProductCreateRequest
import by.youngliqui.productservice.product.api.dto.ProductResponse
import by.youngliqui.productservice.product.api.dto.ProductUpdateRequest
import by.youngliqui.productservice.product.service.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    val productService: ProductService
) : ProductControllerDoc {

    @GetMapping("/{id}")
    override fun getProductById(@PathVariable id: UUID): ProductResponse {
        return productService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createProduct(@Valid @RequestBody request: ProductCreateRequest): ProductResponse {
        return productService.create(request)
    }

    @PutMapping("/{id}")
    override fun updateProduct(
        @PathVariable id: UUID,
        @Valid @RequestBody request: ProductUpdateRequest
    ): ProductResponse {
        return productService.update(id, request)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun deleteProduct(@PathVariable id: UUID) {
        productService.delete(id)
    }
}