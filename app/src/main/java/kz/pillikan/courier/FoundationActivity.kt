package kz.pillikan.courier

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_foundation.*
import kotlinx.android.synthetic.main.nav_header.view.*
import kz.pillikan.courier.common.utils.ZERO
import kz.pillikan.courier.common.views.BaseActivity
import org.jetbrains.anko.sdk27.coroutines.onClick

class FoundationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foundation)
        lets()
    }

    private fun lets() {
        initNavigationView()
    }

    private fun initNavigationView() {

    }

}
