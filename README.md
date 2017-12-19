# ColorPickLayout
[![](https://jitpack.io/v/inlacou/colorpicklayout.svg)](https://jitpack.io/#inlacou/colorpicklayout)

Click on jitpack plugin above here to see how to import last library version.

Layout in which you can select color on touch.

Add as your base layout:
```xml
<colorpickerlayout.inlacou.bvapps.com.colorpicklayout.ColorPickLayout
		android:id="@+id/colorPickerLayout"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:layout_marginTop="16dp"
		android:layout_gravity="center">
		<RelativeLayout
			android:layout_width="match_parent"
			android:layout_height="wrap_content">
			<ImageView
				android:id="@+id/img1"
				android:layout_width="300dp"
				android:layout_height="200dp"
				android:layout_alignParentLeft="true"
				android:layout_alignParentStart="true"
				android:src="@drawable/picture1"/>
			<ImageView
				android:layout_width="300dp"
				android:layout_height="200dp"
				android:layout_alignParentRight="true"
				android:layout_alignParentEnd="true"
				android:src="@drawable/picture1"/>
		</RelativeLayout>
	</colorpickerlayout.inlacou.bvapps.com.colorpicklayout.ColorPickLayout>
```

Set the listener:
```kotlin
  colorPickLayout.setSelector(object: ColorListener {
		override fun onColorSelected(envelope: ColorWrapper) {
			colorDisplay.setBackgroundColor(envelope.color) //Do whatever with the color
		})
```
