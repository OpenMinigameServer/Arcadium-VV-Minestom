package io.github.openminigameserver.arcadium

import net.minestom.server.Bootstrap

fun main(args: Array<String>) {
    // allow to load mixins without using an extension, nor enforcing launch arguments
    val argsWithMixins = arrayOfNulls<String>(args.size + 2)
    System.arraycopy(args, 0, argsWithMixins, 0, args.size)
    argsWithMixins[argsWithMixins.size - 2] = "--mixin"
    argsWithMixins[argsWithMixins.size - 1] = "mixins.arcadium.json"

    Bootstrap.bootstrap("io.github.openminigameserver.arcadium.ArcadiumKt", argsWithMixins)

}