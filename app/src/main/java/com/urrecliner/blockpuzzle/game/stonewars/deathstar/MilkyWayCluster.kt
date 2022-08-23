package com.urrecliner.blockpuzzle.game.stonewars.deathstar

import com.urrecliner.blockpuzzle.cluster.Cluster

/**
 * This is the star cluster "Solar System" in "Milky Way" galaxy. This cluster contains planet Earth.
 */
object MilkyWayCluster : Cluster(0) {

    init {
        add(DeathStar())
    }

    fun get(): DeathStar {
        return spaceObjects[0] as DeathStar
    }

    override fun getShortName(): String {
        return "S" // = Solar System
    }

    override fun getGalaxyShortName(): String {
        return "M" // = Milky Way
    }
}