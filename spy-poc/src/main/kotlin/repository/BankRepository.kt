package com.gabrielspassos.repository

import com.gabrielspassos.model.BankEntity
import java.util.Optional

interface BankRepository {

    fun findById(id: Long): Optional<BankEntity>

    fun insert(bankEntity: BankEntity): BankEntity

    fun update(bankEntity: BankEntity): BankEntity

}