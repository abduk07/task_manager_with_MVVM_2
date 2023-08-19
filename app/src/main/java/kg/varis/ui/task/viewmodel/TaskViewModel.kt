package kg.varis.ui.task.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.varis.taskmanagerwithmvvm2.model.Task
import kg.varis.taskmanagerwithmvvm2.repository.Repository
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun updateData(taskModel: Task) {
        repository.update(taskModel)
    }

    fun insertData(taskModel: Task) {
        repository.insert(taskModel)
    }

}