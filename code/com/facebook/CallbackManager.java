package com.facebook;

import android.content.Intent;
import com.facebook.internal.CallbackManagerImpl;

public abstract interface CallbackManager
{
  public abstract boolean onActivityResult(int paramInt1, int paramInt2, Intent paramIntent);
  
  public static class Factory
  {
    public static CallbackManager create()
    {
      return new CallbackManagerImpl();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\facebook\CallbackManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */