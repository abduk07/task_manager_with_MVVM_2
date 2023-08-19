package kg.varis.taskmanagerwithmvvm2.data.local

import androidx.room.*
import kg.varis.taskmanagerwithmvvm2.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_list ORDER BY id DESC")
    fun getList(): List<Task>

    @Insert
    fun addData(task: Task)

    @Delete
    fun deleteData(task: Task)

    @Update
    fun updateData(task: Task)
}