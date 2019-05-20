package br.ufpe.cin.if710.rss

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.itemlista.view.*

class RSSAdapter (private val rss: List<ItemRSS>, private val c: Context) : RecyclerView.Adapter<RSSAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.itemlista, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rss.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.titulo?.text = rss.get(position).toString()
    }


    class ViewHolder (item : View) : RecyclerView.ViewHolder(item){
        val titulo = item.item_titulo
    }
}