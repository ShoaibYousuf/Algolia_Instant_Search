package com.example.algoliatask

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace


class MainActivity : AppCompatActivity() {

    val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getting_started)
        showProductFragment()
        setupNavigation()
    }

    private fun showFacetFragment() {
        supportFragmentManager.commit {
            add<FacetFragment>(R.id.container)
            addToBackStack("facet")
        }
    }

    private fun setupNavigation() {
        viewModel.displayFilters.observe(this) {
            showFacetFragment()
        }
    }

    private fun showProductFragment() {
        supportFragmentManager.commit {
            replace<ProductFragment>(R.id.container)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (supportFragmentManager.popBackStackImmediate()) return true
        return super.onSupportNavigateUp()
    }
}
