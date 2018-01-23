package nl.boydroid

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import com.greysonparrelli.permiso.Permiso

class Permission {
    companion object {
        @JvmStatic
        fun request(vararg permissions: String?, permissionGranted: (result: Boolean) -> Unit,
                    rationaleTitle: String? = null, rationaleMessage: String? = null, buttonText: String? = null) {
            Permiso.getInstance().requestPermissions(object : Permiso.IOnPermissionResult {
                override fun onRationaleRequested(callback: Permiso.IOnRationaleProvided, vararg permissions: String?) {

                    if (rationaleTitle != null && rationaleMessage != null) {
                        Permiso.getInstance().showRationaleInDialog(rationaleTitle, rationaleMessage, buttonText, callback)
                        return
                    }

                    callback.onRationaleProvided()
                }

                override fun onPermissionResult(resultSet: Permiso.ResultSet) {
                    permissionGranted(resultSet.areAllPermissionsGranted())
                }
            }, *permissions)
        }

        fun isGranted(context: Context, permission: String) = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
}
