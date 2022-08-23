package com.urrecliner.blockpuzzle.cluster

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.urrecliner.blockpuzzle.R
import com.urrecliner.blockpuzzle.game.GameEngineFactory
import com.urrecliner.blockpuzzle.game.stonewars.deathstar.MilkyWayAlert
import com.urrecliner.blockpuzzle.global.AbstractDAO
import kotlinx.android.synthetic.main.activity_start.*

/**
 * Navigation activity
 */
class NavigationActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        if (Build.VERSION.SDK_INT >= 21) {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.navigationBackground)
        }
        AbstractDAO.init(this)

        // build view ----
        clusterView.clusterViewParent = clusterViewParent
        clusterView.setSelectTargetButton(selectTarget)
        selectTarget.setOnClickListener { clusterView.selectTarget() }

        // set data ----
        clusterView.setModel(ClusterViewModel(Cluster1.spaceObjects, GameEngineFactory().getPlanet(), resources, MilkyWayAlert(this)))

        // ensure new daily planet is visible if player is already in delta quadrant ----
        Cluster1Aufdeckungen(Cluster1.spaceObjects).fix()
    }
}