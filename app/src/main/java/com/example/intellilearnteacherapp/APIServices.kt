package com.example.intellilearnteacherapp

import retrofit2.Call
import retrofit2.http.*

interface APIServices {

    @POST("addMcq")
    fun addMCQ(@Body mcq:McqItem) : Call<McqItem>

    @GET("addMcq")
    fun getAllMcqItems() : Call<List<McqItem>>

    @DELETE("addMcq")
    fun deleteMcqItem(@Query("question_ID") id : Int) : Call<DeleteResponse>

}

data class DeleteResponse ( var response : String)