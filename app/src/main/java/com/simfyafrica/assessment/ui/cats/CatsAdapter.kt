package com.simfyafrica.assessment.ui.cats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.simfyafrica.assessment.data.model.Cat
import com.simfyafrica.assessment.BR.catItem

import com.simfyafrica.assessment.databinding.CatsRowItemBinding
import com.simfyafrica.assessment.ui.base.DataBindingViewHolder

class CatsAdapter(private val items: MutableList<Cat>,
                               private val onclick: (accountNumber: String, position: Int) -> Unit)
    : RecyclerView.Adapter<CatsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CatsRowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    fun add(list: MutableList<Cat>) {
        val catItems: MutableList<Cat> = arrayListOf()
        catItems.addAll(items)
        catItems.addAll(list)
        val result: DiffUtil.DiffResult = DiffUtil
            .calculateDiff(CatsDiffUtils(items, catItems))
        items.addAll(catItems)
        result.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(dataBinding: ViewDataBinding) : DataBindingViewHolder<Cat>(dataBinding) {

        override fun onBind(t: Cat): Unit = with(t) {
            dataBinding.setVariable(catItem, t)
            setOnClick(t.id, onclick, position)
        }

        fun setOnClick(idHandle: String, onclick: (idHandle: String, position: Int) -> Unit, position: Int) {
            itemView.setOnClickListener { onclick(idHandle, position) }
        }
    }
}

