package com.assist.redmineapp.Models

data class IssueActivity(
        var issue: Issue? = null
)

data class Journal(
        var id: Int = 0,
        var user: User? = null,
        var notes: String = "",
        var created_on: String = "",
        var details: List<Detail>? = null
)

data class User(
        var id: Int = 0,
        var name: String = ""
)

data class Detail(
        var property: String = "",
        var name: String = "",
        var old_value: String = "",
        var new_value: String = ""
)