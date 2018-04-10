package com.assist.redmineapp.Adapters

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.assist.redmineapp.Models.Journal
import com.assist.redmineapp.R
import kotlinx.android.synthetic.main.issue_activity_card_row.view.*

class IssueActivityAdapter(private var context: Context, private var journal: List<Journal>, val onClickListener: onIssueActivityListAction) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = parent.inflate(R.layout.issue_activity_card_row)
        }
        view.activity_issue_card_row_userName.setOnClickListener { onClickListener.onIssueActivityClick(journal[position]) }
        view.activity_issue_card_row_userName.text = journal[position].user!!.name
        view.activity_issue_card_row_details.text = journal[position].details.toString() // return a list of details
        view.activity_issue_card_row_createdOn.text = journal[position].created_on
        return view
    }


    override fun getItem(p0: Int): Any {
        return journal[p0]
    }

    override fun getItemId(p0: Int): Long {
        return journal[p0].id.toLong()
    }

    override fun getCount(): Int {
        return journal.size
    }

    private fun ViewGroup.inflate(@LayoutRes layoutID: Int, attachedToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(layoutID, this, attachedToRoot)

    interface onIssueActivityListAction {
        fun onIssueActivityClick(journal: Journal)
    }

}