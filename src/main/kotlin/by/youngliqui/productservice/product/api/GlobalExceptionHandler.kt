package by.youngliqui.productservice.product.api

import by.youngliqui.productservice.product.exception.ErrorResponse
import by.youngliqui.productservice.product.exception.ProductNotFoundException
import by.youngliqui.productservice.product.exception.RabbitMQException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(ProductNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleProductNotFound(
        ex: ProductNotFoundException,
        request: WebRequest
    ): ErrorResponse {
        log.warn("Product not found: ${ex.message}")

        return ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = "NOT_FOUND",
            message = ex.message ?: "Товар не найден",
            path = request.getDescription(false).replace("uri=", "")
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ErrorResponse {
        log.warn("Validation failed: ${ex.message}")

        val fieldErrors = mutableMapOf<String, String>()
        ex.bindingResult?.allErrors?.forEach { error ->
            if (error is FieldError) {
                fieldErrors[error.field] = error.defaultMessage ?: "Invalid value"
            }
        }

        return ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = "VALIDATION_ERROR",
            message = "Ошибка валидации входных данных",
            path = request.getDescription(false).replace("uri=", ""),
            details = mapOf("fieldErrors" to fieldErrors)
        )
    }

    @ExceptionHandler(RabbitMQException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleRabbitMQException(
        ex: RabbitMQException,
        request: WebRequest
    ): ErrorResponse {
        log.error("RabbitMQ error occurred: ${ex.message}", ex)

        return ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = "MESSAGING_ERROR",
            message = "Ошибка при отправке события. Операция была выполнена, но оповещение других сервисов не удалось.",
            path = request.getDescription(false).replace("uri=", ""),
            details = mapOf("eventType" to (ex.eventType ?: "UNKNOWN"))
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGlobalException(
        ex: Exception,
        request: WebRequest
    ): ErrorResponse {
        log.error("Unexpected error occurred: ${ex.message}", ex)

        return ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = "INTERNAL_SERVER_ERROR",
            message = "Внутренняя ошибка сервера. Пожалуйста, попробуйте позже.",
            path = request.getDescription(false).replace("uri=", "")
        )
    }
}