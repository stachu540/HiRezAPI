package hirez.exceptions

import java.io.IOException

/**
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
class ResponseException(
			val code: Int,
			val responseMessage: String
) : IOException("[$code] $responseMessage")
