package ltd.matrixstudios.alchemist.commands.punishments.create

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.*
import co.aikar.commands.annotation.Optional
import ltd.matrixstudios.alchemist.models.grant.types.Punishment
import ltd.matrixstudios.alchemist.models.profile.GameProfile
import ltd.matrixstudios.alchemist.punishment.BukkitPunishmentFunctions
import ltd.matrixstudios.alchemist.punishments.PunishmentType
import ltd.matrixstudios.alchemist.punishments.actor.ActorType
import ltd.matrixstudios.alchemist.punishments.actor.DefaultActor
import ltd.matrixstudios.alchemist.punishments.actor.executor.Executor
import ltd.matrixstudios.alchemist.util.TimeUtil
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class TempBanCommand : BaseCommand() {

    @CommandAlias("tempban|tb")
    @CommandPermission("alchemist.punishments.tempban")
    fun ban(sender: CommandSender, @Name("target") gameProfile: GameProfile, @Flags("s") silent: Boolean, @Name("duration")time: String, @Name("reason") reason: String) {
        val punishment = Punishment(
            PunishmentType.BAN.name,
            gameProfile.uuid,
            BukkitPunishmentFunctions.getSenderUUID(sender),
            reason, TimeUtil.parseTime(time),

            DefaultActor(
                BukkitPunishmentFunctions.getExecutorFromSender(sender),
                Bukkit.getServerName(),
                ActorType.GAME)

        )

        BukkitPunishmentFunctions.dispatch(punishment, silent != null)

    }

}