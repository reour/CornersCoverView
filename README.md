CornersCoverView
===============

Android corners cover view.

![CornersCoverView](https://raw.github.com/reour/CornersCoverView/master/image/sample.png)


Gradle
------
```
allprojects {
	repositories {
		...
		maven { url 'https://www.jitpack.io' }
	}
}
```
```
dependencies {
    ...
    implementation 'com.github.reour:CornersCoverView:1.0'
}
```

Usage
-----
```xml
<RelativeLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    
        <ImageView
            android:id="@+id/your_view"
            android:layout_width="200dp"
            android:layout_height="120dp" />
    
        <com.reour.library.view.CornersCoverView
            xmlns:app="http://schemas.android.com/apk/res-auto"
            android:layout_width="200dp"
            android:layout_height="120dp"
            app:corners_color="#23FF11"
            app:corners_radius="60dp" />
            
</RelativeLayout>
```

Attribute
-----
- app:corners_radius
- app:left_top_radius
- app:left_bottom_radius
- app:right_top_radius
- app:right_bottom_radius
- app:corners_color
- app:left_top_color
- app:left_bottom_color
- app:right_top_color
- app:right_bottom_color

Changelog
---------
* **1.0.**
    * Initial release
