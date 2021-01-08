package com.example.asystentnauczyciela.ViewModel.Adapters

import android.util.Log
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
import com.example.asystentnauczyciela.View.FragmentGradeView
import com.example.asystentnauczyciela.ViewModel.DeleteButtonClickListener

class GradesAdapter(
    var gradesList: LiveData<MutableList<Grade>>,
    val deleteButtonClickListener: DeleteButtonClickListener
): RecyclerView.Adapter<GradesAdapter.GradeHolder>() {

    inner class GradeHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.one_row_grades_list, parent, false
        )

        return GradeHolder(view)
    }

    override fun onBindViewHolder(holder: GradeHolder, position: Int) {
        var gradeIndex = holder.itemView.findViewById<TextView>(R.id.studentsCourseGradeIndex)
        var gradeName = holder.itemView.findViewById<TextView>(R.id.studentsCourseGradeName)
        var gradeValue = holder.itemView.findViewById<TextView>(R.id.studentsCourseGradeValue)

        var editBtn = holder.itemView.findViewById<Button>(R.id.goToEditGradeBtn)
        var delButton = holder.itemView.findViewById<Button>(R.id.deleteGradeBtn)



        gradeIndex.text = gradesList.value?.get(position)?.id.toString()
        gradeName.text = gradesList.value?.get(position)?.gradeName
        gradeValue.text = gradesList.value?.get(position)?.gradeValue

        editBtn.setOnClickListener {
            view -> view.findNavController().navigate(R.id.action_fragmentStudentsCourseGrades_to_fragmentGradeView)
            ValuesHolder.chosenGradeIndex = gradesList.value?.get(position)?.id ?: 0
            Log.d("index", "${ValuesHolder.chosenGradeIndex}")
        }

        delButton.setOnClickListener{
            ValuesHolder.chosenGradeIndex = gradesList.value?.get(position)?.id ?: 0
            deleteButtonClickListener.onDelBtnClick(position)
        }
    }

    override fun getItemCount(): Int {
        return gradesList.value?.size?:0
    }
}