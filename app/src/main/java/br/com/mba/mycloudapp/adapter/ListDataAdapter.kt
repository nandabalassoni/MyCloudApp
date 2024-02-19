package br.com.mba.mycloudapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.mba.mycloudapp.databinding.ItemListBinding
import br.com.mba.mycloudapp.model.ClothingItem

class ListDataAdapter(
    private var itemList: MutableList<ClothingItem>
) : RecyclerView.Adapter<ListDataAdapter.ItemListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemListViewHolder {
        val itemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val item = itemList[position]
        holder.tvItem.text = item.name
        holder.colorItem.text = item.color
        holder.itemPrice.text = item.price.toString()
        holder.btnDelete.setOnClickListener {
            itemList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = itemList.size

    inner class ItemListViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvItem: TextView = binding.itemName
        val colorItem: TextView = binding.itemColor
        val itemPrice: TextView = binding.itemPrice
        val btnDelete: ImageButton = binding.btnDelete
    }
}