package com.example.asystentnauczyciela.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.Adapters.CourseListAdapter
import com.example.asystentnauczyciela.ViewModel.CourseViewModel
import com.example.asystentnauczyciela.ViewModel.DeleteButtonClickListener
import kotlinx.android.synthetic.main.fragment_course_list.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentCourseList : Fragment(), DeleteButtonClickListener {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: CourseViewModel
    private lateinit var myAdapter: CourseListAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

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
        viewModel = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
        myAdapter = CourseListAdapter(viewModel.courses, this)

        viewModel.courses.observe(viewLifecycleOwner, androidx.lifecycle.Observer { myAdapter.notifyDataSetChanged() })

        return inflater.inflate(R.layout.fragment_course_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goToAddCourseBtn.setOnClickListener{
            view -> view.findNavController().navigate(R.id.action_fragmentCourseList_to_fragmentAddCourse)
        }

        recyclerView = courseListRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCourseList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDelBtnClick(position: Int) {
        viewModel.courses.value?.get(ValuesHolder.chosenStudentIndex)?.let {
            viewModel.deleteCourse(it)
        }
    }
}