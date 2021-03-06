package twitter4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

public final class HttpClientFactory
{
  private static final Constructor HTTP_CLIENT_CONSTRUCTOR;
  private static final String HTTP_CLIENT_IMPLEMENTATION = "twitter4j.http.httpClient";
  private static final HashMap<HttpClientConfiguration, HttpClient> confClientMap;
  
  static
  {
    Object localObject3 = null;
    String str = System.getProperty("twitter4j.http.httpClient");
    Object localObject1 = localObject3;
    if (str != null) {}
    try
    {
      localObject1 = Class.forName(str);
      localObject3 = localObject1;
      if (localObject1 != null) {}
    }
    catch (ClassNotFoundException localClassNotFoundException2)
    {
      try
      {
        localObject3 = Class.forName("twitter4j.AlternativeHttpClientImpl");
        localObject1 = localObject3;
        if (localObject3 == null) {}
        try
        {
          localObject1 = Class.forName("twitter4j.HttpClientImpl");
          localClassNotFoundException2 = localClassNotFoundException2;
        }
        catch (ClassNotFoundException localClassNotFoundException1)
        {
          try
          {
            HTTP_CLIENT_CONSTRUCTOR = ((Class)localObject1).getConstructor(new Class[] { HttpClientConfiguration.class });
            confClientMap = new HashMap();
            return;
          }
          catch (NoSuchMethodException localNoSuchMethodException)
          {
            throw new AssertionError(localNoSuchMethodException);
          }
          localClassNotFoundException1 = localClassNotFoundException1;
          throw new AssertionError(localClassNotFoundException1);
        }
        localObject2 = localObject3;
      }
      catch (ClassNotFoundException localClassNotFoundException3)
      {
        for (;;)
        {
          Object localObject2;
          Object localObject4 = localObject2;
        }
      }
    }
  }
  
  public static HttpClient getInstance()
  {
    return getInstance(ConfigurationContext.getInstance().getHttpClientConfiguration());
  }
  
  public static HttpClient getInstance(HttpClientConfiguration paramHttpClientConfiguration)
  {
    HttpClient localHttpClient2 = (HttpClient)confClientMap.get(paramHttpClientConfiguration);
    HttpClient localHttpClient1 = localHttpClient2;
    if (localHttpClient2 == null) {}
    try
    {
      localHttpClient1 = (HttpClient)HTTP_CLIENT_CONSTRUCTOR.newInstance(new Object[] { paramHttpClientConfiguration });
      confClientMap.put(paramHttpClientConfiguration, localHttpClient1);
      return localHttpClient1;
    }
    catch (InstantiationException paramHttpClientConfiguration)
    {
      throw new AssertionError(paramHttpClientConfiguration);
    }
    catch (IllegalAccessException paramHttpClientConfiguration)
    {
      throw new AssertionError(paramHttpClientConfiguration);
    }
    catch (InvocationTargetException paramHttpClientConfiguration)
    {
      throw new AssertionError(paramHttpClientConfiguration);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\HttpClientFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */