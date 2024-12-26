package com.gabrielspassos.service

import com.gabrielspassos.dao.BankDAO
import com.gabrielspassos.dto.BankDTO
import com.gabrielspassos.model.BankEntity
import java.util.Optional

class BankService(private val bankDAO: BankDAO) {

    fun findById(id: Long): Optional<BankDTO> {
        return bankDAO.findById(id)
            .map { entity -> BankDTO.map(entity) }
    }

    fun insert(bankDTO: BankDTO): BankDTO {
        val entity = BankEntity.map(bankDTO)
        val savedEntity = bankDAO.insert(entity)
        return BankDTO.map(savedEntity)
    }

    fun update(bankDTO: BankDTO): BankDTO {
        val entity = BankEntity.map(bankDTO)
        val savedEntity = bankDAO.update(entity)
        return BankDTO.map(savedEntity)
    }

    fun seed(): Boolean {
        insert(BankDTO(1, "Brazil Bank", "100"))
        insert(BankDTO(2, "Rio Grande do Sul Bank", "333"))
        insert(BankDTO(3, "North Bank", "745"))
        return true
    }

}