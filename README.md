# InAppUpdates

## Prerequisites

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

## Dependency

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge above):

```gradle
dependencies {
	...
		   implementation 'com.github.immujahidkhan:InAppUpdates:1.0.1'
}
```
## Usage

```
 // Launch the coroutine in a CoroutineScope
        lifecycleScope.launch {
            val checkAppUpdate = CheckAppUpdate(this@MainActivity, this@MainActivity.packageName)
            val isUpdated = checkAppUpdate.isAppUpdated()

            if (isUpdated) {
                // App is not up to date
                // Implement your logic here (e.g., show a dialog or perform an action)
                Log.e(TAG, "App is not up to date")
            } else {
                // App is up to date
                // Implement your logic here
                Log.e(TAG, "App is up to date")
            }
        }
```
