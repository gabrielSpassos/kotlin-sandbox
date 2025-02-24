package com.gabrielspassos.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class PersonDAO (@Autowired private val jdbcTemplate: JdbcTemplate) {

    fun startPersonTable() {
        jdbcTemplate.execute(createPersonTableScript())
    }

    private fun createPersonTableScript() =
        """
        create table if not exists person(
            id              varchar(36) primary key not null default (UUID()),
            external_id     varchar(36) not null default (UUID()),
            name            varchar(255) not null
        );
        """.trimIndent()
}