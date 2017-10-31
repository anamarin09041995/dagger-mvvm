package codemakers.daggermvvm.ui.adapter

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import codemakers.daggermvvm.R
import codemakers.daggermvvm.data.model.Tarea
import codemakers.daggermvvm.databinding.TemplateTareasBinding
import codemakers.daggermvvm.util.inflate
import io.reactivex.subjects.PublishSubject
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ana Marin on 13/10/2017.
 */
class TareaAdapter: RecyclerView.Adapter<TareaAdapter.TodoHolder>() {

    var data:List<Tarea> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    val clearSubject: PublishSubject<Tarea> = PublishSubject.create()
    val updateSubject: PublishSubject<Tarea> = PublishSubject.create()

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.binding.tarea = data[position]
        holder.binding.clear = clearSubject
        holder.binding.update = updateSubject
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder
            = TodoHolder(parent.inflate(R.layout.template_tareas))

    override fun getItemCount(): Int = data.size

    class TodoHolder(view: View): RecyclerView.ViewHolder(view){
        val binding: TemplateTareasBinding = DataBindingUtil.bind(view)
    }

    companion object {

        private val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())

        @JvmStatic
        @BindingAdapter("app:dateFormat")
        fun applyFormat(textView: TextView, date: Date){
            textView.text = format.format(date)
        }
    }

}