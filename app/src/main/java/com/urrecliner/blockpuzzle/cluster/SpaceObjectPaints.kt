package com.urrecliner.blockpuzzle.cluster

import android.content.Context
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.urrecliner.blockpuzzle.R
import com.urrecliner.blockpuzzle.game.stonewars.deathstar.SpaceNebula
import com.urrecliner.blockpuzzle.planet.AbstractPlanet
import com.urrecliner.blockpuzzle.planet.GiantPlanet
import com.urrecliner.blockpuzzle.planet.Moon
import com.urrecliner.blockpuzzle.planet.Planet

class SpaceObjectPaints(context: Context) {
    private val planet = Paint()
    private val giantPlanet = Paint()
    private val moon = Paint()
    private val ownerMarker = Paint()
    private val nebula = Paint()

    init {
        planet.color = ContextCompat.getColor(context, R.color.planet)
        planet.isAntiAlias = true

        giantPlanet.color = ContextCompat.getColor(context, R.color.giantPlanet)
        giantPlanet.isAntiAlias = true

        moon.color = ContextCompat.getColor(context, R.color.moon)
        moon.isAntiAlias = true

        ownerMarker.color = ContextCompat.getColor(context, R.color.myPlanet)
        ownerMarker.strokeWidth = 6f

        nebula.color = ContextCompat.getColor(context, R.color.spaceNebula)
    }

    fun prepare() {
        GiantPlanet.paint = giantPlanet
        Planet.paint = planet
        Moon.paint = moon
        AbstractPlanet.ownerMarkerPaint = ownerMarker
        SpaceNebula.paint = nebula
    }

    /** Set all paint objects to null. */
    fun cleanup() {
        GiantPlanet.paint = null
        Planet.paint = null
        Moon.paint = null
        AbstractPlanet.ownerMarkerPaint = null
        SpaceNebula.paint = null
    }
}