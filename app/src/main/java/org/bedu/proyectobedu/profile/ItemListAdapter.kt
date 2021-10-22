package org.bedu.proyectobedu.profile

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import org.bedu.proyectobedu.R
import org.bedu.proyectobedu.profile.model.ProfileItem

class ItemListAdapter(val context: Activity, val array: ArrayList<ProfileItem>): ArrayAdapter<ProfileItem>(context, R.layout.profile_item, array) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val itemView: View = inflater.inflate(R.layout.profile_item, null)

        initView(itemView, position)

        return itemView
    }


    private fun initView(view: View, position: Int){
        val title : TextView = view.findViewById(R.id.profileTitle)
        title.text = array[position].title
        title.setCompoundDrawablesRelativeWithIntrinsicBounds(array[position].img, 0, 0, 0)
        view.id = array[position].id


    }

}