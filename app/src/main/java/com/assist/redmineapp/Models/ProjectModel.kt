package com.assist.redmineapp.Models

/**
 * Created by beatrice on 02.04.2018.
 */
data class ProjectModel(
        var projects: ProjectsData? = null
)

data class ProjectsData(

        var projects: List<Project>? = null,
        var total_count: Int = 0,
        var offset: Int = 0,
        var limit: Int = 0
)

data class Project(
        var id: Int = 0,
        var name: String = "",
        var identifier: String = "",
        var description: String = "",
        var status: String = "",
        var custom_fields: List<CustomField>? = null,
        var created_on: String = "",
        var updated_on: String = "",
        var parent: Parent? = null

)

data class CustomField(
        var id: Int = 0,
        var name: String = "",
        var value: String = ""
)

data class Parent(
        var id: Int = 0,
        var name: String = ""
)