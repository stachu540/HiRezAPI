package hirez.json

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
interface IdObject<T> {
	val id: T
}


interface ReturnMessage {
	@get:SerializedName("ret_msg")
	val returnMessage: String?
}