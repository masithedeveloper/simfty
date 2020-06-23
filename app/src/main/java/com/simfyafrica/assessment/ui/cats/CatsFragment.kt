package com.simfyafrica.assessment.ui.cats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simfyafrica.assessment.R
import com.simfyafrica.assessment.data.model.Cat
import com.simfyafrica.assessment.databinding.CatsFragmentBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.cats_fragment.*
import java.text.FieldPosition
import javax.inject.Inject

class CatsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CatsViewModel by lazy {
        viewModelFactory.create(CatsViewModel::class.java)
    }

    private lateinit var navController: NavController

    private lateinit var catsAdapter: CatsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = CatsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        rv_home_cats.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        context?.let {
            catsAdapter = CatsAdapter(arrayListOf()) { id: String, position: Int ->
                navigateToCatDetails(id, position)
            }
        }
        rv_home_cats.adapter = catsAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(viewModel) {
            catsData.observe(viewLifecycleOwner, Observer {
                refreshItems(it)
            })

            error.observe(viewLifecycleOwner, Observer {
                ll_cats_progress.visibility = View.GONE
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun refreshItems(it: MutableList<Cat>?) {
        ll_cats_progress.visibility = View.GONE
        it?.run {
            catsAdapter.add(it)
        }
    }

    fun navigateToCatDetails(id: String, position: Int) {
        val bundle = bundleOf("id" to id, "position" to position)
        navController.navigate(R.id.catsFragmentTocatDetailsFragment, bundle)
    }

    companion object {
        @JvmStatic
        val TAG = CatsFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = CatsFragment().apply {
            arguments = Bundle()
        }
    }
}
