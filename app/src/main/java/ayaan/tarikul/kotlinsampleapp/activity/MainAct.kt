package ayaan.tarikul.kotlinsampleapp.activity


import android.content.Intent
import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.RecyclerView
import ayaan.tarikul.kotlinsampleapp.R
import ayaan.tarikul.kotlinsampleapp.utils.PreferenceHelper
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainAct : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    var preferenceHelper : PreferenceHelper? = null;

    lateinit var cdRecy : CardView
    lateinit var cdTabDesign : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_dashboard)

        initViews()
    }


   fun initViews(){
       cdRecy = findViewById(R.id.cdview_recy_view)
       cdTabDesign = findViewById(R.id.cdview_tab_design)

       preferenceHelper = PreferenceHelper(this)
       fab.setOnClickListener { view ->
           Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               .setAction("Action", null).show()
       }

       val toggle = ActionBarDrawerToggle(
           this, drawer_layout, toolbar_dashboard, R.string.navigation_drawer_open, R.string.navigation_drawer_close
       )
       drawer_layout.addDrawerListener(toggle)
       toggle.syncState()

       nav_view.setNavigationItemSelectedListener(this)

       cdRecy.setOnClickListener(){
           val intent = Intent(this, RecyAndCardviewAct::class.java)
           startActivity(intent)
       }

       cdTabDesign.setOnClickListener(){
           val intent = Intent(this, TabDesignAct::class.java)
           startActivity(intent)
       }

   }



    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.sign_out ->{
                doLogout()
                return true
            }



            else -> return super.onOptionsItemSelected(item)
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    fun doLogout(){
        val intent = Intent(this, LoginAct::class.java)
        startActivity(intent)
        ActivityCompat.finishAffinity(this)
        preferenceHelper?.setValueToPreference("email","")
        preferenceHelper?.setValueToPreference("password","")

        preferenceHelper?.setBooleanValueToPreference("isLogin",false)
    }
}
