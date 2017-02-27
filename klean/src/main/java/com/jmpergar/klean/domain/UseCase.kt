package com.lorcapp.klean.domain

import org.funktionale.either.Disjunction

interface UseCase<in I, out R, out E> {

    fun execute(input: I): Disjunction<E, R>
}