package hirez.json.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
class DateFormatAdapter : JsonSerializer<Date>, JsonDeserializer<Date> {
	override fun serialize(src: Date, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
		return JsonPrimitive(DateFormat.getInstance().format(src))
	}
	
	override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date {
		return SimpleDateFormat("M/d/YYYY h:m:s a").apply {
			timeZone = TimeZone.getTimeZone("UTC")
		}.parse(json.asString)
	}
}