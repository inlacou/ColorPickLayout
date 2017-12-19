package colorpickerlayout.inlacou.bvapps.com.colorpicklayout

class ColorWrapper(val color: Int) {
	val html: String
		get() = Utils.getColorHtml(color)
	val rgb: IntArray
		get() = Utils.getColorRGB(color)

}
