package kz.pillikan.courier.content.orders.view

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_detail_page.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.pillikan.courier.R
import kz.pillikan.courier.common.utils.*
import kz.pillikan.courier.common.views.BaseFragment
import kz.pillikan.courier.common.views.viewBinding
import kz.pillikan.courier.content.orders.model.response.arrived.ArrivedResponse
import kz.pillikan.courier.content.orders.view.adapter.ArrivedAdapter
import kz.pillikan.courier.content.orders.viewmodel.ArrivedViewModel
import kz.pillikan.courier.databinding.FragmentDetailPageBinding
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk27.coroutines.onClick

class DetailPageFragment : BaseFragment(R.layout.fragment_detail_page) {

    private val binding by viewBinding(FragmentDetailPageBinding::bind)

    private lateinit var viewModel: ArrivedViewModel

    private var orderStatus = ARRIVED_IN_RESTAURANT

    private val arrivedAdapter: ArrivedAdapter =
        ArrivedAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            navigateTo(R.id.ordersFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    private fun lets() {
        initRecyclerView()
        initViewModel()
        getArrivedList()
        initListeners()
        initObservers()
    }

    private fun initRecyclerView() {
        binding.rvStructure.apply {
            adapter = arrivedAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getArrivedList() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getArrivedList()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ArrivedViewModel::class.java]
    }

    private fun initListeners() {
        binding.llOnBackPressed.onClick {
            navigateTo(R.id.ordersFragment)
        }
        binding.llClientNo.onClick {
            navigateTo(R.id.addPhotoFragment)
        }
        binding.llArrivedRestaurant.onClick {
            setStatusOrder()
        }
    }

    private fun setStatusOrder() {
        when (orderStatus) {
            ARRIVED_IN_RESTAURANT -> {
                binding.tvArrivedRestaurant.text = getString(R.string.take_order)
                orderStatus = TAKE_THE_ORDER
            }
            TAKE_THE_ORDER -> showAlertTakeOrder()
            ARRIVED_TO_THE_CLIENT -> {
                binding.tvArrivedRestaurant.text = getString(R.string.given_to_the_client)
                binding.llClientNo.visibility = View.VISIBLE
                orderStatus = GIVEN_TO_THE_CLIENT
            }
            GIVEN_TO_THE_CLIENT -> {
                showAlertGivenOrder()
            }
        }
    }

    private fun initObservers() {
        viewModel.arrivedList.observe(viewLifecycleOwner, {
            when (it != null) {
                true -> setArrivedListAdapter(it)
                false -> {

                }
            }
        })
    }

    private fun setArrivedListAdapter(it: ArrayList<ArrivedResponse>) {
        arrivedAdapter.addList(it)
    }

    private fun setLoading(loading: Boolean) {
        loadingView.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun showAlertTakeOrder() {
        activity?.alert {
            title = getString(R.string.are_you_sure)
            message = ""
            isCancelable = false
            neutralPressed(getString(R.string.no)) {
                setLoading(false)
                it.dismiss()
            }
            positiveButton(getString(R.string.yes)) {
                setLoading(false)
                orderStatus = ARRIVED_TO_THE_CLIENT
                binding.llCurrentOrderList.visibility = View.GONE
                binding.llClient.visibility = View.VISIBLE
                binding.llClientBellow.visibility = View.GONE
                binding.tvArrivedRestaurant.text = getString(R.string.arrived_to_the_client)
                it.dismiss()
            }
        }?.show()
    }

    private fun showAlertGivenOrder() {
        activity?.alert {
            title = getString(R.string.are_you_sure)
            message = ""
            isCancelable = false
            neutralPressed(getString(R.string.no)) {
                setLoading(false)
                it.dismiss()
            }
            positiveButton(getString(R.string.yes)) {
                setLoading(false)
                binding.llArrivedRestaurant.visibility = View.GONE
                binding.llClientNo.visibility = View.GONE
                it.dismiss()
            }
        }?.show()
    }

}