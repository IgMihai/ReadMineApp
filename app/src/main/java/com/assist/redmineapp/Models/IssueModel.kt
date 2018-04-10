package com.assist.redmineapp.Models

data class IssueData(

        var issues: List<Issue>? = null,
        var total_count: Int = 0,
        var offset: Int = 0,
        var limit: Int = 0
)

data class Issue(
        var id: Int = 0,
        var project: ProjectData? = null,
        var tracker: Tracker? = null,
        var status: Status? = null,
        var priority: Priority? = null,
        var author: Author? = null,
        var assigned_to: AssignedTo? = null,
        var fixed_version: FixedVersion? = null,
        var subject: String = "",
        var description: String = "",
        var start_date: String = "",
        var due_date: String = "",
        var done_ratio: Int = 0,
        var estimated_hours: Int = 0,
        var created_on: String = "",
        var updated_on: String = "",
        var journals: List<Journal>? = null
)

data class ProjectData(
        var id: Int = 0,
        var name: String = ""
)

data class Tracker(
        var id: Int = 0,
        var name: String = ""
)

data class Status(
        var id: Int = 0,
        var name: String = ""
)

data class Priority(
        var id: Int = 0,
        var name: String = ""
)

data class Author(
        var id: Int = 0,
        var name: String = ""
)

data class AssignedTo(
        var id: Int = 0,
        var name: String = ""
)

data class FixedVersion(
        var id: Int = 0,
        var name: String = ""
)
