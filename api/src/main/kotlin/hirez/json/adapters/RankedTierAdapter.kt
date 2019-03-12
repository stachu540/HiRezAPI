package hirez.json.adapters

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import hirez.json.RankedItem

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
class RankedTierAdapter : TypeAdapter<RankedItem.Tier>() {
	override fun write(out: JsonWriter, value: RankedItem.Tier) {
		out.value(value.value)
	}
	
	override fun read(`in`: JsonReader): RankedItem.Tier = RankedItem.Tier.values()[`in`.nextInt()]
}