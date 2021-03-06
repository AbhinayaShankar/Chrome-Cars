package twitter4j.api;

import java.io.File;
import twitter4j.IDs;
import twitter4j.OEmbed;
import twitter4j.OEmbedRequest;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;
import twitter4j.UploadedMedia;

public abstract interface TweetsResources
{
  public abstract Status destroyStatus(long paramLong)
    throws TwitterException;
  
  public abstract OEmbed getOEmbed(OEmbedRequest paramOEmbedRequest)
    throws TwitterException;
  
  public abstract IDs getRetweeterIds(long paramLong1, int paramInt, long paramLong2)
    throws TwitterException;
  
  public abstract IDs getRetweeterIds(long paramLong1, long paramLong2)
    throws TwitterException;
  
  public abstract ResponseList<Status> getRetweets(long paramLong)
    throws TwitterException;
  
  public abstract ResponseList<Status> lookup(long[] paramArrayOfLong)
    throws TwitterException;
  
  public abstract Status retweetStatus(long paramLong)
    throws TwitterException;
  
  public abstract Status showStatus(long paramLong)
    throws TwitterException;
  
  public abstract Status updateStatus(String paramString)
    throws TwitterException;
  
  public abstract Status updateStatus(StatusUpdate paramStatusUpdate)
    throws TwitterException;
  
  public abstract UploadedMedia uploadMedia(File paramFile)
    throws TwitterException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\api\TweetsResources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */