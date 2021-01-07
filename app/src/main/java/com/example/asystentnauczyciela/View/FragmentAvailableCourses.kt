package com.example.asystentnauczyciela.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.Adapters.AvailableCoursesAdapter
import com.example.asystentnauczyciela.ViewModel.Adapters.StudentsCoursesAdapter
import com.example.asystentnauczyciela.ViewModel.AddButtonClickListener
import com.example.asystentnauczyciela.ViewModel.StudentsCoursesViewModel
import kotlinx.android.synthetic.main.fragment_available_courses.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentAvailableCourses : Fragment(), AddButtonClickListener {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel: StudentsCoursesViewModel
    lateinit var myAdapter: AvailableCoursesAdapter
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
        viewModel = ViewModelProvider(requireActivity()).get(StudentsCoursesViewModel::class.java)
        viewModel.notStudentsCourses = viewModel.courseRepository.getNotStudentsCourses(ValuesHolder.chosenStudentId)
        myAdapter = AvailableCoursesAdapter(viewModel.notStudentsCourses, this)

        viewModel.notStudentsCourses.observe(viewLifecycleOwner, Observer { myAdapter.notifyDataSetChanged() })

        return inflater.inflate(R.layout.fragment_available_courses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentName.text = ValuesHolder.student

        recyclerView = availableCourseRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentAvailableCourses().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAddBtnClick(position: Int) {
//        viewModel.notStudentsCourses.value?.get(ValuesHolder.chosenStudentsCourseId)?.let {
//            viewModel.addStudentsCourse(it)
//        }

        //TODO
        viewModel.addStudentsCourse(ValuesHolder.chosenStudentId, ValuesHolder.chosenStudentsCourseId)

        Log.i("student", "${ValuesHolder.chosenStudentId}")
        Log.i("kurs", "${ValuesHolder.chosenStudentsCourseId}")

    }
}
