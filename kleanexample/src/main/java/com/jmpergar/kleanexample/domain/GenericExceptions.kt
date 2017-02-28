package com.jmpergar.kleanexample.domain

sealed class GenericExceptions {

    class NetworkError : GenericExceptions()
    class ServerError : GenericExceptions()
}