package kg.varis.taskmanagerwithmvvm2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.varis.taskmanagerwithmvvm2.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDataBase :RoomDatabase(){

    abstract fun dao():TaskDao
}