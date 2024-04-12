package com.dflorez.assignment3.Views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.dflorez.assignment3.R
import com.dflorez.assignment3.ViewModels.GameViewModel

class Welcome : AppCompatActivity() {

    // ViewModel
    lateinit var viewModel: GameViewModel

    // References
    lateinit var drawerLayout: DrawerLayout
    lateinit var menu: ImageView
    lateinit var home: LinearLayout
    lateinit var game:LinearLayout
    lateinit var high_score:LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        //====================
        // Start Game
        //====================
        // Instantiate the ViewModel with a reference to the ViewModel Class
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        // Binding
        var playerNameFromInput : EditText = findViewById(R.id.playerNameET)
        val btnStartGame:Button = findViewById(R.id.btnStartGame)

        // Button Handler
        btnStartGame.setOnClickListener() {
            var playerName : String = playerNameFromInput.text.toString()

            // Redirect to Game Activity
            redirectActivity(
                this,
                Game::class.java,
                playerName
            )
        }


        //====================
        // Menu
        //====================
        // Drawer Layout
        drawerLayout = findViewById(R.id.drawerLayout)
        menu = findViewById(R.id.menu)
        home = findViewById(R.id.home) // Inside nav_drawer (menu items id)
        game = findViewById(R.id.game) // Inside nav_drawer (menu items id)
        high_score = findViewById(R.id.high_score) // Inside nav_drawer (menu items id)

        // Menu Handler
        menu.setOnClickListener {
            openDrawer(drawerLayout);
        }

        // Home Item
        home.setOnClickListener {
            recreate()
        }

        // Game Item
        game.setOnClickListener {
            redirectActivity(
                this,
                Game::class.java,
                viewModel.playerName.value
            ) // redirects to Game activity
        }

        // Game Item
        high_score.setOnClickListener {
            redirectActivity(
                this,
                HighScore::class.java,
                viewModel.playerName.value
            )
        }

    }

    //==========
    // Methods
    //==========
    // Method to redirect to another activity
    fun redirectActivity(activity: Activity, secondActivity: Class<*>?, playerName: String?) {
        val intent = Intent(activity, secondActivity)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("playerName", playerName)
        activity.startActivity(intent)
        activity.finish() // Destroy current activity
    }

    // Drawer Functions
    fun openDrawer(drawerLayout: DrawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    // Close Drawer (menu)
    fun closeDrawer(drawerLayout: DrawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    //This method will get called every time the recreate method gets called, activity gets recreated and drawer closes
    override fun onPause() {
        super.onPause()
        closeDrawer(drawerLayout)
    }

}