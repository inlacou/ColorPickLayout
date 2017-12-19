package colorpickerlayout.inlacou.bvapps.com.colorpickerlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import colorpickerlayout.inlacou.bvapps.com.colorpicklayout.ColorListener
import colorpickerlayout.inlacou.bvapps.com.colorpicklayout.ColorWrapper
import kotlinx.android.synthetic.main.activity_example.*

class ExampleColorPickerView : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_example)

		colorPickerLayout.setSelector(colorListener)
	}

	private val colorListener = object: ColorListener {
		override fun onColorSelected(envelope: ColorWrapper) {
			colorDisplay.setBackgroundColor(envelope.color)
		}
	}

}