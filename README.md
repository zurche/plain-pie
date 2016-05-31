# plain-pie
A simple plain pie chart widget fully customizable

Add to you PROJECT build.gradle
```groovy
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Add to you APP MODULE build.gradle
```groovy
dependencies {
        compile 'com.github.zurche:plain-pie:v0.1'
}
```
<p align="center">
  <img src="https://github.com/zurche/plain-pie/blob/master/img/pie_1.png" alt="Example Widget"/>
</p>

Add the widget to your layout
```xml
<az.plainpie.PieView
    android:id="@+id/pieView"
    android:layout_width="200dp"
    android:layout_height="200dp"
    plainpie:inner_pie_padding="30"
    plainpie:percentage="75"
    plainpie:percentage_size="70"
    plainpie:inner_text_visibility="true"/>
```

Remember to add a namespace for the library in your layout:
```javascript
xmlns:plainpie="http://schemas.android.com/apk/res-auto"
```

You can customize the widget from the layout file, or from the activity once you obtain a reference to it:
```javascript
PieView pieView3 = (PieView) findViewById(R.id.pieView3);
pieView3.setPercentageBackgroundColor(getResources().getColor(R.color.customColor2));
pieView3.setInnerText("A");
```

You can update any color of the widget:
```javascript
PieView pieView2 = (PieView) findViewById(R.id.pieView2);

// Change the color fill of the bar representing the current percentage
pieView2.setPercentageBackgroundColor(getResources().getColor(R.color.customColor1));

// Change the color fill of the center of the widget
pieView2.setCenterBackgroundColor(getResources().getColor(R.color.customColor21));

// Change the color fill of the background of the widget, by default is transparent
pieView2.setMainBackgroundColor(getResources().getColor(R.color.customColor5));

// Change the color of the text of the widget
pieView2.setTextColor(getResources().getColor(R.color.customColor12));
```

If required you can also change the area covered by the inner circle of the widget to change is appearence:
```javascript
PieView pieView4 = (PieView) findViewById(R.id.pieView4);

// Change the thickness of the percentage bar
pieView4.setPieInnerPadding(50);
```

And finally update the text if you want the widget to represent other thing or even toggle the visibility of its center text:
```javascript
PieView pieView3 = (PieView) findViewById(R.id.pieView3);

// Update the visibility of the widget text
pieView3.setInnerTextVisibility(View.VISIBLE);

// Change the text of the widget
pieView3.setInnerText("A");

// Change the text size of the widget
pieView3.setPercentageTextSize(35);
```

Without inner text         |  With a label instead of a percentage
:-------------------------:|:-------------------------:
![alt tag](https://github.com/zurche/plain-pie/blob/master/img/pie_3.png) | ![alt tag](https://github.com/zurche/plain-pie/blob/master/img/pie_2.png)



