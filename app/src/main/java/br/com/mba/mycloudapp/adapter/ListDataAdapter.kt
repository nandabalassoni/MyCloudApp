package br.com.mba.mycloudapp.adapter

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageButton
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import br.com.mba.mycloudapp.R
//
//class ListDataAdapter(private val data: MutableList<String>, private val onDelete: (String) -> Unit) :
//    RecyclerView.Adapter<ListDataAdapter.MyViewHolder>() {
//
//    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val tvItem: TextView = view.findViewById(R.id.tv_item)
//        val btnDelete: ImageButton = view.findViewById(R.id.btn_delete)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
//        return MyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val item = data[position]
//        holder.tvItem.text = item
//        holder.btnDelete.setOnClickListener {
//            onDelete(item)
//            data.removeAt(position)
//            notifyItemRemoved(position)
//        }
//    }
//
//    override fun getItemCount() = data.size
//}

//class ListDataAdapter(private val data: MutableList<>, private val listener: OnItemClickListener):
//    RecyclerView.Adapter<ListDataAdapter.DadoViewHolder>() {
//        interface OnItemClickListener{
//            fun onItemClick(position: Int)
//        }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeuAdapter.DadoViewHolder {
//        TODO("Not yet implemented")
//    }
//
//    override fun onBindViewHolder(holder: MeuAdapter.DadoViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }
//}

//class MeuAdapter(private val dados: MutableList<Dado>, private val listener: OnItemClickListener) :
//    RecyclerView.Adapter<MeuAdapter.DadoViewHolder>() {
//
//    interface OnItemClickListener {
//        fun onItemClick(position: Int)
//    }
//
//    inner class DadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
//        val textoTextView: TextView = itemView.findViewById(R.id.textoTextView)
//
//        init {
//            itemView.setOnClickListener(this)
//        }
//
//        override fun onClick(view: View?) {
//            val position = adapterPosition
//            if (position != RecyclerView.NO_POSITION) {
//                listener.onItemClick(position)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DadoViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_lista_dados, parent, false)
//        return DadoViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: DadoViewHolder, position: Int) {
//        val currentItem = dados[position]
//        holder.textoTextView.text = currentItem.texto
//    }
//
//    override fun getItemCount() = dados.size
//
//    fun removeItem(position: Int) {
//        dados.removeAt(position)
//        notifyItemRemoved(position)
//    }
//}