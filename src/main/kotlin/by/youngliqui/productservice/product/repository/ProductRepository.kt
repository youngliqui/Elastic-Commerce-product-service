package by.youngliqui.productservice.product.repository

import by.youngliqui.productservice.product.model.Product
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, UUID> {
    @Query("SELECT COUNT(DISTINCT p.brand) FROM Product p")
    fun countDistinctBrands(): Long

    @Query("SELECT COUNT(DISTINCT p.category) FROM Product p")
    fun countDistinctCategories(): Long

    @Query("SELECT p.category as category, COUNT(p) as count FROM Product p GROUP BY p.category")
    fun getCategoryStats(): List<CategoryStat>

    interface CategoryStat {
        fun getCategory(): String
        fun getCount(): Long
    }
}