package edu.example.api_test.openAI.controller

import edu.example.api_test.openAI.service.GoogleMapsService
import edu.example.api_test.openAI.service.GptService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/travel")
class TravelController(
    private val googleMapsService: GoogleMapsService,
    private val gptService: GptService
) {

    @GetMapping("/schedule")
    fun getTravelSchedule(@RequestParam location: String): String {
        val attractions = googleMapsService.getNearbyAttractions(location)
        return gptService.generateTravelSchedule(attractions)
    }
}