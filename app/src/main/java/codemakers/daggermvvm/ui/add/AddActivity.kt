package codemakers.daggermvvm.ui.add

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import codemakers.daggermvvm.R
import codemakers.daggermvvm.data.model.Tarea
import codemakers.daggermvvm.databinding.ActivityAdd2Binding
import codemakers.daggermvvm.util.LifeDisposable
import codemakers.daggermvvm.util.validateForm
import codemakers.daggermvvm.util.text
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.text
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_add2.*
import java.util.*
import javax.inject.Inject

class AddActivity : AppCompatActivity() {

    lateinit var binding: ActivityAdd2Binding
    val dis: LifeDisposable = LifeDisposable(this)

    @Inject
    lateinit var addViewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add2)
        AndroidInjection.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add2)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Agregar")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onResume() {
        super.onResume()

        dis add add.clicks()
                .flatMap { validateForm(R.string.verificarCampos, nombre.text() , descripcion.text())}
                .flatMap {
                    addViewModel.addTarea(it[0], it[1])
                }.subscribeBy (onNext = { finish() })
    }
}
