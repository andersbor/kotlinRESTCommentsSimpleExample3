package com.example.restcommentssimpleexample.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.restcommentssimpleexample.repositories.CommentsRepository

class CommentsViewModel: ViewModel() {
    private val repository: CommentsRepository = CommentsRepository()
    val commentsLiveData: LiveData<List<Comment>?> = repository.commentsLiveData
    val errorMessage: LiveData<String> = repository.errorMessageLiveData

    fun get(position: Int): Comment? {
        return commentsLiveData.value?.get(position)
    }
}