package edu.example.api_test.openWeatherMap.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "openweathermap")
data class OpenWeatherMapProperties (
    var baseUrl: String = "",
    var apiKey: String = "",
    var lang: String = ""
)