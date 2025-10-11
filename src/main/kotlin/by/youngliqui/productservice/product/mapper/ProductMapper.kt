package by.youngliqui.productservice.product.mapper

import by.youngliqui.productservice.product.api.dto.ProductCreateRequest
import by.youngliqui.productservice.product.api.dto.ProductResponse
import by.youngliqui.productservice.product.model.Product

fun Product.toResponse(): ProductResponse = ProductResponse(
    id = this.id,
    name = this.name,
    description = this.description,
    price = this.price,
    brand = this.brand,
    category = this.category
)

fun ProductCreateRequest.toEntity(): Product = Product(
    name = this.name,
    description = this.description,
    price = this.price,
    brand = this.brand,
    category = this.category
)