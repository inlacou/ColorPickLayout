package colorpickerlayout.inlacou.bvapps.com.colorpicklayout

class Cursor(val colorListener: ColorListener) {

	var x: Int = 0
		private set
	var y: Int = 0
		private set

	var color: Int = 0
	var moveListener: MoveListener? = null

	fun setPoint(x: Int, y: Int) {
		this.x = x
		this.y = y
	}

	fun onMoveCenter() {
		moveListener?.onMoveCenter(this)
	}

}