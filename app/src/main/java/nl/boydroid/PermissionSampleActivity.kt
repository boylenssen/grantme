package nl.boydroid

import android.app.Activity
import android.os.Bundle

class PermissionSampleActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Permission.request(android.Manifest.permission.CAMERA, permissionGranted = {})
    }
}