package com.assist.redmineapp.Adapters

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.assist.redmineapp.Models.Project
import com.assist.redmineapp.R
import kotlinx.android.synthetic.main.projects_card_row.view.*

class ProjectAdapter(private var context: Context, private var projects: List<Project>, val onClickListener:onProjectListAction) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = parent.inflate(R.layout.projects_card_row)
        }
        view.project_card_row_title.setOnClickListener{onClickListener.onProjectClick(projects[position])}
        view.project_card_row_title.text = projects[position].name
        return view
    }


    override fun getItem(p0: Int): Any {
        return projects[p0]
    }

    override fun getItemId(p0: Int): Long {
        return projects[p0].id.toLong()
    }

    override fun getCount(): Int {
        return projects.size
    }

    private fun ViewGroup.inflate(@LayoutRes layoutID: Int, attachedToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(layoutID, this, attachedToRoot)

    interface onProjectListAction {
        fun onProjectClick(project: Project)
    }

}