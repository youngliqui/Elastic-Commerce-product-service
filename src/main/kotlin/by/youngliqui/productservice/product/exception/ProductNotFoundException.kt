package by.youngliqui.productservice.product.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@ResponseStatus(HttpStatus.BAD_REQUEST)
class ProductNotFoundException(id: UUID) : RuntimeException("Товар с ID '$id' не найден.")