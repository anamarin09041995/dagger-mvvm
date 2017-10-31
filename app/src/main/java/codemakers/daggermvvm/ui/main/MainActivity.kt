package codemakers.daggermvvm.ui.main

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import codemakers.daggermvvm.R
import codemakers.daggermvvm.data.model.Tarea
import codemakers.daggermvvm.databinding.ActivityMainBinding
import codemakers.daggermvvm.ui.adapter.TareaAdapter
import codemakers.daggermvvm.ui.add.AddActivity
import codemakers.daggermvvm.ui.update.UpdateActivity
import codemakers.daggermvvm.util.LifeDisposable
import codemakers.daggermvvm.util.applySchedulers
import codemakers.daggermvvm.util.snackBarAction
import com.jakewharton.rxbinding2.view.clicks
import dagger.android.AndroidInjection
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.contentView
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var adapter: TareaAdapter

    @Inject
    lateinit var mainViewModel: MainViewModel

    lateinit var binding: ActivityMainBinding
    val dis: LifeDisposable = LifeDisposable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter
        supportActionBar?.setTitle("Tareas")

    }

    override fun onResume() {
        super.onResume()
       dis add adapter.clearSubject
               .flatMap { tarea ->  mainViewModel.eliminar(tarea).map { tarea } }
               .flatMap { snackBarAction(contentView!!, R.string.snackBar, R.string.undo, it ) }
               .flatMap { mainViewModel.deshacer(it as Tarea) }
               .subscribe()

        dis add add.clicks()
                .subscribe { startActivity<AddActivity>() }

        dis add adapter.updateSubject
                .applySchedulers()
                .subscribeBy (onNext = {
                    goToUpdate(it)
                })

        dis add mainViewModel.getAllTodo()
                .subscribe { adapter.data = it }
    }

    fun goToUpdate(tarea: Tarea){
        startActivity<UpdateActivity>("params" to tarea)
    }

}
