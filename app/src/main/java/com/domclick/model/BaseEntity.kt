package com.domclick.model

import javax.persistence.*
import java.io.Serializable
import java.util.Objects

@MappedSuperclass
open class BaseEntity : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Version
    @Column(name = "version")
    var version: Long? = 0L

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val entity = other as BaseEntity?
        return id == entity!!.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}
