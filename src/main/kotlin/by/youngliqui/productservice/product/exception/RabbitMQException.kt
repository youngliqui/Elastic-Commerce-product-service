package by.youngliqui.productservice.product.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class RabbitMQException(message: String, val eventType: String? = null, cause: Throwable? = null) :
    RuntimeException(message, cause)