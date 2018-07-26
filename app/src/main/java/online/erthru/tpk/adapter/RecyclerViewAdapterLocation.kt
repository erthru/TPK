package online.erthru.tpk.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_location.view.*
import online.erthru.tpk.R
import online.erthru.tpk.network.model.Results
import online.erthru.tpk.ui.ClinicActivity
import online.erthru.tpk.ui.LocationDetailActivity
import online.erthru.tpk.util.Const
import online.erthru.tpk.util.Distance
import java.text.DecimalFormat


class RecyclerViewAdapterLocation(private val context: Context, private val arrayList: ArrayList<Results>?) : RecyclerView.Adapter<RecyclerViewAdapterLocation.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_location,parent,false))
    }

    override fun getItemCount(): Int = arrayList?.size ?: 0

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val location = arrayList?.get(position)
        holder.v.tvNameLL.text = location?.name
        holder.v.tvAddressLL.text = location?.vicinity

        val distance = Distance.calculate(Const.lat,Const.lng,location?.geometry?.location?.lat?.toDouble(),location?.geometry?.location?.lng?.toDouble())

        holder.v.tvDistanceLL.text = ""+DecimalFormat("#.##").format(distance)+" KM"

        holder.v.setOnClickListener {

            val i = Intent(context, LocationDetailActivity::class.java)
            i.putExtra("placeId",location?.place_id)
            i.putExtra("lat",location?.geometry?.location?.lat)
            i.putExtra("lng",location?.geometry?.location?.lng)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)

        }

    }


    class Holder(val v:View) : RecyclerView.ViewHolder(v)

}
