package edu.example.api_test.openWeatherMap.service

import edu.example.api_test.openWeatherMap.properties.OpenWeatherMapProperties
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class WeatherService (
    private val webClient: WebClient.Builder,
    private val properties: OpenWeatherMapProperties
){
    fun getCurrentWeather(city: String): Map<String, Any> {
        val url =
            "${properties.baseUrl}/weather?q=$city&appid=${properties.apiKey}&lang=${properties.lang}&units=metric"

        return webClient.build()
            .get()
            .uri(url)
            .retrieve()
            .bodyToMono(Map::class.java)
            .block() as Map<String, Any>
    }
}