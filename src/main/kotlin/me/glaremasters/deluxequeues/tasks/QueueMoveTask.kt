package me.glaremasters.deluxequeues.tasks

import me.glaremasters.deluxequeues.queues.DeluxeQueue
import net.md_5.bungee.api.config.ServerInfo

/**
 * Created by Glare
 * Date: 7/13/2019
 * Time: 10:47 PM
 */
class QueueMoveTask(
        private val queue: DeluxeQueue,
        private val server: ServerInfo
) : Runnable {

    override fun run() {
        // Make sure the queue isn't empty
        if (queue.queue.isEmpty()) {
            return
        }

        // Persist the notification to the user
        queue.queue.forEach(queue::notifyPlayer)

        // Check if the max amount of players on the server are the max slots
        if (queue.server.players.size >= queue.maxSlots) {
            return
        }
        // Get the player next in line
        val player = queue.queue.pollFirst() ?: return
        // Move the player to that server
        player.player.connect(server)
        player.isReadyToMove = true
        /*        // Remove the player from the queue
        queue.getQueue().pollFirst();*/
    }

}
