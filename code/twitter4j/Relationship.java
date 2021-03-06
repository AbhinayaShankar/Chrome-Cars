package twitter4j;

import java.io.Serializable;

public abstract interface Relationship
  extends Serializable, TwitterResponse
{
  public abstract boolean canSourceDm();
  
  public abstract long getSourceUserId();
  
  public abstract String getSourceUserScreenName();
  
  public abstract long getTargetUserId();
  
  public abstract String getTargetUserScreenName();
  
  public abstract boolean isSourceBlockingTarget();
  
  public abstract boolean isSourceFollowedByTarget();
  
  public abstract boolean isSourceFollowingTarget();
  
  public abstract boolean isSourceMutingTarget();
  
  public abstract boolean isSourceNotificationsEnabled();
  
  public abstract boolean isSourceWantRetweets();
  
  public abstract boolean isTargetFollowedBySource();
  
  public abstract boolean isTargetFollowingSource();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\Relationship.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */