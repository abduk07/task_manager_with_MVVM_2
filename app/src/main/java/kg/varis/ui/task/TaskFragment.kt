package kg.varis.ui.task

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kg.varis.taskmanagerwithmvvm2.R
import kg.varis.taskmanagerwithmvvm2.core.BaseFragment
import kg.varis.taskmanagerwithmvvm2.databinding.FragmentTaskBinding
import kg.varis.taskmanagerwithmvvm2.model.Task
import kg.varis.ui.home.HomeFragment
import kg.varis.ui.task.viewmodel.TaskViewModel

@AndroidEntryPoint
class TaskFragment : BaseFragment<FragmentTaskBinding>(R.layout.fragment_task) {

    private val viewModel: TaskViewModel by lazy {
        ViewModelProvider(requireActivity())[TaskViewModel::class.java] }

    override fun inflateViewBinding(): FragmentTaskBinding {
        return FragmentTaskBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.btnSave.setOnClickListener {
            val result = Task(
                title = binding.etTitle.text.toString(),
            )
            viewModel.insertData(result)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
        }
    }
}