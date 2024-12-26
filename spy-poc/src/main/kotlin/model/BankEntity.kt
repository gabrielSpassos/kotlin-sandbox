package com.gabrielspassos.model

import com.gabrielspassos.dto.BankDTO

data class BankEntity(val id: Long, val name: String, val code: String) {

    companion object {
        fun map(bankDTO: BankDTO): BankEntity {
            return BankEntity(
                id = bankDTO.id,
                name = bankDTO.name,
                code = bankDTO.code
            )
        }
    }

}
