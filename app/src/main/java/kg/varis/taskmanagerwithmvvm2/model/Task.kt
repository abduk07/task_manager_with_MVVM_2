package kg.varis.taskmanagerwithmvvm2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_list")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id :Int? = null,
    val title: String? ,
    val checkbox: Boolean?= false
) : java.io.Serializable
