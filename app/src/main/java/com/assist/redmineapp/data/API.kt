package com.assist.redmineapp.data

import com.assist.redmineapp.Login.Models.UserCall
import com.assist.redmineapp.Models.IssueActivity
import com.assist.redmineapp.Models.IssueData
import com.assist.redmineapp.Models.ProjectsData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Created by assist on 30.03.2018.
 */
interface API {

    @GET("/users/current.json")//https://red.assist.ro/users/current.json
    fun loginUser(
            @Header("authorization") authkey: String
    ): Single<UserCall>


    // TODO call with pagination
    @GET("/projects.json?limit=40")
    fun getUserProjects(
            @Header("authorization") authkey: String
    ): Single<ProjectsData>

    // TODO call with pagination
    @GET("projects/{projectId}/issues.json?limit=40")
    fun getProjectIssues(
            @Header("authorization") authkey: String, @Path("projectId") projectID: Int
    ): Single<IssueData>


    @GET("/projects/activity/{projectId}?from=2018-04-10&page=1")
    fun getIssueActivity(
            @Path("projectId") projectID: Int
    ): Single<MutableList<IssueActivity>>
}

