package com.github.darkvanityoflight.recraftedcore.utils.tagutils

import com.github.darkvanityoflight.recraftedcore.ARecraftedPlugin
import com.github.darkvanityoflight.recraftedcore.utils.tagutils.persistentdatatypes.BooleanItemTagType
import com.github.darkvanityoflight.recraftedcore.utils.tagutils.persistentdatatypes.StringArrayItemTagType
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType

class TagManager {
    private var persistentDataContainer : PersistentDataContainer
    var itemMeta : ItemMeta? = null

    constructor(plugin : ARecraftedPlugin, itemMeta: ItemMeta){
        this.itemMeta = itemMeta
    }

    constructor(namespace : String, itemStack: ItemStack){
        itemMeta = itemStack.itemMeta
    }

    init {
        if (itemMeta != null) persistentDataContainer = itemMeta!!.persistentDataContainer
        else {
            throw NullPointerException("Item meta is not allowed to be null to create a TagManager")
        }
    }

    // Using a namespace key
    fun getString(key : NamespacedKey) : String? {
        return persistentDataContainer.get(key, PersistentDataType.STRING)
    }

    fun getInt(key: NamespacedKey) : Int?{
        return persistentDataContainer.get(key, PersistentDataType.INTEGER)
    }

    fun getShort(key: NamespacedKey) : Short?{
        return persistentDataContainer.get(key, PersistentDataType.SHORT)
    }

    fun getByte(key: NamespacedKey) : Byte?{
        return persistentDataContainer.get(key, PersistentDataType.BYTE)
    }

    fun getStringList(key : NamespacedKey) : Array<String>? {
        return persistentDataContainer.get(key, StringArrayItemTagType())
    }

    fun getIntList(key : NamespacedKey) : IntArray? {
        return  persistentDataContainer.get(key, PersistentDataType.INTEGER_ARRAY)
    }

    fun getTagContainer(key: NamespacedKey): PersistentDataContainer? {
        return persistentDataContainer.get(key, PersistentDataType.TAG_CONTAINER)
    }

    fun getTagContainerList(key: NamespacedKey) : Array<out PersistentDataContainer>? {
        return persistentDataContainer.get(key, PersistentDataType.TAG_CONTAINER_ARRAY)
    }

    fun getOther(key: NamespacedKey, persistentDataType: PersistentDataType<Any, Any>) : Any? {
        return persistentDataContainer.get(key, persistentDataType)
    }

    fun <T>getOtherWithType(key: NamespacedKey, persistentDataType: PersistentDataType<Any, Any>) : T? {
        return persistentDataContainer.get(key, persistentDataType) as? T
    }

    fun setString(key: NamespacedKey, string: String) {
        persistentDataContainer.set(key, PersistentDataType.STRING, string)
    }

    fun setInt(key: NamespacedKey, int: Int) {
        persistentDataContainer.set(key, PersistentDataType.INTEGER, int)
    }

    fun setShort(key: NamespacedKey, short: Short){
        persistentDataContainer.set(key, PersistentDataType.SHORT, short)
    }

    fun setByte(key: NamespacedKey, byte: Byte) {
        persistentDataContainer.set(key, PersistentDataType.BYTE, byte)
    }

    fun setStringList(key : NamespacedKey, stringArray: Array<String>){
        persistentDataContainer.set(key, StringArrayItemTagType(), stringArray)
    }

    fun setIntList(key: NamespacedKey, intArray: IntArray) {
        persistentDataContainer.set(key, PersistentDataType.INTEGER_ARRAY, intArray)
    }

    fun setTagContainer(key: NamespacedKey, tagContainer: PersistentDataContainer) {
        persistentDataContainer.set(key, PersistentDataType.TAG_CONTAINER, tagContainer)
    }

    fun setTagContainerList(key: NamespacedKey, tagContainerArray: Array<PersistentDataContainer>) {
        persistentDataContainer.set(key, PersistentDataType.TAG_CONTAINER_ARRAY, tagContainerArray)
    }

    fun setOther(key: NamespacedKey, type: PersistentDataType<Any, Any>, value: Any) {
        persistentDataContainer.set(key, type, value)
    }

    //TODO Using a key manager

    fun enrichWithMeta(itemStack: ItemStack){
        itemStack.itemMeta = itemMeta
    }
}