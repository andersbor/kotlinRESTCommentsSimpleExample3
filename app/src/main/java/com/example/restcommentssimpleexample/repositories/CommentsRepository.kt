package com.example.restcommentssimpleexample.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.restcommentssimpleexample.models.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommentsRepository {
    private val baseUrl = "https://jsonplaceholder.typicode.com/"
    private val commentsService: CommentsService
    val commentsLiveData: MutableLiveData<List<Comment>?> = MutableLiveData<List<Comment>?>()
    val errorMessageLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        commentsService = retrofit.create(CommentsService::class.java)
        getComments()
    }

    fun getComments() {
        val call: Call<List<Comment>> = commentsService.getComments()

        call.enqueue(object : Callback<List<Comment>?> {
            override fun onResponse(
                call: Call<List<Comment>?>,
                response: Response<List<Comment>?>
            ) {
                if (response.isSuccessful) {
                    commentsLiveData.value = response.body()
                    errorMessageLiveData.postValue("")
                } else {
                    val message = response.code().toString() + " " + response.message()
                    //errorMessageLiveData.postValue(message)
                    Log.d("APPLE", message)
                }
            }

            override fun onFailure(call: Call<List<Comment>?>, t: Throwable) {
                commentsLiveData.postValue(null)
                errorMessageLiveData.postValue(t.message)
                Log.d("APPLE", t.message!!)
            }
        })
    }
}