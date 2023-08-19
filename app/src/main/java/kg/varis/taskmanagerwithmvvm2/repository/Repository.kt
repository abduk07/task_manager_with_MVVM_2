package kg.varis.taskmanagerwithmvvm2.repository

import kg.varis.taskmanagerwithmvvm2.data.local.TaskDao
import kg.varis.taskmanagerwithmvvm2.model.Task
import javax.inject.Inject

class Repository @Inject constructor(private val dao: TaskDao) {

    fun getData(): List<Task> {
        return dao.getList()
    }

    fun insert(task: Task) {
        dao.addData(task)
    }

    fun delete(task: Task) {
        dao.deleteData(task)
    }

    fun update(task: Task) {
        dao.updateData(task)
    }
}