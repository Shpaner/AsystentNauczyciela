package com.example.asystentnauczyciela.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.Model.Entities.Grade
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R

class ReportListAdapter (var gradesList: LiveData<MutableList<Grade>>
): RecyclerView.Adapter<ReportListAdapter.ReportGradeHolder>() {

    inner class ReportGradeHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportGradeHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.one_row_report_list, parent, false
        )

        return ReportGradeHolder(view)
    }

    override fun onBindViewHolder(holder: ReportGradeHolder, position: Int) {
        var gradeIndex = holder.itemView.findViewById<TextView>(R.id.indexr)
        var gradeName = holder.itemView.findViewById<TextView>(R.id.gradeNamer)
        var gradeValue = holder.itemView.findViewById<TextView>(R.id.gradeValuer)
        var editBtn = holder.itemView.findViewById<Button>(R.id.goToEditGradeBtnr)

        gradeIndex.text = gradesList.value?.get(position)?.id.toString()
        gradeName.text = gradesList.value?.get(position)?.gradeName
        gradeValue.text = gradesList.value?.get(position)?.gradeValue

        editBtn.setOnClickListener {
                view -> view.findNavController().navigate(R.id.action_fragmentReport_to_fragmentGradeView)
            ValuesHolder.chosenGradeIndex = gradesList.value?.get(position)?.id ?: 0
        }
    }

    override fun getItemCount(): Int {
        return gradesList.value?.size ?: 0
    }
}