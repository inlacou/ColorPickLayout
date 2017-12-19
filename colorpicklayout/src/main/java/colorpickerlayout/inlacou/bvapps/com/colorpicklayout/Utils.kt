package colorpickerlayout.inlacou.bvapps.com.colorpicklayout

/**
 * Created by inlacou on 19/12/17.
 */
object Utils {

	fun getColorHtml(colorVal: Int): String {
		return java.lang.String.format("%06X", 0xFFFFFF and colorVal)
	}

	fun getColorRGB(colorVal: Int): IntArray {
		val rgb = IntArray(3)
		val color = java.lang.Long.parseLong(java.lang.String.format("%06X", 0xFFFFFF and colorVal), 16).toInt()
		rgb[0] = color shr 16 and 0xFF
		rgb[1] = color shr 8 and 0xFF
		rgb[2] = color shr 0 and 0xFF
		return rgb
	}

}
