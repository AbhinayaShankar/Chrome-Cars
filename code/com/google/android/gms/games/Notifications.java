package com.google.android.gms.games;

import com.google.android.gms.common.api.GoogleApiClient;

public abstract interface Notifications
{
  public static final int NOTIFICATION_TYPES_ALL = 7;
  public static final int NOTIFICATION_TYPES_MULTIPLAYER = 3;
  public static final int NOTIFICATION_TYPE_INVITATION = 1;
  public static final int NOTIFICATION_TYPE_MATCH_UPDATE = 2;
  public static final int NOTIFICATION_TYPE_REQUEST = 4;
  
  public abstract void clear(GoogleApiClient paramGoogleApiClient, int paramInt);
  
  public abstract void clearAll(GoogleApiClient paramGoogleApiClient);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\Notifications.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */