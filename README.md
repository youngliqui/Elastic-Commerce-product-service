# Product Service 📦

Сервис управления товарным каталогом. Является "Master System" для данных о товарах. Отвечает за надежное хранение данных в PostgreSQL и асинхронную синхронизацию с поисковым движком.

## 🛠 Технологический стек
- **Language:** Kotlin
- **Framework:** Spring Boot 3
- **Database:** PostgreSQL (Liquibase migration)
- **Message Broker:** RabbitMQ (Producer)

## 🚀 Ключевые функции
- **CRUD операции:** Создание, обновление и удаление товаров.
- **Event-Driven Architecture:** При изменении данных отправляет события (`product.created`, `product.updated`) в RabbitMQ.
- **Batch Processing:** API для массовой загрузки товаров (для первоначального заполнения базы).
