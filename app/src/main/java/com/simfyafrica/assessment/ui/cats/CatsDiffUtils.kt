package com.simfyafrica.assessment.ui.cats

import androidx.recyclerview.widget.DiffUtil
import com.simfyafrica.assessment.data.model.Cat

class CatsDiffUtils(
    private val oldCats: List<Cat>,
    private val newCats: List<Cat>
) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCats[oldItemPosition].description == newCats[newItemPosition].description
    }

    override fun getOldListSize() = oldCats.size

    override fun getNewListSize() = newCats.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCats[oldItemPosition].id == newCats[newItemPosition].id
    }
}