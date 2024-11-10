package edu.example.api_test.openWeatherMap.controller

import edu.example.api_test.openWeatherMap.service.WeatherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class WeatherController(
    private val weatherService: WeatherService
) {

    @GetMapping("/weather")
    fun getWeather(@RequestParam city: String): Map<String, Any> {
        return weatherService.getCurrentWeather(city)
    }
}