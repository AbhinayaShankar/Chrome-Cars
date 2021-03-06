package twitter4j;

import java.io.Serializable;

public abstract interface AccountSettings
  extends Serializable, TwitterResponse
{
  public abstract String getLanguage();
  
  public abstract String getScreenName();
  
  public abstract String getSleepEndTime();
  
  public abstract String getSleepStartTime();
  
  public abstract TimeZone getTimeZone();
  
  public abstract Location[] getTrendLocations();
  
  public abstract boolean isAlwaysUseHttps();
  
  public abstract boolean isDiscoverableByEmail();
  
  public abstract boolean isGeoEnabled();
  
  public abstract boolean isSleepTimeEnabled();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\AccountSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */