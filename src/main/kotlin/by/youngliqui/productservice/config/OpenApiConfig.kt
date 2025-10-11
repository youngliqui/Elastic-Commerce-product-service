package by.youngliqui.productservice.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Product Service API")
                    .version("1.0.0")
                    .description("Микросервис для управления каталогом товаров (создание, обновление, удаление).")
            )
    }
}