package by.youngliqui.productservice.product.model

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

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