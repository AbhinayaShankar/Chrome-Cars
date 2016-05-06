package twitter4j;

import java.io.Serializable;

public abstract interface OEmbed
  extends Serializable, TwitterResponse
{
  public abstract String getAuthorName();
  
  public abstract String getAuthorURL();
  
  public abstract long getCacheAge();
  
  public abstract String getHtml();
  
  public abstract String getURL();
  
  public abstract String getVersion();
  
  public abstract int getWidth();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\OEmbed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */