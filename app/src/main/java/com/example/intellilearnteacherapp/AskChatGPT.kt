package com.example.intellilearnteacherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ask_chat_gpt.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

class AskChatGPT : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_chat_gpt)

        callChatGPTAPI("Please provide a summary of the book 'To Kill a Mockingbird'")

    }

    private fun callChatGPTAPI(prompt: String) {
        val apiKey = "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" //my api key
        val url = "https://api.openai.com/v1/engines/davinci-codex/completions"

        val client = OkHttpClient()

        val requestBody = JSONObject()
        requestBody.put("prompt", prompt)
        requestBody.put("max_tokens", 50)

        val body = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), requestBody.toString())

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle the error
                Toast.makeText(this@AskChatGPT, "Error getting response..", Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val jsonResponse = JSONObject(response.body?.string() ?: "")
                    val generatedText = jsonResponse.getJSONObject("choices").getJSONArray("text").getString(0)
                    runOnUiThread {
                        // Update UI with the generated text

                        tvChatGPTResponse.text = generatedText

                    }
                } else {
                    // Handle the error
                    Toast.makeText(this@AskChatGPT, "Response unsuccessful..", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}