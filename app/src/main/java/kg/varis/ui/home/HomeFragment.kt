package kg.varis.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kg.varis.taskmanagerwithmvvm2.R
import kg.varis.taskmanagerwithmvvm2.core.BaseFragment
import kg.varis.taskmanagerwithmvvm2.databinding.FragmentHomeBinding
import kg.varis.taskmanagerwithmvvm2.model.Task
import kg.varis.ui.home.adapter.HomeAdapter
import kg.varis.ui.home.viewmodel.HomeViewModel
import kg.varis.ui.task.TaskFragment


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private lateinit var adapter: HomeAdapter
    private lateinit var viewModel: HomeViewModel

    override fun inflateViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun initView() {
        adapter = HomeAdapter(this::deleteClick, this::onClick,this::onClickOnCheckBox)
        binding.recyclerView.adapter = adapter
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        setData()
        binding.btnAddTasks.setOnClickListener {
            replaceActivity()
        }
    }

    private fun onClickOnCheckBox(taskModel: Task) {
        viewModel.updateData(taskModel)
        viewModel.updateData(taskModel)
        setData()
    }

    private fun replaceActivity() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, TaskFragment()).commit()
    }

    private fun setData() {
        val list = viewModel.getData()
        for (task in list) {
            if (task.checkbox == true) {
                adapter.addTrueCheckBox(task) // Добавляем элементы с true checkBox в начало списка
            }
        }
        adapter.addData(list)
    }

    private fun onClick(taskModel: Task) {
        setData()
    }

    private fun deleteClick(taskModel: Task) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete?")
        builder.setMessage("Delete sure")
        builder.setPositiveButton("Delete") { d, _ ->
            viewModel.deleteData(taskModel)
            setData()
            d.dismiss()
        }
        builder.setNegativeButton("No") { d, _ ->
            d.dismiss()
        }
        builder.create().show()
    }
}