package com.example.asystentnauczyciela.View

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.Model.ValuesHolder
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.ViewModel.Adapters.GradesAdapter
import com.example.asystentnauczyciela.ViewModel.Adapters.ReportListAdapter
import com.example.asystentnauczyciela.ViewModel.DeleteButtonClickListener
import com.example.asystentnauczyciela.ViewModel.GradeViewModel
import kotlinx.android.synthetic.main.fragment_report.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentReport : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel: GradeViewModel
    lateinit var myLayoutManager: LinearLayoutManager
    lateinit var myAdapter: ReportListAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewModel = ViewModelProvider(requireActivity()).get(GradeViewModel::class.java)
        val format = DateTimeFormatter.ofPattern("yyy/MM/dd")
        val date = LocalDateTime.now().format(format)
        ValuesHolder.date = date

        viewModel.reportGrades = viewModel.gradeRepository.getReport(date)
        myLayoutManager = LinearLayoutManager(context)
        myAdapter = ReportListAdapter(viewModel.reportGrades)

        viewModel.reportGrades.observe(viewLifecycleOwner, Observer { myAdapter.notifyDataSetChanged() })

        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todaysDate.text = ValuesHolder.date

        recyclerView = reportGradesRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentReport().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}