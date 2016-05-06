package org.apache.cordova;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaInterfaceImpl
  implements CordovaInterface
{
  private static final String TAG = "CordovaInterfaceImpl";
  protected Activity activity;
  protected CordovaPlugin activityResultCallback;
  protected int activityResultRequestCode;
  protected boolean activityWasDestroyed = false;
  protected String initCallbackService;
  protected CordovaPlugin permissionResultCallback;
  protected PluginManager pluginManager;
  protected Bundle savedPluginState;
  protected ActivityResultHolder savedResult;
  protected ExecutorService threadPool;
  
  public CordovaInterfaceImpl(Activity paramActivity)
  {
    this(paramActivity, Executors.newCachedThreadPool());
  }
  
  public CordovaInterfaceImpl(Activity paramActivity, ExecutorService paramExecutorService)
  {
    this.activity = paramActivity;
    this.threadPool = paramExecutorService;
  }
  
  public Activity getActivity()
  {
    return this.activity;
  }
  
  public ExecutorService getThreadPool()
  {
    return this.threadPool;
  }
  
  public boolean hasPermission(String paramString)
  {
    return (Build.VERSION.SDK_INT < 23) || (this.activity.checkSelfPermission(paramString) == 0);
  }
  
  public boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    CordovaPlugin localCordovaPlugin = this.activityResultCallback;
    Object localObject = localCordovaPlugin;
    if (localCordovaPlugin == null)
    {
      localObject = localCordovaPlugin;
      if (this.initCallbackService != null)
      {
        this.savedResult = new ActivityResultHolder(paramInt1, paramInt2, paramIntent);
        localObject = localCordovaPlugin;
        if (this.pluginManager != null)
        {
          localCordovaPlugin = this.pluginManager.getPlugin(this.initCallbackService);
          localObject = localCordovaPlugin;
          if (localCordovaPlugin != null)
          {
            localCordovaPlugin.onRestoreStateForActivityResult(this.savedPluginState.getBundle(localCordovaPlugin.getServiceName()), new ResumeCallback(localCordovaPlugin.getServiceName(), this.pluginManager));
            localObject = localCordovaPlugin;
          }
        }
      }
    }
    this.activityResultCallback = null;
    if (localObject != null)
    {
      Log.d("CordovaInterfaceImpl", "Sending activity result to plugin");
      this.initCallbackService = null;
      this.savedResult = null;
      ((CordovaPlugin)localObject).onActivityResult(paramInt1, paramInt2, paramIntent);
      return true;
    }
    localObject = new StringBuilder("Got an activity result, but no plugin was registered to receive it");
    if (this.savedResult != null) {}
    for (paramIntent = " yet!";; paramIntent = ".")
    {
      Log.w("CordovaInterfaceImpl", paramIntent);
      return false;
    }
  }
  
  public void onCordovaInit(PluginManager paramPluginManager)
  {
    this.pluginManager = paramPluginManager;
    if (this.savedResult != null) {}
    do
    {
      onActivityResult(this.savedResult.requestCode, this.savedResult.resultCode, this.savedResult.intent);
      do
      {
        return;
      } while (!this.activityWasDestroyed);
      this.activityWasDestroyed = false;
      paramPluginManager = (CoreAndroid)paramPluginManager.getPlugin("CoreAndroid");
    } while (paramPluginManager == null);
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("action", "resume");
      paramPluginManager.sendResumeEvent(new PluginResult(PluginResult.Status.OK, localJSONObject));
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        LOG.e("CordovaInterfaceImpl", "Failed to create event message", localJSONException);
      }
    }
  }
  
  public Object onMessage(String paramString, Object paramObject)
  {
    if ("exit".equals(paramString)) {
      this.activity.finish();
    }
    return null;
  }
  
  public void onRequestPermissionResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
    throws JSONException
  {
    if (this.permissionResultCallback != null)
    {
      this.permissionResultCallback.onRequestPermissionResult(paramInt, paramArrayOfString, paramArrayOfInt);
      this.permissionResultCallback = null;
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (this.activityResultCallback != null) {
      paramBundle.putString("callbackService", this.activityResultCallback.getServiceName());
    }
    paramBundle.putBundle("plugin", this.pluginManager.onSaveInstanceState());
  }
  
  public void requestPermission(CordovaPlugin paramCordovaPlugin, int paramInt, String paramString)
  {
    this.permissionResultCallback = paramCordovaPlugin;
    getActivity().requestPermissions(new String[] { paramString }, paramInt);
  }
  
  public void requestPermissions(CordovaPlugin paramCordovaPlugin, int paramInt, String[] paramArrayOfString)
  {
    this.permissionResultCallback = paramCordovaPlugin;
    getActivity().requestPermissions(paramArrayOfString, paramInt);
  }
  
  public void restoreInstanceState(Bundle paramBundle)
  {
    this.initCallbackService = paramBundle.getString("callbackService");
    this.savedPluginState = paramBundle.getBundle("plugin");
    this.activityWasDestroyed = true;
  }
  
  public void setActivityResultCallback(CordovaPlugin paramCordovaPlugin)
  {
    if (this.activityResultCallback != null) {
      this.activityResultCallback.onActivityResult(this.activityResultRequestCode, 0, null);
    }
    this.activityResultCallback = paramCordovaPlugin;
  }
  
  public void setActivityResultRequestCode(int paramInt)
  {
    this.activityResultRequestCode = paramInt;
  }
  
  public void startActivityForResult(CordovaPlugin paramCordovaPlugin, Intent paramIntent, int paramInt)
  {
    setActivityResultCallback(paramCordovaPlugin);
    try
    {
      this.activity.startActivityForResult(paramIntent, paramInt);
      return;
    }
    catch (RuntimeException paramCordovaPlugin)
    {
      this.activityResultCallback = null;
      throw paramCordovaPlugin;
    }
  }
  
  private static class ActivityResultHolder
  {
    private Intent intent;
    private int requestCode;
    private int resultCode;
    
    public ActivityResultHolder(int paramInt1, int paramInt2, Intent paramIntent)
    {
      this.requestCode = paramInt1;
      this.resultCode = paramInt2;
      this.intent = paramIntent;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\CordovaInterfaceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */