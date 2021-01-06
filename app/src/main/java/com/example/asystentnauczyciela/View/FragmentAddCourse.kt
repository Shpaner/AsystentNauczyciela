package com.example.asystentnauczyciela.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.CourseViewModel
import kotlinx.android.synthetic.main.fragment_add_course.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentAddCourse : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var viewModel: CourseViewModel

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
        viewModel = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addNewCourseBtn.setOnClickListener {
            var name: String = newCourseName.getText().toString()

            if(!"".equals(name)) {
                viewModel.addCourse(name)
                newCourseName.text.clear()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentAddCourse().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}