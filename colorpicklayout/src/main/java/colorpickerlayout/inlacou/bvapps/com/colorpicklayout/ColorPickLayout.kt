package colorpickerlayout.inlacou.bvapps.com.colorpicklayout

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import java.lang.IllegalArgumentException
import android.view.View

class ColorPickLayout : FrameLayout {

	private var cursor: Cursor? = null
	private var actionDownTime: Long = 0
	var singleClickListener: SingleClickListener? = null
	var singleClickThresholdLimit = 75

	private val color: Int
		get() = cursor!!.color

	private val moveListener = object : MoveListener {
		override fun onMoveCenter(cursor: Cursor) {
			val centerX = measuredWidth / 2
			val centerY = measuredHeight / 2
			cursor.setPoint(centerX, centerY)
		}
	}

	val colorEnvelope: ColorWrapper
		get() = ColorWrapper(color)

	constructor(context: Context) : super(context) {}

	constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
		init()
		onCreate()
	}

	constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
		init()
		onCreate()
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
		init()
		onCreate()
	}

	private fun init() {
		viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
			override fun onGlobalLayout() {
				if (Build.VERSION.SDK_INT < 16) {
					viewTreeObserver.removeGlobalOnLayoutListener(this)
				} else {
					viewTreeObserver.removeOnGlobalLayoutListener(this)
				}
				onFirstLayout()
			}
		})
	}

	private fun onFirstLayout() {
		selectCenter()
		loadListeners()
	}

	private fun onCreate() {
		setPadding(0, 0, 0, 0)
	}

	private fun loadListeners() {
		setOnTouchListener { v, event ->
			if (cursor != null) {
				when (event.action) {
					MotionEvent.ACTION_DOWN -> {
						actionDownTime = System.currentTimeMillis()
						//onTouchReceived(event)
						true
					}
					MotionEvent.ACTION_MOVE -> {
						onTouchReceived(event)
					}
					MotionEvent.ACTION_UP -> {
						//onTouchReceived(event)
						if(System.currentTimeMillis()-actionDownTime<singleClickThresholdLimit) singleClickListener?.onSingleClick()
						false
					}
					else -> false
				}
			} else
				false
		}
	}

	private fun onTouchReceived(event: MotionEvent): Boolean {
		val snapPoint = Point(event.x.toInt(), event.y.toInt())
		cursor!!.color = getColorFromBitmap(snapPoint.x.toFloat(), snapPoint.y.toFloat())

		if (color != Color.TRANSPARENT) {
			cursor!!.setPoint(snapPoint.x, snapPoint.y)
			fireColorListener()
		}
		if (color == Color.TRANSPARENT){
			return false
		}
		return true
	}

	private fun getColorFromBitmap(x: Float, y: Float): Int {
		try {

			buildDrawingCache()
			if (drawingCache != null) return drawingCache.getPixel(x.toInt(), y.toInt())
		} catch (ignored: IllegalArgumentException) {
		}

		return 0
	}

	fun loadBitmapFromView(v: View): Bitmap {
		val b = Bitmap.createBitmap(v.layoutParams.width, v.layoutParams.height, Bitmap.Config.ARGB_8888)
		val c = Canvas(b)
		v.layout(0, 0, v.layoutParams.width, v.layoutParams.height)
		v.draw(c)
		return b
	}

	private fun fireColorListener() {
		cursor!!.colorListener.onColorSelected(colorEnvelope)
	}


	fun setSelector(colorListener: ColorListener): Cursor {
		val cursor = Cursor(colorListener)
		cursor.moveListener = moveListener
		cursor.onMoveCenter()
		this.cursor = cursor
		return cursor
	}

	private fun setSelectorPoint(x: Int, y: Int) {
		if (cursor != null) {
			cursor!!.setPoint(x, y)
			cursor!!.color = getColorFromBitmap(x.toFloat(), y.toFloat())
			fireColorListener()
		}
	}

	private fun selectCenter() {
		if (cursor != null)
			setSelectorPoint(measuredWidth / 2, measuredHeight / 2)
	}

	interface SingleClickListener{
		fun onSingleClick()
	}
}