package com.gabrielspassos.dao

import com.gabrielspassos.model.BankEntity
import com.gabrielspassos.repository.BankRepository
import java.util.Optional

class BankDAO(private val bankRepository: BankRepository) {

    fun findById(id: Long): Optional<BankEntity> {
        return bankRepository.findById(id)
    }

    fun insert(bankEntity: BankEntity): BankEntity {
        return bankRepository.insert(bankEntity)
    }

    fun update(bankEntity: BankEntity): BankEntity {
        return bankRepository.update(bankEntity)
    }

}