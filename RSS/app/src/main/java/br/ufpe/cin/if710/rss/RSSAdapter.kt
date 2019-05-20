package br.ufpe.cin.if710.rss

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.itemlista.view.*
import kotlinx.android.synthetic.main.itemlista.view.item_data
import kotlinx.android.synthetic.main.itemlista.view.item_titulo
import kotlinx.android.synthetic.main.rss_item.view.*

class RSSAdapter (private val rss: List<ItemRSS>, private val c: Context) : RecyclerView.Adapter<RSSAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.rss_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rss.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rssItem = rss[position]
        holder?.titulo?.text = rssItem.title
        holder?.date?.text = rssItem.pubDate
        holder?.date?.text = rssItem.link
    }

    class ViewHolder (item : View) : RecyclerView.ViewHolder(item){
        val titulo = item.item_titulo
        val date = item.item_data
        val link = item.item_link
    }
}