package com.github.darkvanityoflight.recraftedcore.tagutils.persistentdatatypes

import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.util.*


class StringArrayItemTagType : PersistentDataType<ByteArray, Array<String>> {
	private val charset : Charset

	constructor(charset: Charset){
		this.charset = charset
	}

	constructor(){
		charset  = Charset.defaultCharset()
	}

	override fun getPrimitiveType(): Class<ByteArray> {
		return ByteArray::class.java
	}

	override fun getComplexType(): Class<Array<String>> {
		return Array<String>::class.java
	}

	override fun toPrimitive(strings: Array<String>, itemTagAdapterContext: PersistentDataAdapterContext): ByteArray {
		val allStringBytes = arrayOfNulls<ByteArray>(strings.size)
		var total = 0
		for (i in allStringBytes.indices) {
			val bytes = strings[i].toByteArray(charset)
			allStringBytes[i] = bytes
			total += bytes.size
		}
		val buffer: ByteBuffer = ByteBuffer.allocate(total + allStringBytes.size * 4) //stores integers
		for (bytes in allStringBytes) {
			buffer.putInt(bytes!!.size)
			buffer.put(bytes)
		}
		return buffer.array()
	}

	override fun fromPrimitive(bytes: ByteArray, itemTagAdapterContext: PersistentDataAdapterContext): Array<String> {
		val buffer: ByteBuffer = ByteBuffer.wrap(bytes)
		val list = ArrayList<String>()
		while (buffer.remaining() > 0) {
			if (buffer.remaining() < 4) break
			val stringLength: Int = buffer.int
			if (buffer.remaining() < stringLength) break
			val stringBytes = ByteArray(stringLength)
			buffer.get(stringBytes)
			list.add(String(stringBytes, charset))
		}
		return list.toTypedArray()
	}
}
