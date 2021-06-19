package com.github.darkvanityoflight.recraftedcore.utils.tagutils.persistentdatatypes

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType

class BooleanItemTagType : PersistentDataType<Byte, Boolean> {
    /**
     * Returns the primitive data type of this tag.
     *
     * @return the class
     */
    override fun getPrimitiveType(): Class<Byte> = Byte::class.java

    /**
     * Returns the complex object type the primitive value resembles.
     *
     * @return the class type
     */
    override fun getComplexType(): Class<Boolean> = Boolean::class.java

    /**
     * Returns the primitive data that resembles the complex object passed to
     * this method.
     *
     * @param complex the complex object instance
     * @param context the context this operation is running in
     * @return the primitive value
     */
    override fun toPrimitive(complex: Boolean, context: PersistentDataAdapterContext): Byte {
        return if (complex){
            0.toByte()
        }else{
            1.toByte()
        }
    }

    /**
     * Creates a complex object based of the passed primitive value
     *
     * @param primitive the primitive value
     * @param context the context this operation is running in
     * @return the complex object instance
     */
    override fun fromPrimitive(primitive: Byte, context: PersistentDataAdapterContext): Boolean {
        return primitive == 0.toByte()
    }
}