package com.google.android.gms.tagmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class PreviewActivity
  extends Activity
{
  private void a(String paramString1, String paramString2, String paramString3)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    localAlertDialog.setTitle(paramString1);
    localAlertDialog.setMessage(paramString2);
    localAlertDialog.setButton(-1, paramString3, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localAlertDialog.show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    try
    {
      super.onCreate(paramBundle);
      bh.u("Preview activity");
      paramBundle = getIntent().getData();
      if (!TagManager.getInstance(this).f(paramBundle))
      {
        paramBundle = "Cannot preview the app with the uri: " + paramBundle + ". Launching current version instead.";
        bh.w(paramBundle);
        a("Preview failure", paramBundle, "Continue");
      }
      paramBundle = getPackageManager().getLaunchIntentForPackage(getPackageName());
      if (paramBundle != null)
      {
        bh.u("Invoke the launch activity for package name: " + getPackageName());
        startActivity(paramBundle);
        return;
      }
      bh.u("No launch activity found for package name: " + getPackageName());
      return;
    }
    catch (Exception paramBundle)
    {
      bh.t("Calling preview threw an exception: " + paramBundle.getMessage());
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\PreviewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */