package by.youngliqui.productservice.product.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "products")
data class Product(
    @Id
    @UuidGenerator
    @Column(updatable = false, nullable = false)
    val id: UUID? = null,

    @Column(nullable = false)
    var name: String,

    var description: String?,

    @Column(nullable = false)
    var price: Double,

    @Column(nullable = false)
    var brand: String,

    @Column(nullable = false)
    var category: String,

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant = Instant.now()
)