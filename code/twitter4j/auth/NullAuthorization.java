package twitter4j.auth;

import java.io.ObjectStreamException;
import java.io.Serializable;
import twitter4j.HttpRequest;

public class NullAuthorization
  implements Serializable, Authorization
{
  private static final NullAuthorization SINGLETON = new NullAuthorization();
  private static final long serialVersionUID = -7704668493278727510L;
  
  public static NullAuthorization getInstance()
  {
    return SINGLETON;
  }
  
  private Object readResolve()
    throws ObjectStreamException
  {
    return SINGLETON;
  }
  
  public boolean equals(Object paramObject)
  {
    return SINGLETON == paramObject;
  }
  
  public String getAuthorizationHeader(HttpRequest paramHttpRequest)
  {
    return null;
  }
  
  public boolean isEnabled()
  {
    return false;
  }
  
  public String toString()
  {
    return "NullAuthentication{SINGLETON}";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\auth\NullAuthorization.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */