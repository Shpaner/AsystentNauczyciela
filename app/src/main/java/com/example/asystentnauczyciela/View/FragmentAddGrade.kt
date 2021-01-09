package com.example.asystentnauczyciela.View

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.GradeViewModel
import kotlinx.android.synthetic.main.fragment_add_grade.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentAddGrade : Fragment(), AdapterView.OnItemSelectedListener {

    private var param1: String? = null
    private var param2: String? = null

    private var marks: List<String> = listOf("2", "2+", "3-", "3", "3+", "4-", "4", "4+", "-5", "5")
    private var selectedMark: String = ""

    private lateinit var viewModel: GradeViewModel
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity()).get(GradeViewModel::class.java)

        adapter = context?.let {
            ArrayAdapter(it, android.R.layout.simple_spinner_item, marks)
        }!!
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        return inflater.inflate(R.layout.fragment_add_grade, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentsName.text = ValuesHolder.student
        coursesName.text = ValuesHolder.chosenCourseName

        gradeSpinner.adapter = adapter
        gradeSpinner.onItemSelectedListener = this

        addNewGradeButton.setOnClickListener {
            var name: String = newGradeName.text.toString()
            var description: String = newGradeDescription.text.toString()
            val format = DateTimeFormatter.ofPattern("yyy/MM/dd")
            val date = LocalDateTime.now().format(format)


            if("" != selectedMark) {
                Log.d("","dodano ocenę")
                viewModel.addGrade(ValuesHolder.chosenStudentId, description, ValuesHolder.chosenStudentsCourseId, selectedMark, date, name, ValuesHolder.student, ValuesHolder.chosenCourseName)
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FragmentAddGrade().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        selectedMark = parent.getItemAtPosition(pos).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        selectedMark = ""
    }
}