package kg.varis.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.varis.taskmanagerwithmvvm2.databinding.ItemTaskBinding
import kg.varis.taskmanagerwithmvvm2.model.Task

class HomeAdapter(
    val deleteClick: (Task) -> Unit,
    val onClick: (Task) -> Unit,
    val onClickOnCheckBox: (Task) -> Unit
) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var list = mutableListOf<Task>()

    fun addData(lists: List<Task>) {
        list.clear()
        list.addAll(lists)
        list.sortByDescending { it.checkbox }
        notifyDataSetChanged()
    }

    fun addTrueCheckBox(taskModel: Task) {
        if (taskModel.checkbox == true) {
            list.add(0, taskModel) // Добавляем в начало списка
            notifyItemInserted(0)
        }
    }

    fun deleteData(lists: Task) {
        list.remove(lists)
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class HomeViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(taskModel: Task) {
            binding.tvTask.text = taskModel.title
            binding.checkbox.setOnCheckedChangeListener(null) // Удалите существующий обработчик, чтобы избежать рекурсивного вызова

            binding.checkbox.isChecked = taskModel.checkbox ?: false

            binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                val updatedTaskModel = taskModel.copy(checkbox = isChecked)
                onClickOnCheckBox(updatedTaskModel)
            }

            itemView.setOnLongClickListener {
                deleteClick(taskModel)
                false
            }
            itemView.setOnClickListener {
                onClick(taskModel)
            }
        }

    }
}