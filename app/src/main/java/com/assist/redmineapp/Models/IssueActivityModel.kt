package com.assist.redmineapp.Models

data class IssueActivity(
        var issue: Issue = Issue()
)

data class Journal(
        var id: Int = 0,
        var user: BaseIdNameModel = BaseIdNameModel(),
        var notes: String = "",
        var created_on: String = "",
        var details: MutableList<Detail>? = mutableListOf()
)

data class Detail(
        var property: String = "",
        var name: String = "",
        var old_value: String = "",
        var new_value: String = ""
)