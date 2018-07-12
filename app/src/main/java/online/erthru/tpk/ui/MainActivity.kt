package online.erthru.tpk.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import online.erthru.tpk.R
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!EasyPermissions.hasPermissions(this,android.Manifest.permission.ACCESS_FINE_LOCATION) || !EasyPermissions.hasPermissions(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)){

            EasyPermissions.requestPermissions(this,"Aplikasi membutuhkan lokasi mu",991,android.Manifest.permission.ACCESS_FINE_LOCATION)
            EasyPermissions.requestPermissions(this,"Aplikasi membutuhkan lokasi mu",992,android.Manifest.permission.ACCESS_COARSE_LOCATION)

        }

        layoutHospitalMain.setOnClickListener { startActivity(Intent(applicationContext, ClinicActivity::class.java)) }
        layoutDrugStoreMain.setOnClickListener { startActivity(Intent(applicationContext, DrugsStoreActivity::class.java)) }
        layoutAboutMain.setOnClickListener { startActivity(Intent(applicationContext, AboutActivity::class.java)) }
        layoutHelpMain.setOnClickListener { startActivity(Intent(applicationContext, HelpActivity::class.java)) }

    }
}
