package hirez.json.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
class BooleanTextDeserializer : JsonDeserializer<Boolean> {
	override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext) =
				json.asString.let {
					when {
						it.equals("y", ignoreCase = true) -> true
						it.equals("n", ignoreCase = true) -> false
						else -> context.deserialize(json, typeOfT)
					}
				}
}