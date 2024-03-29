package com.singidunum.orders.services

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.lang.IllegalStateException

sealed class UserServiceException(message: String, cause: Throwable, status: HttpStatus = HttpStatus.BAD_REQUEST) :
    ResponseStatusException(status, message, cause)

class RequestBodyValidationException(message: String) : UserServiceException(message, IllegalStateException())