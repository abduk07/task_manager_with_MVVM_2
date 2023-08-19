package kg.varis.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.varis.taskmanagerwithmvvm2.model.Task
import kg.varis.taskmanagerwithmvvm2.repository.Repository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository):ViewModel() {

    fun getData():List<Task>{
        return repository.getData()
    }

    fun deleteData(taskModel: Task){
        repository.delete(taskModel)
    }

    fun insertData(taskModel: Task) {
        repository.insert(taskModel)
    }

    fun updateData(taskModel: Task) {
        repository.update(taskModel)
    }


}