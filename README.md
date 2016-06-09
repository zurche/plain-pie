# plain-pie

[![Release](https://jitpack.io/v/zurche/plain-pie.svg)](https://jitpack.io/#zurche/plain-pie/v0.1) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-plain--pie-green.svg?style=true)](https://android-arsenal.com/details/1/3689)

Add to your project build.gradle
```groovy
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Add to your app module build.gradle
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
PieView pieView = (PieView) findViewById(R.id.pieView);
pieView.setPercentageBackgroundColor(getResources().getColor(R.color.customColor2));
pieView.setInnerText("A");
```

You can update any color of the widget:
```javascript
PieView pieView = (PieView) findViewById(R.id.pieView);

// Change the color fill of the bar representing the current percentage
pieView.setPercentageBackgroundColor(getResources().getColor(R.color.customColor1));

// Change the color fill of the center of the widget
pieView.setCenterBackgroundColor(getResources().getColor(R.color.customColor21));

// Change the color fill of the background of the widget, by default is transparent
pieView.setMainBackgroundColor(getResources().getColor(R.color.customColor5));

// Change the color of the text of the widget
pieView.setTextColor(getResources().getColor(R.color.customColor12));
```

If required you can also change the area covered by the inner circle of the widget to change is appearence:
```javascript
PieView pieView = (PieView) findViewById(R.id.pieView);

// Change the thickness of the percentage bar
pieView.setPieInnerPadding(50);
```

And finally update the text if you want the widget to represent other thing or even toggle the visibility of its center text:
```javascript
PieView pieView = (PieView) findViewById(R.id.pieView);

// Update the visibility of the widget text
pieView.setInnerTextVisibility(View.VISIBLE);

// Change the text of the widget
pieView.setInnerText("A");

// Change the text size of the widget
pieView.setPercentageTextSize(35);
```

Without inner text         |  With a label instead of a percentage
:-------------------------:|:-------------------------:
![alt tag](https://github.com/zurche/plain-pie/blob/master/img/pie_3.png) | ![alt tag](https://github.com/zurche/plain-pie/blob/master/img/pie_2.png)


License
--------

    Copyright 2016 Alejandro Zürcher

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
