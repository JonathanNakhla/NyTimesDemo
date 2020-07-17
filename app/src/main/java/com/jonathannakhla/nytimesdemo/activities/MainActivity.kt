package com.jonathannakhla.nytimesdemo.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.idescout.sql.SqlScoutServer
import com.jonathannakhla.nytimesdemo.R
import com.jonathannakhla.nytimesdemo.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var sqlScoutServer: SqlScoutServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sqlScoutServer = SqlScoutServer.create(this.applicationContext, packageName)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, MainFragment.newInstance(), MainFragment.TAG)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        sqlScoutServer.resume()
    }

    override fun onPause() {
        super.onPause()
        sqlScoutServer.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        sqlScoutServer.destroy()
    }
}