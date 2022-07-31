package ltd.matrixstudios.alchemist.network

import ltd.matrixstudios.alchemist.api.AlchemistAPI
import ltd.matrixstudios.alchemist.service.profiles.ProfileGameService
import java.util.*
import java.util.concurrent.TimeUnit

object NetworkManager {

    fun hasFullyDCed(target: UUID): Boolean {
        var offline = false
        val it = AlchemistAPI.quickFindProfile(target).get()

        if (!it!!.isOnline() && System.currentTimeMillis().minus(it.lastSeenAt) >= TimeUnit.MINUTES.toMillis(1L)) {
            offline = true
        } else if (it.isOnline()) {
            offline = false
        }

        return offline
    }

    fun joinedServerFromList(target: UUID) : Boolean {
        val profile = ProfileGameService.byId(target) ?: return false

        if (!profile.isOnline()) return true

        return false
    }
}