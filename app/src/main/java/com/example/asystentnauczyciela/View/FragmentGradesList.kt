package com.example.asystentnauczyciela.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.Adapters.GradesAdapter
import com.example.asystentnauczyciela.ViewModel.DeleteButtonClickListener
import com.example.asystentnauczyciela.ViewModel.GradeViewModel
import kotlinx.android.synthetic.main.fragment_grades_list.*
import kotlinx.android.synthetic.main.fragment_grades_list.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentGradesList : Fragment(), DeleteButtonClickListener {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel: GradeViewModel
    lateinit var myAdapter: GradesAdapter
    lateinit var myLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myLayoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(GradeViewModel::class.java)
        viewModel.studentsGrades = viewModel.gradeRepository.getStudentsGrades(ValuesHolder.chosenStudentId, ValuesHolder.chosenStudentsCourseId)
        myAdapter = GradesAdapter(viewModel.studentsGrades, this)

        viewModel.studentsGrades.observe(viewLifecycleOwner, Observer { myAdapter.notifyDataSetChanged() })
        return inflater.inflate(R.layout.fragment_grades_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentName.text = ValuesHolder.student
        courseName.text = ValuesHolder.chosenCourseName

        goToAddGradeBtn.setOnClickListener {
            view -> view.findNavController().navigate(R.id.action_fragmentStudentsCourseGrades_to_fragmentAddGrade)
        }

        recyclerView = studentsCourseGradesRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentGradesList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDelBtnClick(position: Int) {
        viewModel.studentsGrades.value?.get(ValuesHolder.chosenStudentsCourseId)?.let {
            viewModel.deleteGrade(it)
        }
    }
}