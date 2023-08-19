package kg.varis.taskmanagerwithmvvm2.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kg.varis.taskmanagerwithmvvm2.data.local.AppDataBase
import kg.varis.taskmanagerwithmvvm2.data.local.TaskDao

@Module
@InstallIn(SingletonComponent::class)
class AppModel {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "task_list")
            .allowMainThreadQueries().build()
    }

    @Provides
    fun dao(@ApplicationContext context: Context): TaskDao {
        return provideDatabase(context).dao()
    }
}