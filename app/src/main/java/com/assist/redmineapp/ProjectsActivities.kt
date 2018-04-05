package com.assist.redmineapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.assist.redmineapp.Adapters.ProjectAdapter
import com.assist.redmineapp.Models.Project
import com.assist.redmineapp.Models.ProjectsData
import com.assist.redmineapp.Utils.getAuthToken
import com.assist.redmineapp.data.RestClient
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_projects_activities.*

class ProjectsActivities : AppCompatActivity() {


    lateinit var adapter: ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects_activities)
        getUserProjects()

    }

    private fun getUserProjects() {
        Log.d("API from shared", Utils.readSharedPreferencesApiKey(baseContext))
        RestClient.instance.api.getUserProjects(getAuthToken(Utils.readSharedPreferencesApiKey(this), ""))
                .subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : SingleObserver<ProjectsData> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(projectsData: ProjectsData) {
                        Log.d("Projects Adapter", projectsData.projects!!.size.toString())
                        populateList(projectsData)
                    }

                    override fun onError(e: Throwable) {
                        Log.i("Main", e.message)
                    }
                })
    }

    private fun populateList(projectsData: ProjectsData) {
        adapter = ProjectAdapter(this@ProjectsActivities, projectsData.projects!!, object : ProjectAdapter.onProjectListAction {
            override fun onProjectClick(project: Project) {
                Toast.makeText(baseContext, project.name, Toast.LENGTH_SHORT).show()
            }
        })
        project_recyclerView.adapter = adapter
    }


    fun logout(v: View) {
        Utils.deleteSharePreferences(this@ProjectsActivities)
        finish()
    }
}
