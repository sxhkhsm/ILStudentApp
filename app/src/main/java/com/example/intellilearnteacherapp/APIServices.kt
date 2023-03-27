package com.example.intellilearnteacherapp

import com.example.intellilearnteacherapp.models.ClassModel
import com.example.intellilearnteacherapp.models.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface APIServices {

    @POST("addMcq")
    fun addMCQ(@Body mcq:McqItem) : Call<McqItem>

    @GET("addMcq")
    fun getAllMcqItems() : Call<List<McqItem>>

    @GET("teacherClasses")
    fun getTeacherClasses(@Query("teacher_ID") id : Int) : Call<List<ClassModel>>


    @FormUrlEncoded
    @POST("loginTeacher")
    fun loginTeacher(

        @Field("email") email:String,
        @Field("password") password:String,

    ) : Call<LoginResponse>


    @DELETE("addMcq")
    fun deleteMcqItem(@Query("question_ID") id : Int) : Call<DeleteResponse>

}

data class DeleteResponse ( var response : String)