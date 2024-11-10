package edu.example.api_test.openAI.service

import com.fasterxml.jackson.databind.JsonNode
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.OkHttpClient
import okhttp3.Request
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GptService(
    private val objectMapper: ObjectMapper,
    @Value("\${openAI.api.key}")
    private val openAiApiKey: String
) {
    private val client = OkHttpClient()

    fun generateTravelSchedule(attractions: List<String>): String {
        println(attractions)
        val prompt = "다음 장소들을 포함해 추천 여행 일정을 작성해줘: ${attractions.joinToString(", ")}"
        println(prompt)

        val requestBody = mapOf(
            "model" to "gpt-4o-mini",
            "messages" to listOf(mapOf("role" to "user", "content" to prompt)),
            "stream" to "True"
        ).let { objectMapper.writeValueAsString(it) }
            .toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .post(requestBody)
            .addHeader("Authorization", "Bearer $openAiApiKey")
            .build()

        client.newCall(request).execute().use { response ->
            val responseBody = response.body?.string()
            if (responseBody == null) {
                return "응답을 가져오는 중 오류가 발생했습니다. 다시 시도해주세요."
            }

            println("API 응답: $responseBody")  // 전체 응답 출력

            val jsonNode: JsonNode
            try {
                jsonNode = objectMapper.readTree(responseBody)
            } catch (e: Exception) {
                return "응답 파싱 중 오류가 발생했습니다. 다시 시도해주세요."
            }

            // choices 노드 확인 및 선택 메시지 추출
            val choicesNode = jsonNode["choices"]
            if (choicesNode != null && choicesNode.isArray && choicesNode.size() > 0) {
                val messageNode = choicesNode[0]["message"]
                if (messageNode != null && messageNode.has("content")) {
                    return messageNode["content"].asText()
                } else {
                    println("message 노드가 존재하지 않거나 content 필드가 없음")
                }
            } else {
                println("choices 배열이 존재하지 않거나 비어있음")
            }

            return "여행 일정을 생성하는 중 오류가 발생했습니다. 다시 시도해주세요."
        }
    }
}