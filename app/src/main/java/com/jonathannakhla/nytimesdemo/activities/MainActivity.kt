package com.jonathannakhla.nytimesdemo.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jonathannakhla.nytimesdemo.R
import com.jonathannakhla.nytimesdemo.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, MainFragment.newInstance(), MainFragment.TAG)
            .commit()
    }
}
