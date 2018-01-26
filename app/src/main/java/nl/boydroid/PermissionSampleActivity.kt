package nl.boydroid

import android.os.Bundle
import android.util.Log
import com.greysonparrelli.permiso.PermisoActivity

class PermissionSampleActivity : PermisoActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        grantMe(android.Manifest.permission.CAMERA, permissionGranted = {
            if (it) {
                Log.d("Granted", "yes")
            } else {
                Log.d("Granted", "aaaah....shucks!")
            }
        })

    }
}