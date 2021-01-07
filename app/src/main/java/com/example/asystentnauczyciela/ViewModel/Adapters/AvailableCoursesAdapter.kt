package com.example.asystentnauczyciela.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.Model.Entities.Course
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.AddButtonClickListener
import com.example.asystentnauczyciela.ViewModel.DeleteButtonClickListener


class AvailableCoursesAdapter (var availableCoursesList: LiveData<MutableList<Course>>,
                               val addButtonClickListener: AddButtonClickListener
                            ): RecyclerView.Adapter<AvailableCoursesAdapter.AvailableCoursesHolder>() {
    inner class AvailableCoursesHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableCoursesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_row_available_courses,parent,false)
        return AvailableCoursesHolder(view)
    }

    override fun onBindViewHolder(holder: AvailableCoursesHolder, position: Int) {
        var availableCourseIndex = holder.itemView.findViewById<TextView>(R.id.availableCourseIndex)
        var availableCourseName = holder.itemView.findViewById<TextView>(R.id.availableCourseName)
        var addButton = holder.itemView.findViewById<Button>(R.id.addAvailableCourseBtn)

        availableCourseIndex.text = availableCoursesList.value?.get(position)?.id.toString()
        availableCourseName.text = availableCoursesList.value?.get(position)?.name

        addButton.setOnClickListener{
            //ValuesHolder.chosenParticipantId = availableCoursesList.value?.get(position)?.id ?: 0
            ValuesHolder.chosenStudentsCourseId = availableCoursesList.value?.get(position)?.id ?: 0

            addButtonClickListener.onAddBtnClick(position)
        }
    }

    override fun getItemCount(): Int {
        return availableCoursesList.value?.size?:0
    }
}