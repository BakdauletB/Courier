package kz.pillikan.courier.content.profile.view

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_profile.*
import kz.pillikan.courier.R
import kz.pillikan.courier.common.views.BaseFragment
import org.jetbrains.anko.sdk27.coroutines.onClick

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    private fun lets() {
        initToolbar()
        initListeners()
    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            view?.let {
                Navigation.findNavController(it).navigate(R.id.ordersFragment)
            }
        }
    }

    private fun initListeners() {
        ll_earn.onClick {
            view?.let { it1 ->
                Navigation.findNavController(it1).navigate(R.id.earningsFragment)
            }
        }
    }

}