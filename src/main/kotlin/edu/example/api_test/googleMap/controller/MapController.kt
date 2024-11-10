package edu.example.api_test.googleMap.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MapController(
    @Value("\${google.api.key}") private val apiKey: String
) {

    @GetMapping("/map")
    fun showMapPage(model: Model): String {
        model.addAttribute("apiKey", apiKey)
        return "googleMap"
    }
}