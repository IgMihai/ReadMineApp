package com.assist.redmineapp.Adapters

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.assist.redmineapp.Models.Issue
import com.assist.redmineapp.R
import kotlinx.android.synthetic.main.issues_card_row.view.*

class IssueAdapter(private var context: Context, private var issues: List<Issue>, val onClickListener: onIssueListAction) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = parent.inflate(R.layout.issues_card_row)
        }
        view.issues_card_row_issueID.setOnClickListener { onClickListener.onIssueClick(issues[position]) }
        view.issues_card_row_issueID.text = issues[position].id.toString()
        view.issues_card_row_issueSubject.text = issues[position].subject
        view.issues_card_row_issueStatus.text = issues[position].status!!.name
        view.issues_card_row_issueAssignedTo.text = issues[position].assigned_to!!.name
        return view
    }


    override fun getItem(p0: Int): Any {
        return issues[p0]
    }

    override fun getItemId(p0: Int): Long {
        return issues[p0].id.toLong()
    }

    override fun getCount(): Int {
        return issues.size
    }

    private fun ViewGroup.inflate(@LayoutRes layoutID: Int, attachedToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(layoutID, this, attachedToRoot)

    interface onIssueListAction {
        fun onIssueClick(issue: Issue)
    }

}