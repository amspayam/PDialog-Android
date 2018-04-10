# PDialog-Android
Android Library to show a beautiful Alert View
<br/>
[![](https://jitpack.io/v/amspayam/PDialog-Android.svg)](https://jitpack.io/#amspayam/PDialog-Android)
<br/>
Minimum API 17
[Sample project](https://github.com/amspayam/AlertView-Android/tree/master/app)
## Usage:
#### Step 1

Add JitPack repository in your root build.gradle at the end of repositories.

    allprojects {
        repositories {
    	    ...
    	    maven { url 'https://jitpack.io' }
        }
    }
   
Add dependency in your app level build.gradle.

    dependencies {
	        implementation 'com.github.amspayam:PDialog-Android:1.0.0'
	}
   
#### Step 2
use in Java Code
```Java
new PDialog
        .PDialogBuilder(context, "hi")
        .titleColor(R.color.colorPrimary)
        .isCancelable(false)
        .firstButtonTitle("Ok")
        .setFirstButtonCallBack(MyActivity.this)
        .build();
```
