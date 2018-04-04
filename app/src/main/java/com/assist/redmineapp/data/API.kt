package com.assist.redmineapp.data

import com.assist.redmineapp.Models.ProjectGeneralData
import com.assist.redmineapp.Models.UserGeneralData
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by assist on 30.03.2018.
 */
interface API {

    @GET("/users/current.json")//https://red.assist.ro/users/current.json
    fun loginUser(
            @Header("authorization") authkey: String
    ): Single<UserGeneralData>

    @GET("projects.json")
    fun getUsersProjects(
            @Header("authorization") authkey: String
    ): Single<ProjectGeneralData>
}