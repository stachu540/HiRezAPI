package hirez.json

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import hirez.json.adapters.BooleanTextDeserializer

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class QueueMatch(
			@SerializedName("ret_msg")
			override val returnMessage: String?,
			@SerializedName("match", alternate = ["Match"])
			override val id: Long,
			@JsonAdapter(BooleanTextDeserializer::class)
			@SerializedName("active_flag", alternate = ["Active_Flag"])
			val active: Boolean
) : IdObject<Long>, ReturnMessage