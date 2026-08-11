package com.gabrielspassos.repository

import com.gabrielspassos.entity.ExchangeEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ExchangeRepository : CrudRepository<ExchangeEntity, UUID> {
}