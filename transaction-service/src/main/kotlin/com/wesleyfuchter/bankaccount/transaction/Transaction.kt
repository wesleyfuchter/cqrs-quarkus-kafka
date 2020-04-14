package com.wesleyfuchter.bankaccount.transaction

import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import org.hibernate.annotations.GenericGenerator
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "transactions")
class Transaction(

        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "id", updatable = false, nullable = false)
        var id: String = "",

        @Column(name = "account_id")
        var accountId: String,

        @Column
        var description: String,

        @Column @Enumerated(EnumType.STRING)
        var type: TransactionType,

        @Column
        var value: Double

): PanacheEntityBase()

enum class TransactionType {
    INCOME, EXPENSE
}