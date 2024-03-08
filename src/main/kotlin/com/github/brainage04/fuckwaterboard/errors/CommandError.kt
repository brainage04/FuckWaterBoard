package com.github.brainage04.fuckwaterboard.errors

class CommandError(message: String, cause: Throwable) : Error(message, cause)