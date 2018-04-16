package com.assist.redmineapp.GeneralActivities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.assist.redmineapp.Adapters.IssueActivityAdapter
import com.assist.redmineapp.Models.IssueActivity
import com.assist.redmineapp.Models.Journal
import com.assist.redmineapp.R
import com.assist.redmineapp.data.RestClientAssist
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
        RestClientAssist.instance.api_assist.getIssueActivity(388)
                .subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : SingleObserver<MutableList<IssueActivity>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(issueActivity: MutableList<IssueActivity>) {
                        for (item in 0 until issueActivity.size) {
                            Log.d("Issues Activity Adapter", issueActivity[item].issue.toString())
                            populateList(issueActivity[item])
                        }

                    }

                    override fun onError(e: Throwable) {
                        Log.i("Issues Activity ", e.message)
                    }
                })
    }

    private fun populateList(issueActivity: IssueActivity) {
        adapter = IssueActivityAdapter(this@ActivityIssueActivity, issueActivity.issue.journals!!, object : IssueActivityAdapter.onIssueActivityListAction {
            override fun onIssueActivityClick(journal: Journal) {
                Toast.makeText(baseContext, journal.user.name, Toast.LENGTH_SHORT).show()
            }
        })
        issueActivity_ListView.adapter = adapter
    }
}
