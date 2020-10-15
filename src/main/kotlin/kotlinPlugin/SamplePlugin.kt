package kotlinPlugin

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.TNTPrimed

/*
 * @author Steve28
 */

class SamplePlugin: JavaPlugin(){
    override fun onEnable() {
        logger.info("Plugin enabled")
        Bukkit.getWorlds().first().apply {
            val border = worldBorder
            border.center = Location(this, 0.0, 0.0, 0.0)
            border.size = 1000.0
            spawnLocation = getHighestBlockAt(0, 0).location
        }
        server.scheduler.runTaskTimer(this, SummonTNT(), 0L,60000L)
    }

    override fun onDisable(){
        logger.info("Plugin disabled")
    }
}

class SummonTNT: Runnable {
    override fun run() {
        for (players in Bukkit.getOnlinePlayers()) {
            val location = (players?.location ?: Bukkit.getWorlds().first().spawnLocation).add(0.0,3.0,0.0)
            val world = location.world
            world?.spawn(location, TNTPrimed::class.java) {
                entity -> entity.fuseTicks = 200
            }
        }
        Bukkit.broadcastMessage("폭탄 떨어져유~")
    }
}
