package kz.pillikan.courier.content.orders.view

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_orders.*
import kotlinx.android.synthetic.main.nav_header.view.*
import kz.pillikan.courier.R
import kz.pillikan.courier.common.utils.SERIALIZED_ORDER
import kz.pillikan.courier.common.utils.ZERO
import kz.pillikan.courier.common.views.BaseFragment
import kz.pillikan.courier.content.orders.model.response.order.OrdersResponse
import kz.pillikan.courier.content.orders.view.adapter.CurrentOrdersAdapter
import kz.pillikan.courier.content.orders.view.adapter.NewOrdersAdapter
import kz.pillikan.courier.content.orders.viewmodel.OrdersViewModel
import org.jetbrains.anko.sdk27.coroutines.onClick

class OrdersFragment : BaseFragment(R.layout.fragment_orders) {

    private lateinit var viewModel: OrdersViewModel
    private val bundle = Bundle()
    private val currentOrdersAdapter: CurrentOrdersAdapter =
        CurrentOrdersAdapter(this)
    private val newOrdersAdapter: NewOrdersAdapter =
        NewOrdersAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lets()
    }

    private fun lets() {
        initRecyclerView()
        initViewModel()
        updateFeeds()
        initListeners()
        initObservers()
    }

    private fun initRecyclerView() {
        rv_current_orders.apply {
            adapter = currentOrdersAdapter
            layoutManager = LinearLayoutManager(context)
        }
        rv_new_orders.apply {
            adapter = currentOrdersAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun updateFeeds() {
        viewModel.getCurrentOrdersList()
        viewModel.getNewOrdersList()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[OrdersViewModel::class.java]
    }

    private fun initListeners() {

        ll_on_the_line.onClick {
            val popup = PopupMenu(context, ll_on_the_line)
            popup.inflate(R.menu.menu)

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_add_contact -> {
                    }
                    R.id.action_settings -> {
                    }
                }
                false
            }
            popup.show()
        }

        iv_burger.onClick {
            drawer_layout.open()
        }

        val header = nav_view.getHeaderView(ZERO)
        header.cl_header.onClick {
            navigateTo(R.id.profileFragment)
        }
    }

    fun onClickCurrentItem(currentOrdersResponse: OrdersResponse) {
        bundle.putSerializable(SERIALIZED_ORDER, currentOrdersResponse)
        view?.let { it1 ->
            Navigation.findNavController(it1)
                .navigate(R.id.action_ordersFragment_to_arrivedFragment, bundle)
        }
    }

    fun onClickNewItem(newOrdersResponse: OrdersResponse) {
        bundle.putSerializable(SERIALIZED_ORDER, newOrdersResponse)
        view?.let { it1 ->
            Navigation.findNavController(it1)
                .navigate(R.id.action_ordersFragment_to_arrivedFragment, bundle)
        }
    }

    private fun initObservers() {
        viewModel.currentOrdersList.observe(viewLifecycleOwner, {
            setCurrentOrderAdapter(it)
        })
        viewModel.newOrdersList.observe(viewLifecycleOwner, {
            setNewOrderAdapter(it)
        })
    }

    private fun setCurrentOrderAdapter(it: ArrayList<OrdersResponse>?) {
        currentOrdersAdapter.addList(it!!)
    }

    private fun setNewOrderAdapter(it: ArrayList<OrdersResponse>) {
        newOrdersAdapter.addList(it)
    }

}