package org.example.challenge.faas.builder

interface Builder<T> {
    fun build(): T
}