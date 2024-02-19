package br.com.mba.mycloudapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.mba.mycloudapp.databinding.ItemListBinding
import br.com.mba.mycloudapp.model.ClothingItem
import com.google.firebase.firestore.FirebaseFirestore

class ListDataAdapter(
    private var itemList: MutableList<ClothingItem>
) : RecyclerView.Adapter<ListDataAdapter.ItemListViewHolder>() {

    private val db = FirebaseFirestore.getInstance()

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
            val itemToRemove = itemList[position]
            itemList.removeAt(position)
            notifyItemRemoved(position)

            // Aqui é feita a exclusão do item no Firestore. nesse caso apenas de novos itens cadastrados, pois é gerado um id novo para cada um.
            db.collection("clothing_items").document(itemToRemove.id).delete()
                .addOnSuccessListener {
                    Toast.makeText(holder.itemView.context, "Item excluído com sucesso!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(holder.itemView.context, "Erro ao excluir item", Toast.LENGTH_SHORT).show()
                }
        }
    }

    override fun getItemCount() = itemList.size

    inner class ItemListViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvItem: TextView = binding.itemName
        val colorItem: TextView = binding.itemColor
        val itemPrice: TextView = binding.itemPrice
        val btnDelete: Button = binding.btnDelete
    }
}