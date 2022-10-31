package com.example.get_set_go.adapter

import android.content.Context
import android.os.Build
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.get_set_go.R
import com.example.get_set_go.StylesFragment
import com.example.get_set_go.ThirdActivity
import com.example.get_set_go.databinding.ActivityThirdBinding
import com.example.get_set_go.databinding.RecyclerviewItemviewBinding
import com.example.get_set_go.model.Scroll
import com.google.android.material.imageview.ShapeableImageView

//import com.google.android.ads.mediationtestsuite.viewmodels.ItemViewHolder  ( mother fucking error bcoz of this)

class ItemAdapter(private val context: ThirdActivity, private val dataset: List<Scroll>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var binding: RecyclerviewItemviewBinding? = null

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val textView : TextView = view.findViewById(R.id.item_title)
        val imageView : ImageView = view.findViewById(R.id.item_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_itemview,parent,false)
        return ItemViewHolder(adapterLayout)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text= context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }
    override fun getItemCount(): Int {
        return dataset.size
    }
}

//class ItemAdapter (private  val newsList:ArrayList<Scroll>) : RecyclerView.Adapter<ItemAdapter.MyViewHolder>{
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_itemview,parent,false)
//        return MyViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentView = newsList[position]
//        holder.titleImage.setImageResource(currentView.titleImage)
//        holder.tvHeading.text = currentView.heading
//    }
//
//    override fun getItemCount(): Int {
//        return newsList.size
//    }
//
//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//
//        val titleImage : ShapeableImageView = itemView.findViewById(R.id.title_image)
//        val tvHeading : TextView = itemView.findViewById(R.id.tvheading)
//    }
//}