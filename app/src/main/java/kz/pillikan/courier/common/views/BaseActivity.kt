package kz.pillikan.courier.common.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kz.pillikan.courier.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun navigateTo(navDirections: Int) {
        findNavController(R.id.container)
            .navigate(navDirections)
    }

}