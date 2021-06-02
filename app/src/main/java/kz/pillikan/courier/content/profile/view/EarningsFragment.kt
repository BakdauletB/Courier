package kz.pillikan.courier.content.profile.view

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_earnings.*
import kz.pillikan.courier.R
import kz.pillikan.courier.common.views.BaseFragment
import kz.pillikan.courier.content.profile.model.HistoryOfOrdersResponse
import kz.pillikan.courier.content.profile.view.adapter.HistoryOfOrdersAdapter
import kz.pillikan.courier.content.profile.viewmodel.EarningsViewFragment

class EarningsFragment : BaseFragment(R.layout.fragment_earnings) {

    private lateinit var viewModel: EarningsViewFragment

    private val historyOfOrdersAdapter: HistoryOfOrdersAdapter =
        HistoryOfOrdersAdapter(this)

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
        initViewModel()
        initRecyclerView()
        updateFeeds()
        initObservers()
        initListeners()
    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            view?.let { it1 ->
                Navigation.findNavController(it1).navigate(R.id.profileFragment)
            }
        }
    }

    private fun initListeners() {

    }

    private fun initRecyclerView() {
        rv_history_of_orders.apply {
            adapter = historyOfOrdersAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun updateFeeds() {
        viewModel.getHistoryOfOrders()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[EarningsViewFragment::class.java]
    }

    private fun initObservers() {
        viewModel.historyOfOrdersResponse.observe(viewLifecycleOwner, {
            setHistoryOfOrdersList(it)
        })
    }

    private fun setHistoryOfOrdersList(it: List<HistoryOfOrdersResponse>?) {
        historyOfOrdersAdapter.addList(it!!)
    }
}