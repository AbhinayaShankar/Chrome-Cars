package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

class q
  extends BroadcastReceiver
{
  static final String rg = q.class.getName();
  private final af rh;
  
  q(af paramaf)
  {
    this.rh = paramaf;
  }
  
  public static void p(Context paramContext)
  {
    Intent localIntent = new Intent("com.google.analytics.RADIO_POWERED");
    localIntent.addCategory(paramContext.getPackageName());
    localIntent.putExtra(rg, true);
    paramContext.sendBroadcast(localIntent);
  }
  
  public void o(Context paramContext)
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    paramContext.registerReceiver(this, localIntentFilter);
    localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.google.analytics.RADIO_POWERED");
    localIntentFilter.addCategory(paramContext.getPackageName());
    paramContext.registerReceiver(this, localIntentFilter);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    boolean bool1 = false;
    paramContext = paramIntent.getAction();
    if ("android.net.conn.CONNECTIVITY_CHANGE".equals(paramContext))
    {
      bool2 = paramIntent.getBooleanExtra("noConnectivity", false);
      paramContext = this.rh;
      if (!bool2) {
        bool1 = true;
      }
      paramContext.q(bool1);
    }
    while ((!"com.google.analytics.RADIO_POWERED".equals(paramContext)) || (paramIntent.hasExtra(rg)))
    {
      boolean bool2;
      return;
    }
    this.rh.bF();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */