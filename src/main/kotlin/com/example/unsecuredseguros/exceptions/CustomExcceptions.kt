package com.example.unsecuredseguros.exceptions


class BadRequestException(message: String) : RuntimeException("$DESCRIPTION. $message"){
    companion object {
        const val DESCRIPTION = "Bad reques exception (400)"
    }
}


class NotFoundException(message: String) : RuntimeException("$DESCRIPTION. $message"){
    companion object {
        const val DESCRIPTION = "Not Found Exception (404"
    }
}


class UnauthorizedException(message: String) : RuntimeException("$DESCRIPTION. $message"){
    companion object {
        const val DESCRIPTION = "Unauthorized Exception (401)"
    }
}


class ConflictException(message: String) : RuntimeException("$DESCRIPTION. $message"){
    companion object {
        const val DESCRIPTION = "Conflict Exception (409)"
    }
}

