package colorpickerlayout.inlacou.bvapps.com.colorpickerlayout

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import colorpickerlayout.inlacou.bvapps.com.colorpicklayout.ColorListener
import colorpickerlayout.inlacou.bvapps.com.colorpicklayout.ColorPickLayout
import colorpickerlayout.inlacou.bvapps.com.colorpicklayout.ColorWrapper
import colorpickerlayout.inlacou.bvapps.com.colorpicklayout.Utils
import kotlinx.android.synthetic.main.activity_example.*

class ExampleColorPickerView : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_example)

		colorPickerLayout.setSelector(colorListener)
		colorPickerLayout.singleClickListener = object: ColorPickLayout.SingleClickListener{
			override fun onSingleClick() {
				Toast.makeText(this@ExampleColorPickerView, "Single click", Toast.LENGTH_SHORT).show()
			}
		}

		Log.d("color", Utils.getColorHtml(Color.BLACK))
		Log.d("color", ""+Color.parseColor("#DDDDDD"))
	}

	private val colorListener = object: ColorListener {
		override fun onColorSelected(envelope: ColorWrapper) {
			colorDisplay.setBackgroundColor(envelope.color)
		}
	}

}