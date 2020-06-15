package hook.intent.anywhere;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if ("android".equals(lpparam.packageName)) {
            Class clazz = XposedHelpers.findClass("com.android.server.am.ActivityStackSupervisor",
                    lpparam.classLoader);
            XposedBridge.hookAllMethods(clazz, "checkStartAnyActivityPermission", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(true);
                }
            });
        }
    }
}
