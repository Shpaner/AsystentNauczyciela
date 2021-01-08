package com.example.asystentnauczyciela.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_selected_student.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentSelectedStudent : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: StudentViewModel

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

        viewModel = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)

        // TODO dodac adapter i layout manager

        return inflater.inflate(R.layout.fragment_selected_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentNameTextView.text = viewModel.students.value?.get(ValuesHolder.chosenStudentIndex)?.name + " " + viewModel.students.value?.get(ValuesHolder.chosenStudentIndex)?.lastName
        ValuesHolder.student = studentNameTextView.text as String

        Log.d("student", "${ValuesHolder.student}")

        goToStudentsCoursesBtn.setOnClickListener {
                view -> view.findNavController().navigate(R.id.action_fragmentSelectedStudent_to_fragmentStudentsCourseList)
        }

        goToRemarks.setOnClickListener {
                view -> view.findNavController().navigate(R.id.action_fragmentSelectedStudent_to_fragmentRemarks)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FragmentSelectedStudent().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}