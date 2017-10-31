package codemakers.daggermvvm.ui.update

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import codemakers.daggermvvm.R
import codemakers.daggermvvm.data.model.Tarea
import codemakers.daggermvvm.databinding.ActivityUpdateBinding
import codemakers.daggermvvm.util.LifeDisposable
import codemakers.daggermvvm.util.text
import codemakers.daggermvvm.util.validateForm
import com.jakewharton.rxbinding2.view.clicks
import dagger.android.AndroidInjection
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_add2.*
import kotlinx.android.synthetic.main.activity_update.*
import javax.inject.Inject

class UpdateActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateBinding
    val dis: LifeDisposable = LifeDisposable(this)
    lateinit var tarea : Tarea

    @Inject
    lateinit var updateViewModel: UpdateViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)
        AndroidInjection.inject(this)
        tarea = intent.getParcelableExtra("params")
        descripcion_update.setText(tarea.description)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(tarea.name)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


    override fun onResume() {
        super.onResume()

        dis add save.clicks()
                .flatMap { validateForm(R.string.verificarCampos, descripcion_update.text())}
                .flatMap { tarea.description = it[0]
                            updateViewModel.update(tarea)}
                .subscribeBy (onNext = {
                    finish()
                })
    }
}
