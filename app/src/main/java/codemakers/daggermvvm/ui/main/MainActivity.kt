package codemakers.daggermvvm.ui.main

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import codemakers.daggermvvm.R
import codemakers.daggermvvm.databinding.ActivityMainBinding
import codemakers.daggermvvm.ui.adapter.TareaAdapter
import codemakers.daggermvvm.ui.add.AddActivity
import codemakers.daggermvvm.util.LifeDisposable
import com.jakewharton.rxbinding2.view.clicks
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
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


        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        supportActionBar?.setTitle("Tareas")

    }

    override fun onResume() {
        super.onResume()
       dis add adapter.clearSubject
                .flatMap { Observable.fromCallable { mainViewModel.dao::delete } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { mainViewModel.dao::delete }

        dis add add.clicks()
                .subscribe { startActivity<AddActivity>() }

        dis add mainViewModel.getAllTodo()
                .subscribe { adapter.data = it }
    }

}
