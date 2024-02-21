package br.com.mba.mycloudapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.mba.mycloudapp.databinding.ItemListBinding
import br.com.mba.mycloudapp.model.ClothingItem
import br.com.mba.mycloudapp.ui.activity.EditDataActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.text.NumberFormat
import java.util.Locale

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

        holder.btnEdit.setOnClickListener {
            val nextScreen = Intent(holder.itemView.context, EditDataActivity::class.java)
            nextScreen.putExtra("itemId", item.id)
            holder.itemView.context.startActivity(nextScreen)
        }

        var price = item.price
        val format = NumberFormat.getCurrencyInstance(Locale("pt","BR"))
        val formattedPrice = format.format(price)

        holder.itemPrice.text = formattedPrice

    }

    override fun getItemCount() = itemList.size

    inner class ItemListViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvItem: TextView = binding.itemName
        val colorItem: TextView = binding.itemColor
        val itemPrice: TextView = binding.itemPrice
        val btnDelete: Button = binding.btnDelete
        val btnEdit: Button = binding.btnEdit
    }
}