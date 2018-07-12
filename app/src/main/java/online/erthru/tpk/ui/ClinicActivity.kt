package online.erthru.tpk.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable
import com.valdesekamdem.library.mdtoast.MDToast
import im.delight.android.location.SimpleLocation
import online.erthru.tpk.network.ApiConfig
import kotlinx.android.synthetic.main.activity_clinic.*
import online.erthru.tpk.R
import online.erthru.tpk.adapter.RecyclerViewAdapterLocation
import online.erthru.tpk.network.response.ResultNearby
import online.erthru.tpk.util.Const
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Response

class ClinicActivity : AppCompatActivity() {

    lateinit var simpleLocation: SimpleLocation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clinic)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor("#03A9F4")
        }

        pbClinic.indeterminateDrawable = FoldingCirclesDrawable.Builder(this)
                .colors(resources.getIntArray(R.array.google_colors)).build()

        rvClinic.setHasFixedSize(true)
        rvClinic.layoutManager = LinearLayoutManager(this)

        simpleLocation = SimpleLocation(this)

        if(!simpleLocation.hasLocationEnabled()){
            SimpleLocation.openSettings(this)
        }

        val lat = simpleLocation.latitude
        val lng = simpleLocation.longitude
        val location = "${lat.toString()},${lng.toString()}"

        Const.lat = lat
        Const.lng = lng

        Log.d("LOCATION",location)

        rvClinic.adapter = RecyclerViewAdapterLocation(this,null)
        loadClinicList(location)

        imgCloseClinic.setOnClickListener { this.finish() }

    }

    override fun onResume() {
        super.onResume()
        simpleLocation.beginUpdates()
    }

    override fun onPause() {
        super.onPause()
        simpleLocation.endUpdates()
    }


    private fun loadClinicList(myLocation:String?){

        pbClinic.visibility = View.VISIBLE
        ApiConfig().instance().json(ApiConfig.API_KEY,"dokter",myLocation,"distance")
                .enqueue(object : retrofit2.Callback<ResultNearby>{

                    override fun onFailure(call: Call<ResultNearby>?, t: Throwable?) {
                        pbClinic.visibility = View.GONE
                        MDToast.makeText(applicationContext,"Koneksi bermasalah", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show()
                        finish()
                    }

                    override fun onResponse(call: Call<ResultNearby>?, response: Response<ResultNearby>?) {

                        if(response?.body()?.results?.size == 0 || response?.body()?.results == null){

                            MDToast.makeText(applicationContext,"Data kosong", MDToast.LENGTH_SHORT, MDToast.TYPE_ERROR).show()
                            finish()

                        }

                        pbClinic.visibility = View.GONE
                        val adapter = RecyclerViewAdapterLocation(applicationContext,response?.body()?.results)
                        adapter.notifyDataSetChanged()
                        rvClinic.adapter = adapter

                    }

                })

    }

}
