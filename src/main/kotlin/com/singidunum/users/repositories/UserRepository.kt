package com.singidunum.users.repositories

import com.singidunum.users.database.tables.UsersTable
import com.singidunum.users.models.User
import com.singidunum.users.models.UserDigest
import com.singidunum.users.utils.toUUID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class UserRepository {
    fun getAllUsers(filterByEmail: String? = null): List<User> {
        val selectQuery = if (filterByEmail == null) {
            UsersTable
                .selectAll()
        } else {
            UsersTable
                .select {
                    UsersTable.email.eq(filterByEmail)
                }
        }

        return transaction {
           selectQuery
                .map { row ->
                    User(
                        id = row[UsersTable.id].value.toString(),
                        email = row[UsersTable.email],
                        firstName = row[UsersTable.firstName],
                        lastName = row[UsersTable.lastName]
                    )
                }
        }
    }

    fun getUserById(userId: UUID): User {
        val row = transaction {
            UsersTable.select {
                UsersTable.id.eq(userId)
            }.first()
        }

        return User(
            id = row[UsersTable.id].value.toString(),
            email = row[UsersTable.email],
            firstName = row[UsersTable.firstName],
            lastName = row[UsersTable.lastName]
        )
    }

    fun getUserDigestByEmail(email: String): UserDigest {
        return transaction {
            UsersTable
                .select {
                    UsersTable.email.eq(email)
                }
                .map { row ->
                    UserDigest(
                        email = row[UsersTable.email],
                        passwordHash = row[UsersTable.password]
                    )
                }.first()
        }
    }

    fun insertUser(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): User {
        val row = transaction {
            UsersTable.insert {
                it[UsersTable.email] = email
                it[UsersTable.password] = password
                it[UsersTable.firstName] = firstName
                it[UsersTable.lastName] = lastName
            }
        }

        return User(
            id = row[UsersTable.id].value.toString(),
            email = row[UsersTable.email],
            firstName = row[UsersTable.firstName],
            lastName = row[UsersTable.lastName]
        )
    }

    fun updateUser(
        userId: UUID,
        email: String? = null,
        password: String? = null,
        firstName: String? = null,
        lastName: String? = null
    ): User {
        val row = transaction {
            UsersTable.update(
                where = {
                    UsersTable.id.eq(userId)
                },
                body = { statement ->
                    email?.let {
                        statement[UsersTable.email] = it
                    }

                    password?.let {
                        statement[UsersTable.password] = it
                    }

                    firstName?.let {
                        statement[UsersTable.firstName] = it
                    }

                    lastName?.let {
                        statement[UsersTable.lastName] = it
                    }
                }
            )
        }

        return getUserById(userId)
    }

    fun deleteUser(userId: UUID) =
        transaction {
            UsersTable.deleteWhere {
                UsersTable.id.eq(userId)
            }
        }
}

