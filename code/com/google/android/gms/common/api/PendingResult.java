package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;

public abstract interface PendingResult<R extends Result>
{
  public abstract R await();
  
  public abstract R await(long paramLong, TimeUnit paramTimeUnit);
  
  public abstract void setResultCallback(ResultCallback<R> paramResultCallback);
  
  public abstract void setResultCallback(ResultCallback<R> paramResultCallback, long paramLong, TimeUnit paramTimeUnit);
  
  public static abstract interface a
  {
    public abstract void k(Status paramStatus);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\api\PendingResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */