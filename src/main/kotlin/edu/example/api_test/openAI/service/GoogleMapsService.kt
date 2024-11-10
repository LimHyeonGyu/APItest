package edu.example.api_test.openAI.service

import com.google.maps.GeoApiContext
import com.google.maps.PlacesApi
import com.google.maps.model.PlaceType
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GoogleMapsService(
    @Value("\${google.api.key}")
    private val googleMapsApiKey: String
){
    private val context = GeoApiContext.Builder()
        .apiKey(googleMapsApiKey)
        .build()

    fun getNearbyAttractions(location: String): List<String> {
        val response = PlacesApi.textSearchQuery(context, location).type(PlaceType.TOURIST_ATTRACTION).await()
        return response.results.map { it.name }
    }

}