package dev.supasintatiyanupanwong.apps.android.bpi.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB; private set

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = (javaClass.findParameterizedType().actualTypeArguments[0] as Class<VB>)
            .run {
                try {
                    (getMethod("inflate", LayoutInflater::class.java)
                        .invoke(null, layoutInflater) as VB)
                        .also { setContentView(it.root) }
                } catch (ignored: NoSuchMethodException) {
                    // Handles the case of merge tag, also no need to manually call setContentView.
                    getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java)
                        .invoke(null, layoutInflater, findViewById(android.R.id.content)) as VB
                }
            }
    }


    private fun Class<*>.findParameterizedType(): ParameterizedType {
        when (val genericSuperclass = genericSuperclass) {
            is ParameterizedType -> return genericSuperclass
            is Class<*> -> return genericSuperclass.findParameterizedType()
        }

        throw ClassCastException("$name cannot be cast to ${ParameterizedType::javaClass.name}")
    }

}
