package com.urrecliner.blockpuzzle.game

import com.urrecliner.blockpuzzle.gamedefinition.SSLiberatedInfo
import com.urrecliner.blockpuzzle.gamestate.SpielstandDAO
import com.urrecliner.blockpuzzle.planet.IPlanet

class GameInfoService {

    fun isPlanetFullyLiberated(planet: IPlanet): Boolean {
        val defs = planet.gameDefinitions
        val dao = SpielstandDAO()
        for (gi in defs.indices) {
            val ss = dao.load(planet, gi)
            val info = SSLiberatedInfo(ss)
            if (!defs[gi].isLiberated(info)) {
                return false
            }
        }
        return defs.size > 0
    }

    fun executeLiberationFeature(planet: IPlanet) {
        planet.gameDefinitions[0].liberatedFeature?.start()
    }
}