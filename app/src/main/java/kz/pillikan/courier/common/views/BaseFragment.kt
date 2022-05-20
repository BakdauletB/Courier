package kz.pillikan.courier.common.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kz.pillikan.courier.R
import org.jetbrains.anko.alert

open class BaseFragment(private val resource: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(resource, container, false)
    }

    fun navigateTo(navDirections: Int) {
        requireActivity().findNavController(R.id.container)
            .navigate(navDirections)
    }

    fun errorDialog(errorMsg: String) {
        activity?.alert {
            title = getString(R.string.error_unknown_title)
            message = errorMsg
            isCancelable = false
            positiveButton(getString(R.string.dialog_ok)) { dialog ->
                dialog.dismiss()
            }
        }?.show()
    }

    fun arraysInDialog(navDirections: Int){
       requireActivity().findNavController(R.id.container)
           .navigate(navDirections)
    }

    fun errorDialog(errorMsg: Int){
        activity?.alert {
            title = getString(R.string.retail_name)
            message = errorMsg
            isCancelable = true
            positivr("")
        }
    }
}