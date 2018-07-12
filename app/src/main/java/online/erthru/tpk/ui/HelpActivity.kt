package online.erthru.tpk.ui

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_help.*
import online.erthru.tpk.R

class HelpActivity : AppCompatActivity() {

    val content = "1. Untuk melihat daftar tempat prakter dokter, Tap bagian prakter dokter " +
            "kemudian pilih tempat prakter yang akan didatangi. Tap tombol 'PETUNJUK ARAH' untuk melihat " +
            "direction tempat tersebut dari lokasimu ke tempat tujuan \n\n" +
            "2. Untuk melihat daftar apotek, Tap bagian apotek " +
            "kemudian pilih apotek yang akan didatangi. Tap tombol 'PETUNJUK ARAH' untuk melihat " +
            "direction tempat tersebut dari lokasimu ke tempat tujuan \n\n" +
            "3. Semua tempat yang ditampilkan telah diurutkan berdasarkan yang terdekat."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor("#00BCD4")
        }

        imgCloseHelp.setOnClickListener { this.finish() }
        tvContentHelp.text = content

    }
}
