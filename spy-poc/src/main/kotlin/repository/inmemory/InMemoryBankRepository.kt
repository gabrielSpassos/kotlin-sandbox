package com.gabrielspassos.repository.inmemory

import com.gabrielspassos.model.BankEntity
import com.gabrielspassos.repository.BankRepository
import java.util.Optional

class InMemoryBankRepository: BankRepository {

    private val banks = mutableListOf<BankEntity>()

    override fun findById(id: Long): Optional<BankEntity> {
        val bank = banks.firstOrNull { bankEntity -> bankEntity.id == id }
        return Optional.ofNullable(bank)
    }

    override fun insert(bankEntity: BankEntity): BankEntity {
        banks.add(bankEntity)
        return bankEntity
    }

    override fun update(bankEntity: BankEntity): BankEntity {
        val bank = findById(bankEntity.id)
            .orElseThrow { throw IllegalArgumentException("Bank not found") }

        banks.remove(bank)
        banks.add(bankEntity)
        return bankEntity
    }

}