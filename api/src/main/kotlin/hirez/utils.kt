package hirez

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

private val map = TreeMap<Int, String>().apply {
	put(9, "IX")
	put(5, "V")
	put(4, "IV")
	put(1, "I")
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
@Throws(NoSuchAlgorithmException::class)
internal fun String.md5() = MessageDigest.getInstance("MD5").digest(toByteArray()).joinToString("") { String.format("%02x", it) }

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
fun Int.toRomanNumber(): String {
	val l = map.floorKey(this)
	if (this == l) {
		return map.getValue(this)
	}
	return map.getValue(l) + (this - l).toRomanNumber()
}