package com.gabrielspassos.dto

import com.gabrielspassos.model.BankEntity

data class BankDTO(val id: Long, val name: String, val code: String) {

    companion object {
        fun map(bankEntity: BankEntity): BankDTO {
            return BankDTO(
                id = bankEntity.id,
                name = bankEntity.name,
                code = bankEntity.code
            )
        }
    }

}
