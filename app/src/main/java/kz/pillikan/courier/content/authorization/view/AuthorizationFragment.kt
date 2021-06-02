package kz.pillikan.courier.content.authorization.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_authorization.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.pillikan.courier.R
import kz.pillikan.courier.common.helpers.Validators
import kz.pillikan.courier.common.views.BaseFragment
import kz.pillikan.courier.content.authorization.model.request.SignInRequest
import kz.pillikan.courier.content.authorization.viewmodel.AuthorizationViewModel
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert


class AuthorizationFragment : BaseFragment(R.layout.fragment_earnings) {
    private var root: View? = null
    private lateinit var viewModel: AuthorizationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_authorization, container, false)
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }
    private fun lets() {
        initViewModel()
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        btn_sign_in.onClick { prepareLogin()}
    }
    private fun prepareLogin() {
        val tvLogin = et_login.text.toString()
        val tvPassword = et_password.text.toString()

        val signInRequest = SignInRequest(userName = tvLogin, password = tvPassword)

        when (tvLogin.isNotEmpty() && Validators.validatePassword(tvPassword)) {
            true -> logIn(signInRequest)
            false -> Toast.makeText(
                requireContext(),
                getString(R.string.the_data_you_entered_is_incorrect),
                Toast.LENGTH_LONG
            ).show()
        }
    }
    private fun logIn(auth: SignInRequest){
        setLoading(true)
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.logIn(auth)
        }
    }
    private fun initObservers() {
        viewModel.isError.observe(viewLifecycleOwner, {
            setLoading(false)
            errorDialog(getString(R.string.error_unknown_body))
        })
        viewModel.isSuccess.observe(viewLifecycleOwner,  {
            when (it) {
                true -> {
                    setLoading(false)
                    view?.let { it1 ->
                        Navigation.findNavController(it1)
                            .navigate(R.id.action_authorizationFragment_to_ordersFragment)
                    }

                }
                false -> unSuccessFullDialog()
            }
        })
    }
    private fun unSuccessFullDialog() {
        alert {
            title = getString(R.string.error_auth_wrong_data_title)
            message = getString(R.string.error_auth_wrong_data_msg)
            isCancelable = false
            positiveButton(getString(R.string.dialog_retry)) { dialog ->
                setLoading(false)
                dialog.dismiss()
            }
            negativeButton(getString(R.string.dialog_exit)) {
                requireActivity().finish()
            }
        }.show()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AuthorizationViewModel::class.java)
    }
    private fun setLoading(loading: Boolean) {
        loadingView.visibility = if (loading) View.VISIBLE else View.GONE
        btn_sign_in.isEnabled = !loading
        et_login.isEnabled = !loading
        et_password.isEnabled = !loading
    }

}