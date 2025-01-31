package ltd.matrixstudios.alchemist.util.menu

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

abstract class Button {

    abstract fun getMaterial(player: Player) : Material
    abstract fun getDescription(player: Player) : MutableList<String>?
    abstract fun getDisplayName(player: Player) : String?
    abstract fun getData(player: Player) : Short

    abstract fun onClick(player: Player, slot: Int, type: ClickType)

    open fun getButtonItem(player: Player) : ItemStack? { return null }

    fun constructItemStack(player: Player) : ItemStack {
        if (getButtonItem(player) != null)
        {
            return getButtonItem(player)!!
        }

        val itemStack: ItemStack = ItemStack(getMaterial(player))

        itemStack.durability = getData(player)

        val itemMeta = itemStack.itemMeta

        itemMeta.displayName = getDisplayName(player)
        itemMeta.lore = getDescription(player)


        itemStack.itemMeta = itemMeta

        return itemStack
    }
}