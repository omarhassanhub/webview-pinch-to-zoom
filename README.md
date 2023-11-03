# webview-pinch-to-zoom

This a sample project to demonstrate zoom issue in a webview. We are currently facing this issue on the BBC Sport app.

In our app, when a WebView is destroyed using `webView.destroy()`, it can lead to an issue where the WebView gains the capability to zoom into its content.
This problem impacts all pages containing a WebView within our app. We've attempted workarounds to prevent zooming programmatically, but so far, none of them have proven effective.

#### Reasons for destroying a webview:
* Destroy a WebView when it's no longer needed
* Destroy it in the `onDestroy` or `onDestroyView` methods to prevent memory leaks and ensure proper cleanup when the user navigates away from the WebView.
* During the back navigation process, the old WebView in what was the top fragment is destroyed, the lower fragment is restored, and in this way a new WebView instance is configured.

The following configurations does not prevent zoom after a webview is destroyed:
```
 settings.setSupportZoom(false)
 settings.builtInZoomControls = false
 settings.displayZoomControls = false
```

#### Problem Description:
**Expected result:**
* Users should not be able to zoom content in a WebView.

**Actual result:**
* Users can zoom content in a WebView.

**About Chrome version on mobile:**
* **Chrome version**: 117.0.5938.60
* **Channel**: Stable
* **OS**:Android

#### Instructions on how to run the app and reproduce the zoom issue:

* To run the app on Android Studio: Menu -> Run -> Run
* Press ADD WEBVIEW
* Pinch to zoom on the football page (It should not zoom)
* Press NAVIGATE BACK
* Press ADD WEBVIEW again
* Pinch to zoom on the football page (You should now be able to zoom the content)
* If you comment out `webView.destroy()`, re-run the app and perform the above steps again, you'll notice that you won't be able to zoom.

 
