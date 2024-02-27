package com.example.restcommentssimpleexample.models

import java.io.Serializable

data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
) : Serializable { // Serializable is needed for passing objects between activities
    override fun toString(): String {
        return "$email: $name"
    }
}
