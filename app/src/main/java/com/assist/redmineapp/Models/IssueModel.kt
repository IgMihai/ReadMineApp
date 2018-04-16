package com.assist.redmineapp.Models

data class IssueData(

        var issues: List<Issue>? = mutableListOf(),
        var total_count: Int = 0,
        var offset: Int = 0,
        var limit: Int = 0
)

data class Issue(
        var id: Int = 0,
        var project: BaseIdNameModel = BaseIdNameModel(),
        var tracker: BaseIdNameModel = BaseIdNameModel(),
        var status: BaseIdNameModel = BaseIdNameModel(),
        var priority: BaseIdNameModel = BaseIdNameModel(),
        var custom_fields: List<CustomFields> = mutableListOf(),
        var author: BaseIdNameModel = BaseIdNameModel(),
        var fixed_version: BaseIdNameModel = BaseIdNameModel(),
        var category: BaseIdNameModel = BaseIdNameModel(),
        var assigned_to: BaseIdNameModel = BaseIdNameModel(),
        var subject: String = "",
        var description: String = "",
        var start_date: String = "",
        var due_date: String = "",
        var done_ratio: Int = 0,
        var parent: ParentIssues = ParentIssues(),
        var estimated_hours: Int = 0,
        var created_on: String = "",
        var closed_on: String = "",
        var updated_on: String = "",
        var attachments: List<Attachments>? = mutableListOf(),
        var journals: List<Journal>? = mutableListOf()

)

data class BaseIdNameModel(
        var id: Int = 0,
        var name: String = ""
)

data class ParentIssues(
        var id: Int = 0
)

data class CustomFields(
        var id: Int = 0,
        var name: String = "",
        var value: String = ""
)

data class Attachments(
        var id: Int = 0,
        var filesize: Int = 0,
        var filename: String = "",
        var content_type: String = "",
        var description: String = "",
        var content_url: String = "",
        var thumbnail_url: String = "",
        var author: BaseIdNameModel = BaseIdNameModel(),
        var created_on: String = "",
        var updated_on: String = ""
)