package by.youngliqui.productservice.product.service

import by.youngliqui.productservice.config.RabbitMQConfig
import by.youngliqui.productservice.product.event.ProductCreatedEvent
import by.youngliqui.productservice.product.event.ProductDeletedEvent
import by.youngliqui.productservice.product.event.ProductUpdatedEvent
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class RabbitMQProducerServiceImpl(
    private val rabbitTemplate: RabbitTemplate
) : RabbitMQProducerService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun sendProductCreated(event: ProductCreatedEvent) {
        val routingKey = "${RabbitMQConfig.ROUTING_KEY_PREFIX}.created"
        log.info("Отправка события ProductCreatedEvent с ключом '$routingKey': $event")
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, routingKey, event)
    }

    override fun sendProductUpdated(event: ProductUpdatedEvent) {
        val routingKey = "${RabbitMQConfig.ROUTING_KEY_PREFIX}.updated"
        log.info("Отправка события ProductUpdatedEvent с ключом '$routingKey': $event")
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, routingKey, event)
    }

    override fun sendProductDeleted(event: ProductDeletedEvent) {
        val routingKey = "${RabbitMQConfig.ROUTING_KEY_PREFIX}.deleted"
        log.info("Отправка события ProductDeletedEvent с ключом '$routingKey': $event")
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, routingKey, event)
    }
}