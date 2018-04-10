package com.assist.redmineapp.GeneralActivities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.assist.redmineapp.Adapters.IssueAdapter
import com.assist.redmineapp.Models.Issue
import com.assist.redmineapp.Models.IssueData
import com.assist.redmineapp.R
import com.assist.redmineapp.Utils
import com.assist.redmineapp.data.RestClient
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_issues.*

const val IssueID = "issueID"

class IssuesActivity : AppCompatActivity() {

    lateinit var adapter: IssueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues)
        getProjectIssues()
    }

    private fun getProjectIssues() {
        val projectID = intent.getStringExtra(TEXT)
        RestClient.instance.api.getProjectIssues(Utils.getAuthToken(Utils.readSharedPreferencesApiKey(this), ""), projectID.toInt())
                .subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : SingleObserver<IssueData> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(issueData: IssueData) {
                        Log.d("Issues Adapter", issueData.issues!!.size.toString())
                        populateList(issueData)

                    }

                    override fun onError(e: Throwable) {
                        Log.i("Main", e.message)
                    }
                })
    }

    private fun populateList(issueData: IssueData) {
        adapter = IssueAdapter(this@IssuesActivity, issueData.issues!!, object : IssueAdapter.onIssueListAction {
            override fun onIssueClick(issue: Issue) {
                val intent = Intent(this@IssuesActivity, ActivityIssueActivity::class.java).apply {
                    putExtra(IssueID, issue.id.toString())
                }
                startActivity(intent)
            }
        })
        issues_activity_ListView.adapter = adapter
    }
}
