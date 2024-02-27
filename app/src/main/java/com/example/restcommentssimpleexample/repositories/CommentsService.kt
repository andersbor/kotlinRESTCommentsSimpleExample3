package com.example.restcommentssimpleexample.repositories

import com.example.restcommentssimpleexample.models.Comment
import retrofit2.Call
import retrofit2.http.GET

interface CommentsService {
    @GET("comments")
    fun getComments(): Call<List<Comment>>
}