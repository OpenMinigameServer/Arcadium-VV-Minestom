package io.github.openminigameserver.arcadium

import de.gerrygames.viarewind.api.ViaRewindConfigImpl
import de.gerrygames.viarewind.api.ViaRewindPlatform
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.GameMode
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.instance.Chunk
import net.minestom.server.instance.ChunkGenerator
import net.minestom.server.instance.ChunkPopulator
import net.minestom.server.instance.batch.ChunkBatch
import net.minestom.server.instance.block.Block
import net.minestom.server.utils.Position
import net.minestom.server.world.biomes.Biome
import java.util.*
import us.myles.ViaVersion.api.Via
import io.github.openminigameserver.arcadium.via.MinestomViaLoader
import io.github.openminigameserver.arcadium.via.MinestomViaInjector
import us.myles.ViaVersion.ViaManager
import io.github.openminigameserver.arcadium.via.MinestomViaPlatform
import nl.matsv.viabackwards.ViaBackwards

import io.github.openminigameserver.arcadium.via.backwards.MinestomViaBackwardsPlatform
import io.github.openminigameserver.arcadium.via.rewind.MinestomViaRewindPlatform
import java.io.File


fun main(args: Array<String>) {
    val server = MinecraftServer.init()
    MinecraftServer.setGroupedPacket(false)
    MinecraftServer.setPacketCaching(false)
    MinecraftServer.setShouldProcessNettyErrors(true)
    val instanceManager = MinecraftServer.getInstanceManager()

    // Create the instance
    val instanceContainer = instanceManager.createInstanceContainer()
    // Set the ChunkGenerator
    instanceContainer.chunkGenerator = object : ChunkGenerator {
        override fun generateChunkData(batch: ChunkBatch, chunkX: Int, chunkZ: Int) {
            for (x in 0 until Chunk.CHUNK_SIZE_X) for (z in 0 until Chunk.CHUNK_SIZE_Z) {
                for (y in 0..54) {
                    batch.setBlock(x, y, z, Block.STONE)
                }
            }
        }

        override fun fillBiomes(biomes: Array<out Biome>, chunkX: Int, chunkZ: Int) = Arrays.fill(biomes, Biome.PLAINS);

        override fun getPopulators(): MutableList<ChunkPopulator>? = null

    }
    // Enable the auto chunk loading (when players come close)
    instanceContainer.enableAutoChunkLoad(true)

    // Add an event callback to specify the spawning instance (and the spawn position)
    val globalEventHandler = MinecraftServer.getGlobalEventHandler()
    globalEventHandler.addEventCallback(
        PlayerLoginEvent::class.java
    ) { event: PlayerLoginEvent ->
        val player = event.player
        player.isAllowFlying = true
        player.gameMode = GameMode.CREATIVE

        event.setSpawningInstance(instanceContainer)
        player.respawnPoint = Position(0f, 55.0f, 0f)
    }

    registerVia()

    server.start(
        "0.0.0.0", 25565
    ) { connection, responseData ->
        responseData.apply {
            responseData.setDescription("NickArcade + Arcadium")
        }
    }
}

private fun registerVia() {
    //ViaVersion
    val platform = MinestomViaPlatform()
    MinecraftServer.setCompressionThreshold(0)
    Via.init(
        ViaManager.builder()
            .injector(MinestomViaInjector())
            .loader(MinestomViaLoader())
            .platform(platform).build()
    )
    platform.isEnabled = true
    MinestomViaBackwardsPlatform().also {
        it.init(it.dataFolder)
    }
    MinestomViaRewindPlatform().also {
        it.init(ViaRewindConfigImpl(File("ViaRewind", "config.yml")))
    }
    Via.getManager().init()
}