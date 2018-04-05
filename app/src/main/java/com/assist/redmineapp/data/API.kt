package com.assist.redmineapp.data

import com.assist.redmineapp.Models.ProjectModel
import com.assist.redmineapp.Models.ProjectsData
import com.assist.redmineapp.Models.UserCall
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Created by assist on 30.03.2018.
 */
interface API {

    @GET("/users/current.json")//https://red.assist.ro/users/current.json
    fun loginUser(
            @Header("authorization") authkey: String
    ): Single<UserCall>


    // TODO call with pagination
    @GET("projects.json?limit=40")
    fun getUserProjects(
            @Header("authorization") authkey: String
    ): Single<ProjectsData>
}