package com.assist.redmineapp.GeneralActivities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.assist.redmineapp.Adapters.IssueActivityAdapter
import com.assist.redmineapp.Models.IssueActivity
import com.assist.redmineapp.Models.Journal
import com.assist.redmineapp.R
import com.assist.redmineapp.Utils
import com.assist.redmineapp.data.RestClient
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_issue_activity.*

class ActivityIssueActivity : AppCompatActivity() {
    lateinit var adapter: IssueActivityAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue_activity)
        getIssueActivity()
    }

    private fun getIssueActivity() {
        val issueID = intent.getStringExtra(IssueID)
        RestClient.instance.api.getIssueActivity(Utils.getAuthToken(Utils.readSharedPreferencesApiKey(this), ""), issueID.toInt())
                .subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : SingleObserver<IssueActivity> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(issueActivity: IssueActivity) {
                        Log.d("Issues Activity Adapter", issueActivity.issue!!.journals!!.size.toString())
                        populateList(issueActivity)

                    }

                    override fun onError(e: Throwable) {
                        Log.i("Main", e.message)
                    }
                })
    }

    private fun populateList(issueActivity: IssueActivity) {
        adapter = IssueActivityAdapter(this@ActivityIssueActivity, issueActivity.issue!!.journals!!, object : IssueActivityAdapter.onIssueActivityListAction {
            override fun onIssueActivityClick(journal: Journal) {
                Toast.makeText(baseContext, journal.user!!.name, Toast.LENGTH_SHORT).show()
            }
        })
        issueActivity_ListView.adapter = adapter
    }
}
