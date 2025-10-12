package by.youngliqui.productservice.product.service

import by.youngliqui.productservice.product.event.ProductCreatedEvent
import by.youngliqui.productservice.product.event.ProductDeletedEvent
import by.youngliqui.productservice.product.event.ProductUpdatedEvent

interface RabbitMQProducerService {

    fun sendProductCreated(event: ProductCreatedEvent)

    fun sendProductUpdated(event: ProductUpdatedEvent)

    fun sendProductDeleted(event: ProductDeletedEvent)

}