package twitter4j.api;

import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.UserList;

public abstract interface ListsResources
{
  public abstract UserList createUserList(String paramString1, boolean paramBoolean, String paramString2)
    throws TwitterException;
  
  public abstract UserList createUserListMember(long paramLong1, long paramLong2)
    throws TwitterException;
  
  public abstract UserList createUserListMember(long paramLong1, String paramString, long paramLong2)
    throws TwitterException;
  
  public abstract UserList createUserListMember(String paramString1, String paramString2, long paramLong)
    throws TwitterException;
  
  public abstract UserList createUserListMembers(long paramLong, String paramString, long[] paramArrayOfLong)
    throws TwitterException;
  
  public abstract UserList createUserListMembers(long paramLong, String paramString, String[] paramArrayOfString)
    throws TwitterException;
  
  public abstract UserList createUserListMembers(long paramLong, long[] paramArrayOfLong)
    throws TwitterException;
  
  public abstract UserList createUserListMembers(long paramLong, String[] paramArrayOfString)
    throws TwitterException;
  
  public abstract UserList createUserListMembers(String paramString1, String paramString2, long[] paramArrayOfLong)
    throws TwitterException;
  
  public abstract UserList createUserListMembers(String paramString1, String paramString2, String[] paramArrayOfString)
    throws TwitterException;
  
  public abstract UserList createUserListSubscription(long paramLong)
    throws TwitterException;
  
  public abstract UserList createUserListSubscription(long paramLong, String paramString)
    throws TwitterException;
  
  public abstract UserList createUserListSubscription(String paramString1, String paramString2)
    throws TwitterException;
  
  public abstract UserList destroyUserList(long paramLong)
    throws TwitterException;
  
  public abstract UserList destroyUserList(long paramLong, String paramString)
    throws TwitterException;
  
  public abstract UserList destroyUserList(String paramString1, String paramString2)
    throws TwitterException;
  
  public abstract UserList destroyUserListMember(long paramLong1, long paramLong2)
    throws TwitterException;
  
  public abstract UserList destroyUserListMember(long paramLong, String paramString)
    throws TwitterException;
  
  public abstract UserList destroyUserListMember(long paramLong1, String paramString, long paramLong2)
    throws TwitterException;
  
  public abstract UserList destroyUserListMember(String paramString1, String paramString2, long paramLong)
    throws TwitterException;
  
  public abstract UserList destroyUserListMembers(long paramLong, long[] paramArrayOfLong)
    throws TwitterException;
  
  public abstract UserList destroyUserListMembers(long paramLong, String[] paramArrayOfString)
    throws TwitterException;
  
  public abstract UserList destroyUserListMembers(String paramString1, String paramString2, String[] paramArrayOfString)
    throws TwitterException;
  
  public abstract UserList destroyUserListSubscription(long paramLong)
    throws TwitterException;
  
  public abstract UserList destroyUserListSubscription(long paramLong, String paramString)
    throws TwitterException;
  
  public abstract UserList destroyUserListSubscription(String paramString1, String paramString2)
    throws TwitterException;
  
  public abstract PagableResponseList<User> getUserListMembers(long paramLong1, long paramLong2)
    throws TwitterException;
  
  public abstract PagableResponseList<User> getUserListMembers(long paramLong1, String paramString, long paramLong2)
    throws TwitterException;
  
  public abstract PagableResponseList<User> getUserListMembers(String paramString1, String paramString2, long paramLong)
    throws TwitterException;
  
  public abstract PagableResponseList<UserList> getUserListMemberships(long paramLong)
    throws TwitterException;
  
  public abstract PagableResponseList<UserList> getUserListMemberships(long paramLong1, long paramLong2)
    throws TwitterException;
  
  public abstract PagableResponseList<UserList> getUserListMemberships(long paramLong1, long paramLong2, boolean paramBoolean)
    throws TwitterException;
  
  public abstract PagableResponseList<UserList> getUserListMemberships(String paramString, long paramLong)
    throws TwitterException;
  
  public abstract PagableResponseList<UserList> getUserListMemberships(String paramString, long paramLong, boolean paramBoolean)
    throws TwitterException;
  
  public abstract ResponseList<Status> getUserListStatuses(long paramLong, String paramString, Paging paramPaging)
    throws TwitterException;
  
  public abstract ResponseList<Status> getUserListStatuses(long paramLong, Paging paramPaging)
    throws TwitterException;
  
  public abstract ResponseList<Status> getUserListStatuses(String paramString1, String paramString2, Paging paramPaging)
    throws TwitterException;
  
  public abstract PagableResponseList<User> getUserListSubscribers(long paramLong1, long paramLong2)
    throws TwitterException;
  
  public abstract PagableResponseList<User> getUserListSubscribers(long paramLong1, String paramString, long paramLong2)
    throws TwitterException;
  
  public abstract PagableResponseList<User> getUserListSubscribers(String paramString1, String paramString2, long paramLong)
    throws TwitterException;
  
  public abstract PagableResponseList<UserList> getUserListSubscriptions(String paramString, long paramLong)
    throws TwitterException;
  
  public abstract ResponseList<UserList> getUserLists(long paramLong)
    throws TwitterException;
  
  public abstract ResponseList<UserList> getUserLists(String paramString)
    throws TwitterException;
  
  public abstract PagableResponseList<UserList> getUserListsOwnerships(long paramLong1, int paramInt, long paramLong2)
    throws TwitterException;
  
  public abstract PagableResponseList<UserList> getUserListsOwnerships(String paramString, int paramInt, long paramLong)
    throws TwitterException;
  
  public abstract UserList showUserList(long paramLong)
    throws TwitterException;
  
  public abstract UserList showUserList(long paramLong, String paramString)
    throws TwitterException;
  
  public abstract UserList showUserList(String paramString1, String paramString2)
    throws TwitterException;
  
  public abstract User showUserListMembership(long paramLong1, long paramLong2)
    throws TwitterException;
  
  public abstract User showUserListMembership(long paramLong1, String paramString, long paramLong2)
    throws TwitterException;
  
  public abstract User showUserListMembership(String paramString1, String paramString2, long paramLong)
    throws TwitterException;
  
  public abstract User showUserListSubscription(long paramLong1, long paramLong2)
    throws TwitterException;
  
  public abstract User showUserListSubscription(long paramLong1, String paramString, long paramLong2)
    throws TwitterException;
  
  public abstract User showUserListSubscription(String paramString1, String paramString2, long paramLong)
    throws TwitterException;
  
  public abstract UserList updateUserList(long paramLong, String paramString1, String paramString2, boolean paramBoolean, String paramString3)
    throws TwitterException;
  
  public abstract UserList updateUserList(long paramLong, String paramString1, boolean paramBoolean, String paramString2)
    throws TwitterException;
  
  public abstract UserList updateUserList(String paramString1, String paramString2, String paramString3, boolean paramBoolean, String paramString4)
    throws TwitterException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\api\ListsResources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */