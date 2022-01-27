package com.app.dogchangers.domain.utils


class Constants {

    companion object {

        //we can save these two in config.properties
        const val DOMAIN_URL: String = "https://api.thedogapi.com/v1/"
        const val X_API_KEY: String = "f0577d56-e697-4747-83e1-98776c9df4ad"

        const val NETWORK_TIMEOUT = 15L
        const val LIMIT = 80
        const val LIMIT_BREEDS = 200

    }
}