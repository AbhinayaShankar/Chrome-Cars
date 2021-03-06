package twitter4j;

import java.util.Arrays;
import twitter4j.conf.Configuration;

final class IDsJSONImpl
  extends TwitterResponseImpl
  implements IDs
{
  private static final long serialVersionUID = 6999637496007165672L;
  private long[] ids;
  private long nextCursor = -1L;
  private long previousCursor = -1L;
  
  IDsJSONImpl(String paramString)
    throws TwitterException
  {
    init(paramString);
  }
  
  IDsJSONImpl(HttpResponse paramHttpResponse, Configuration paramConfiguration)
    throws TwitterException
  {
    super(paramHttpResponse);
    paramHttpResponse = paramHttpResponse.asString();
    init(paramHttpResponse);
    if (paramConfiguration.isJSONStoreEnabled())
    {
      TwitterObjectFactory.clearThreadLocalMap();
      TwitterObjectFactory.registerJSONObject(this, paramHttpResponse);
    }
  }
  
  private void init(String paramString)
    throws TwitterException
  {
    int j;
    try
    {
      if (!paramString.startsWith("{")) {
        break label134;
      }
      paramString = new JSONObject(paramString);
      JSONArray localJSONArray = paramString.getJSONArray("ids");
      this.ids = new long[localJSONArray.length()];
      i = 0;
      for (;;)
      {
        j = localJSONArray.length();
        if (i < j) {
          try
          {
            this.ids[i] = Long.parseLong(localJSONArray.getString(i));
            i += 1;
          }
          catch (NumberFormatException localNumberFormatException1)
          {
            throw new TwitterException("Twitter API returned malformed response: " + paramString, localNumberFormatException1);
          }
        }
      }
      this.previousCursor = ParseUtil.getLong("previous_cursor", paramString);
    }
    catch (JSONException paramString)
    {
      throw new TwitterException(paramString);
    }
    this.nextCursor = ParseUtil.getLong("next_cursor", paramString);
    return;
    label134:
    paramString = new JSONArray(paramString);
    this.ids = new long[paramString.length()];
    int i = 0;
    for (;;)
    {
      j = paramString.length();
      if (i < j) {
        try
        {
          this.ids[i] = Long.parseLong(paramString.getString(i));
          i += 1;
        }
        catch (NumberFormatException localNumberFormatException2)
        {
          throw new TwitterException("Twitter API returned malformed response: " + paramString, localNumberFormatException2);
        }
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof IDs)) {
        return false;
      }
      paramObject = (IDs)paramObject;
    } while (Arrays.equals(this.ids, ((IDs)paramObject).getIDs()));
    return false;
  }
  
  public long[] getIDs()
  {
    return this.ids;
  }
  
  public long getNextCursor()
  {
    return this.nextCursor;
  }
  
  public long getPreviousCursor()
  {
    return this.previousCursor;
  }
  
  public boolean hasNext()
  {
    return 0L != this.nextCursor;
  }
  
  public boolean hasPrevious()
  {
    return 0L != this.previousCursor;
  }
  
  public int hashCode()
  {
    if (this.ids != null) {
      return Arrays.hashCode(this.ids);
    }
    return 0;
  }
  
  public String toString()
  {
    return "IDsJSONImpl{ids=" + Arrays.toString(this.ids) + ", previousCursor=" + this.previousCursor + ", nextCursor=" + this.nextCursor + '}';
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\IDsJSONImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */