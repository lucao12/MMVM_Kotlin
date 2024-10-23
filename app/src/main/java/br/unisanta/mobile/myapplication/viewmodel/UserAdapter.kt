package br.unisanta.mobile.myapplication.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.unisanta.mobile.myapplication.R
import coil.load

class UserAdapter (private val users:List<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txvNome: TextView = itemView.findViewById(R.id.txv_name)
        val txvEmail: TextView = itemView.findViewById(R.id.txv_email)
        val txvImg: ImageView = itemView.findViewById(R.id.txv_Img)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.txvNome.text = user.nome
        holder.txvEmail.text = user.email
        holder.txvImg.load(user.profilePic)
    }
    override fun getItemCount(): Int {
        return users.size
    }
}