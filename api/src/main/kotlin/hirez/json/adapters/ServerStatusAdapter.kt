package hirez.json.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import hirez.json.Server

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
class ServerStatusAdapter : TypeAdapter<Server.Status>() {
	override fun write(out: JsonWriter, value: Server.Status) {
		out.value(value.name)
	}
	
	override fun read(`in`: JsonReader) = Server.Status.valueOf(`in`.nextString())
}