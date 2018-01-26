package nl.boydroid

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.greysonparrelli.permiso.Permiso
import com.greysonparrelli.permiso.PermisoActivity

class Permission {
    companion object {

        @JvmStatic
        /**
         * @deprecated
         * Please use the extension method AppCompatActivity.grantMe()
         */
        @Deprecated("Please use the extension method AppCompatActivity.grantMe()")
        fun request(vararg permissions: String?, permissionGranted: (result: Boolean) -> Unit,
                    rationaleTitle: String? = null, rationaleMessage: String? = null, buttonText: String? = null) {
            val instance = Permiso.getInstance()
            instance.requestPermissions(object : Permiso.IOnPermissionResult {
                override fun onRationaleRequested(callback: Permiso.IOnRationaleProvided, vararg permissions: String?) {

                    if (rationaleTitle != null && rationaleMessage != null) {
                        instance.showRationaleInDialog(rationaleTitle, rationaleMessage, buttonText, callback)
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

fun AppCompatActivity.grantMe(vararg permissions: String?, permissionGranted: (result: Boolean) -> Unit,
                                        rationaleTitle: String? = null, rationaleMessage: String? = null, buttonText: String? = null) {
    if (this !is PermisoActivity) {
        throw IllegalArgumentException("Activity must derive from PermisoActivity")
    }

    Permission.request(*permissions, permissionGranted = permissionGranted, rationaleTitle = rationaleTitle, rationaleMessage = rationaleMessage, buttonText = buttonText)
}