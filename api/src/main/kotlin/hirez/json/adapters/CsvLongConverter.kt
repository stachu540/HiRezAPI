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
class CsvLongConverter : JsonDeserializer<List<Long>> {
	override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): List<Long> =
				json.asString.let { if (it.isEmpty()) emptyList() else it.split(", ").map { it.toLong() } }
}