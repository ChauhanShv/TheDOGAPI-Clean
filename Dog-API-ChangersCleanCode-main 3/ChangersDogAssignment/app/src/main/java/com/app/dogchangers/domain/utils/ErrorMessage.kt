package com.app.dogchangers.domain.utils

class ErrorMessage{

    companion object{

        const val UNABLE_TO_RESOLVE_HOST = "Unable to resolve host"
        const val GENERIC_ERROR = "Error"
        const val INVALID_PAGE = "Invalid page."
        const val ERROR_CHECK_NETWORK_CONNECTION = "Couldn't reach server. Check your internet connection."
        const val ERROR_UNKNOWN = "Unknown error"
        const val ERROR_SOMETHING_WENT_WRONG = "Something went wrong."
        const val NETWORK_ERROR = "Network error"
        const val NETWORK_ERROR_TIMEOUT = "Network timeout"
        const val CACHE_ERROR_TIMEOUT = "Cache timeout"
        const val UNKNOWN_ERROR = "Unknown error"

        fun isPaginationDone(errorResponse: String?): Boolean{
            return errorResponse?.contains(INVALID_PAGE)?: false
        }
    }

}













