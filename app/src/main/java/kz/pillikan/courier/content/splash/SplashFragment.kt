package kz.pillikan.courier.content.splash


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.pillikan.courier.R
import kz.pillikan.courier.common.utils.DELAY_THREE_SECOND
import kz.pillikan.courier.common.views.BaseFragment

class SplashFragment : BaseFragment(R.layout.fragment_earnings) {

    private var root: View? = null

    private lateinit var viewModel: SplashViewModel

    private var isFirstLaunch = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_splash, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }


    private fun lets() {
        initViewModel()
        initNetwork()
        initObservers()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    private fun initNetwork() {
        CoroutineScope(Dispatchers.IO).launch { checkNetwork() }
    }

    private fun initObservers() {
        viewModel.isNetworkConnection.observe(viewLifecycleOwner, {
            if (it) {
                checkAuthorize()
            } else {
                errorDialog(getString(R.string.error_unknown_body))
            }
        })

        viewModel.isAuthorize.observe(viewLifecycleOwner, { it ->
            if (it) {
                view?.let { it1 ->
                    Navigation.findNavController(it1)
                        .navigate(R.id.action_splashFragment_to_ordersFragment)
                } }else {
                view?.let {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_splashFragment_to_authorizationFragment)
                }
            }
        })
    }

    private suspend fun checkNetwork() {
        if (isFirstLaunch) {
            delay(DELAY_THREE_SECOND)
            isFirstLaunch = false
        }
        viewModel.checkNetwork(requireContext())
    }

    private fun checkAuthorize() {
        viewModel.checkAuthorize()
    }

}