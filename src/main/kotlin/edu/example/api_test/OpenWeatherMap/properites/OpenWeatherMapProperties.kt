package edu.example.api_test.OpenWeatherMap.properites

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "openweathermap")
data class OpenWeatherMapProperties (
    var baseUrl: String = "",
    var apiKey: String = "",
    var lang: String = ""
)