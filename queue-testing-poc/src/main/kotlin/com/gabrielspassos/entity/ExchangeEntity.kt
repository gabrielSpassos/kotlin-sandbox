package com.gabrielspassos.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Table(name = "exchanges")
class ExchangeEntity(

    @Id
    @Column("id")
    val id: UUID? = null,

    @Column("user_id")
    val userId: UUID,

    @Column("usd_to_brl_exchange_rate")
    val usdToBrlExchangeRate: BigDecimal,

    @Column("rate_date_time")
    val rateDateTime: LocalDateTime,

    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column("updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    fun copy(id: UUID? = this.id,
             userId: UUID = this.userId,
             usdToBrlExchangeRate: BigDecimal = this.usdToBrlExchangeRate,
             rateDateTime: LocalDateTime = this.rateDateTime,
             createdAt: LocalDateTime = this.createdAt,
             updatedAt: LocalDateTime = this.updatedAt,
             ): ExchangeEntity {
        return ExchangeEntity(
            id = id,
            userId = userId,
            usdToBrlExchangeRate = usdToBrlExchangeRate,
            rateDateTime = rateDateTime,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}