package by.youngliqui.productservice.product.exception

import java.util.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ProductNotFoundException(id: UUID) : RuntimeException("Товар с ID '$id' не найден.")