package twitter4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import twitter4j.api.DirectMessagesResources;
import twitter4j.api.FavoritesResources;
import twitter4j.api.FriendsFollowersResources;
import twitter4j.api.HelpResources;
import twitter4j.api.HelpResources.Language;
import twitter4j.api.ListsResources;
import twitter4j.api.PlacesGeoResources;
import twitter4j.api.SavedSearchesResources;
import twitter4j.api.SearchResource;
import twitter4j.api.SpamReportingResource;
import twitter4j.api.SuggestedUsersResources;
import twitter4j.api.TimelinesResources;
import twitter4j.api.TrendsResources;
import twitter4j.api.TweetsResources;
import twitter4j.api.UsersResources;
import twitter4j.auth.Authorization;
import twitter4j.conf.Configuration;

class TwitterImpl
  extends TwitterBaseImpl
  implements Twitter
{
  private static final ConcurrentHashMap<Configuration, HttpParameter[]> implicitParamsMap = new ConcurrentHashMap();
  private static final ConcurrentHashMap<Configuration, String> implicitParamsStrMap = new ConcurrentHashMap();
  private static final long serialVersionUID = 9170943084096085770L;
  private final HttpParameter[] IMPLICIT_PARAMS;
  private final String IMPLICIT_PARAMS_STR;
  private final HttpParameter INCLUDE_MY_RETWEET;
  
  TwitterImpl(Configuration paramConfiguration, Authorization paramAuthorization)
  {
    super(paramConfiguration, paramAuthorization);
    this.INCLUDE_MY_RETWEET = new HttpParameter("include_my_retweet", paramConfiguration.isIncludeMyRetweetEnabled());
    if (implicitParamsMap.containsKey(paramConfiguration))
    {
      this.IMPLICIT_PARAMS = ((HttpParameter[])implicitParamsMap.get(paramConfiguration));
      this.IMPLICIT_PARAMS_STR = ((String)implicitParamsStrMap.get(paramConfiguration));
      return;
    }
    if (paramConfiguration.isIncludeEntitiesEnabled())
    {
      paramAuthorization = "include_entities=true";
      if (paramConfiguration.getContributingTo() == -1L) {
        break label307;
      }
    }
    label307:
    for (int i = 1;; i = 0)
    {
      Object localObject = paramAuthorization;
      if (i != 0)
      {
        localObject = paramAuthorization;
        if (!"".equals(paramAuthorization)) {
          localObject = paramAuthorization + "?";
        }
        localObject = (String)localObject + "contributingto=" + paramConfiguration.getContributingTo();
      }
      paramAuthorization = new ArrayList(3);
      if (paramConfiguration.isIncludeEntitiesEnabled()) {
        paramAuthorization.add(new HttpParameter("include_entities", "true"));
      }
      if (i != 0) {
        paramAuthorization.add(new HttpParameter("contributingto", paramConfiguration.getContributingTo()));
      }
      if (paramConfiguration.isTrimUserEnabled()) {
        paramAuthorization.add(new HttpParameter("trim_user", "1"));
      }
      paramAuthorization = (HttpParameter[])paramAuthorization.toArray(new HttpParameter[paramAuthorization.size()]);
      implicitParamsStrMap.putIfAbsent(paramConfiguration, localObject);
      implicitParamsMap.putIfAbsent(paramConfiguration, paramAuthorization);
      this.IMPLICIT_PARAMS = paramAuthorization;
      this.IMPLICIT_PARAMS_STR = ((String)localObject);
      return;
      paramAuthorization = "";
      break;
    }
  }
  
  private void addParameterToList(List<HttpParameter> paramList, String paramString1, String paramString2)
  {
    if (paramString2 != null) {
      paramList.add(new HttpParameter(paramString1, paramString2));
    }
  }
  
  private void checkFileValidity(File paramFile)
    throws TwitterException
  {
    if (!paramFile.exists()) {
      throw new TwitterException(new FileNotFoundException(paramFile + " is not found."));
    }
    if (!paramFile.isFile()) {
      throw new TwitterException(new IOException(paramFile + " is not a file."));
    }
  }
  
  private HttpResponse get(String paramString)
    throws TwitterException
  {
    ensureAuthorizationEnabled();
    String str = paramString;
    if (this.IMPLICIT_PARAMS_STR.length() > 0) {
      if (!paramString.contains("?")) {
        break label84;
      }
    }
    label84:
    for (str = paramString + "&" + this.IMPLICIT_PARAMS_STR; !this.conf.isMBeanEnabled(); str = paramString + "?" + this.IMPLICIT_PARAMS_STR) {
      return this.http.get(str, null, this.auth, this);
    }
    long l1 = System.currentTimeMillis();
    try
    {
      paramString = this.http.get(str, null, this.auth, this);
      l2 = System.currentTimeMillis();
      TwitterAPIMonitor.getInstance().methodCalled(str, l2 - l1, isOk(paramString));
      return paramString;
    }
    finally
    {
      long l2 = System.currentTimeMillis();
      TwitterAPIMonitor.getInstance().methodCalled(str, l2 - l1, isOk(null));
    }
  }
  
  private HttpResponse get(String paramString, HttpParameter[] paramArrayOfHttpParameter)
    throws TwitterException
  {
    ensureAuthorizationEnabled();
    if (!this.conf.isMBeanEnabled()) {
      return this.http.get(paramString, mergeImplicitParams(paramArrayOfHttpParameter), this.auth, this);
    }
    long l1 = System.currentTimeMillis();
    try
    {
      paramArrayOfHttpParameter = this.http.get(paramString, mergeImplicitParams(paramArrayOfHttpParameter), this.auth, this);
      l2 = System.currentTimeMillis();
      TwitterAPIMonitor.getInstance().methodCalled(paramString, l2 - l1, isOk(paramArrayOfHttpParameter));
      return paramArrayOfHttpParameter;
    }
    finally
    {
      long l2 = System.currentTimeMillis();
      TwitterAPIMonitor.getInstance().methodCalled(paramString, l2 - l1, isOk(null));
    }
  }
  
  private boolean isOk(HttpResponse paramHttpResponse)
  {
    return (paramHttpResponse != null) && (paramHttpResponse.getStatusCode() < 300);
  }
  
  private HttpParameter[] mergeImplicitParams(HttpParameter[] paramArrayOfHttpParameter)
  {
    return mergeParameters(paramArrayOfHttpParameter, this.IMPLICIT_PARAMS);
  }
  
  private HttpParameter[] mergeParameters(HttpParameter[] paramArrayOfHttpParameter, HttpParameter paramHttpParameter)
  {
    if ((paramArrayOfHttpParameter != null) && (paramHttpParameter != null))
    {
      HttpParameter[] arrayOfHttpParameter = new HttpParameter[paramArrayOfHttpParameter.length + 1];
      System.arraycopy(paramArrayOfHttpParameter, 0, arrayOfHttpParameter, 0, paramArrayOfHttpParameter.length);
      arrayOfHttpParameter[(arrayOfHttpParameter.length - 1)] = paramHttpParameter;
      return arrayOfHttpParameter;
    }
    if ((paramArrayOfHttpParameter == null) && (paramHttpParameter == null)) {
      return new HttpParameter[0];
    }
    if (paramArrayOfHttpParameter != null) {
      return paramArrayOfHttpParameter;
    }
    return new HttpParameter[] { paramHttpParameter };
  }
  
  private HttpParameter[] mergeParameters(HttpParameter[] paramArrayOfHttpParameter1, HttpParameter[] paramArrayOfHttpParameter2)
  {
    if ((paramArrayOfHttpParameter1 != null) && (paramArrayOfHttpParameter2 != null))
    {
      HttpParameter[] arrayOfHttpParameter = new HttpParameter[paramArrayOfHttpParameter1.length + paramArrayOfHttpParameter2.length];
      System.arraycopy(paramArrayOfHttpParameter1, 0, arrayOfHttpParameter, 0, paramArrayOfHttpParameter1.length);
      System.arraycopy(paramArrayOfHttpParameter2, 0, arrayOfHttpParameter, paramArrayOfHttpParameter1.length, paramArrayOfHttpParameter2.length);
      return arrayOfHttpParameter;
    }
    if ((paramArrayOfHttpParameter1 == null) && (paramArrayOfHttpParameter2 == null)) {
      return new HttpParameter[0];
    }
    if (paramArrayOfHttpParameter1 != null) {
      return paramArrayOfHttpParameter1;
    }
    return paramArrayOfHttpParameter2;
  }
  
  private HttpResponse post(String paramString)
    throws TwitterException
  {
    ensureAuthorizationEnabled();
    if (!this.conf.isMBeanEnabled()) {
      return this.http.post(paramString, this.IMPLICIT_PARAMS, this.auth, this);
    }
    long l1 = System.currentTimeMillis();
    try
    {
      HttpResponse localHttpResponse = this.http.post(paramString, this.IMPLICIT_PARAMS, this.auth, this);
      l2 = System.currentTimeMillis();
      TwitterAPIMonitor.getInstance().methodCalled(paramString, l2 - l1, isOk(localHttpResponse));
      return localHttpResponse;
    }
    finally
    {
      long l2 = System.currentTimeMillis();
      TwitterAPIMonitor.getInstance().methodCalled(paramString, l2 - l1, isOk(null));
    }
  }
  
  private HttpResponse post(String paramString, HttpParameter[] paramArrayOfHttpParameter)
    throws TwitterException
  {
    ensureAuthorizationEnabled();
    if (!this.conf.isMBeanEnabled()) {
      return this.http.post(paramString, mergeImplicitParams(paramArrayOfHttpParameter), this.auth, this);
    }
    long l1 = System.currentTimeMillis();
    try
    {
      paramArrayOfHttpParameter = this.http.post(paramString, mergeImplicitParams(paramArrayOfHttpParameter), this.auth, this);
      l2 = System.currentTimeMillis();
      TwitterAPIMonitor.getInstance().methodCalled(paramString, l2 - l1, isOk(paramArrayOfHttpParameter));
      return paramArrayOfHttpParameter;
    }
    finally
    {
      long l2 = System.currentTimeMillis();
      TwitterAPIMonitor.getInstance().methodCalled(paramString, l2 - l1, isOk(null));
    }
  }
  
  private UserList updateUserList(String paramString1, boolean paramBoolean, String paramString2, HttpParameter... paramVarArgs)
    throws TwitterException
  {
    ArrayList localArrayList = new ArrayList();
    Collections.addAll(localArrayList, paramVarArgs);
    if (paramString1 != null) {
      localArrayList.add(new HttpParameter("name", paramString1));
    }
    if (paramBoolean) {}
    for (paramString1 = "public";; paramString1 = "private")
    {
      localArrayList.add(new HttpParameter("mode", paramString1));
      if (paramString2 != null) {
        localArrayList.add(new HttpParameter("description", paramString2));
      }
      return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/update.json", (HttpParameter[])localArrayList.toArray(new HttpParameter[localArrayList.size()])));
    }
  }
  
  public User createBlock(long paramLong)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "blocks/create.json?user_id=" + paramLong));
  }
  
  public User createBlock(String paramString)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "blocks/create.json?screen_name=" + paramString));
  }
  
  public Status createFavorite(long paramLong)
    throws TwitterException
  {
    return this.factory.createStatus(post(this.conf.getRestBaseURL() + "favorites/create.json?id=" + paramLong));
  }
  
  public User createFriendship(long paramLong)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "friendships/create.json?user_id=" + paramLong));
  }
  
  public User createFriendship(long paramLong, boolean paramBoolean)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "friendships/create.json?user_id=" + paramLong + "&follow=" + paramBoolean));
  }
  
  public User createFriendship(String paramString)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "friendships/create.json?screen_name=" + paramString));
  }
  
  public User createFriendship(String paramString, boolean paramBoolean)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "friendships/create.json?screen_name=" + paramString + "&follow=" + paramBoolean));
  }
  
  public User createMute(long paramLong)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "mutes/users/create.json?user_id=" + paramLong));
  }
  
  public User createMute(String paramString)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "mutes/users/create.json?screen_name=" + paramString));
  }
  
  public SavedSearch createSavedSearch(String paramString)
    throws TwitterException
  {
    return this.factory.createSavedSearch(post(this.conf.getRestBaseURL() + "saved_searches/create.json", new HttpParameter[] { new HttpParameter("query", paramString) }));
  }
  
  public UserList createUserList(String paramString1, boolean paramBoolean, String paramString2)
    throws TwitterException
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new HttpParameter("name", paramString1));
    if (paramBoolean) {}
    for (paramString1 = "public";; paramString1 = "private")
    {
      localArrayList.add(new HttpParameter("mode", paramString1));
      if (paramString2 != null) {
        localArrayList.add(new HttpParameter("description", paramString2));
      }
      return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/create.json", (HttpParameter[])localArrayList.toArray(new HttpParameter[localArrayList.size()])));
    }
  }
  
  public UserList createUserListMember(long paramLong1, long paramLong2)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create.json", new HttpParameter[] { new HttpParameter("user_id", paramLong2), new HttpParameter("list_id", paramLong1) }));
  }
  
  public UserList createUserListMember(long paramLong1, String paramString, long paramLong2)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create.json", new HttpParameter[] { new HttpParameter("user_id", paramLong2), new HttpParameter("owner_id", paramLong1), new HttpParameter("slug", paramString) }));
  }
  
  public UserList createUserListMember(String paramString1, String paramString2, long paramLong)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create.json", new HttpParameter[] { new HttpParameter("user_id", paramLong), new HttpParameter("owner_screen_name", paramString1), new HttpParameter("slug", paramString2) }));
  }
  
  public UserList createUserListMembers(long paramLong, String paramString, long[] paramArrayOfLong)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create_all.json", new HttpParameter[] { new HttpParameter("owner_id", paramLong), new HttpParameter("slug", paramString), new HttpParameter("user_id", StringUtil.join(paramArrayOfLong)) }));
  }
  
  public UserList createUserListMembers(long paramLong, String paramString, String[] paramArrayOfString)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create_all.json", new HttpParameter[] { new HttpParameter("owner_id", paramLong), new HttpParameter("slug", paramString), new HttpParameter("screen_name", StringUtil.join(paramArrayOfString)) }));
  }
  
  public UserList createUserListMembers(long paramLong, long[] paramArrayOfLong)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create_all.json", new HttpParameter[] { new HttpParameter("list_id", paramLong), new HttpParameter("user_id", StringUtil.join(paramArrayOfLong)) }));
  }
  
  public UserList createUserListMembers(long paramLong, String[] paramArrayOfString)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create_all.json", new HttpParameter[] { new HttpParameter("list_id", paramLong), new HttpParameter("screen_name", StringUtil.join(paramArrayOfString)) }));
  }
  
  public UserList createUserListMembers(String paramString1, String paramString2, long[] paramArrayOfLong)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create_all.json", new HttpParameter[] { new HttpParameter("owner_screen_name", paramString1), new HttpParameter("slug", paramString2), new HttpParameter("user_id", StringUtil.join(paramArrayOfLong)) }));
  }
  
  public UserList createUserListMembers(String paramString1, String paramString2, String[] paramArrayOfString)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create_all.json", new HttpParameter[] { new HttpParameter("owner_screen_name", paramString1), new HttpParameter("slug", paramString2), new HttpParameter("screen_name", StringUtil.join(paramArrayOfString)) }));
  }
  
  public UserList createUserListSubscription(long paramLong)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/subscribers/create.json", new HttpParameter[] { new HttpParameter("list_id", paramLong) }));
  }
  
  public UserList createUserListSubscription(long paramLong, String paramString)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/subscribers/create.json", new HttpParameter[] { new HttpParameter("owner_id", paramLong), new HttpParameter("slug", paramString) }));
  }
  
  public UserList createUserListSubscription(String paramString1, String paramString2)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/subscribers/create.json", new HttpParameter[] { new HttpParameter("owner_screen_name", paramString1), new HttpParameter("slug", paramString2) }));
  }
  
  public User destroyBlock(long paramLong)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "blocks/destroy.json?user_id=" + paramLong));
  }
  
  public User destroyBlock(String paramString)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "blocks/destroy.json?screen_name=" + paramString));
  }
  
  public DirectMessage destroyDirectMessage(long paramLong)
    throws TwitterException
  {
    return this.factory.createDirectMessage(post(this.conf.getRestBaseURL() + "direct_messages/destroy.json?id=" + paramLong));
  }
  
  public Status destroyFavorite(long paramLong)
    throws TwitterException
  {
    return this.factory.createStatus(post(this.conf.getRestBaseURL() + "favorites/destroy.json?id=" + paramLong));
  }
  
  public User destroyFriendship(long paramLong)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "friendships/destroy.json?user_id=" + paramLong));
  }
  
  public User destroyFriendship(String paramString)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "friendships/destroy.json?screen_name=" + paramString));
  }
  
  public User destroyMute(long paramLong)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "mutes/users/destroy.json?user_id=" + paramLong));
  }
  
  public User destroyMute(String paramString)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "mutes/users/destroy.json?screen_name=" + paramString));
  }
  
  public SavedSearch destroySavedSearch(int paramInt)
    throws TwitterException
  {
    return this.factory.createSavedSearch(post(this.conf.getRestBaseURL() + "saved_searches/destroy/" + paramInt + ".json"));
  }
  
  public Status destroyStatus(long paramLong)
    throws TwitterException
  {
    return this.factory.createStatus(post(this.conf.getRestBaseURL() + "statuses/destroy/" + paramLong + ".json"));
  }
  
  public UserList destroyUserList(long paramLong)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/destroy.json", new HttpParameter[] { new HttpParameter("list_id", paramLong) }));
  }
  
  public UserList destroyUserList(long paramLong, String paramString)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/destroy.json", new HttpParameter[] { new HttpParameter("owner_id", paramLong), new HttpParameter("slug", paramString) }));
  }
  
  public UserList destroyUserList(String paramString1, String paramString2)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/destroy.json", new HttpParameter[] { new HttpParameter("owner_screen_name", paramString1), new HttpParameter("slug", paramString2) }));
  }
  
  public UserList destroyUserListMember(long paramLong1, long paramLong2)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy.json", new HttpParameter[] { new HttpParameter("list_id", paramLong1), new HttpParameter("user_id", paramLong2) }));
  }
  
  public UserList destroyUserListMember(long paramLong, String paramString)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy.json", new HttpParameter[] { new HttpParameter("list_id", paramLong), new HttpParameter("screen_name", paramString) }));
  }
  
  public UserList destroyUserListMember(long paramLong1, String paramString, long paramLong2)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy.json", new HttpParameter[] { new HttpParameter("owner_id", paramLong1), new HttpParameter("slug", paramString), new HttpParameter("user_id", paramLong2) }));
  }
  
  public UserList destroyUserListMember(String paramString1, String paramString2, long paramLong)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy.json", new HttpParameter[] { new HttpParameter("owner_screen_name", paramString1), new HttpParameter("slug", paramString2), new HttpParameter("user_id", paramLong) }));
  }
  
  public UserList destroyUserListMembers(long paramLong, long[] paramArrayOfLong)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy_all.json", new HttpParameter[] { new HttpParameter("list_id", paramLong), new HttpParameter("user_id", StringUtil.join(paramArrayOfLong)) }));
  }
  
  public UserList destroyUserListMembers(long paramLong, String[] paramArrayOfString)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy_all.json", new HttpParameter[] { new HttpParameter("list_id", paramLong), new HttpParameter("screen_name", StringUtil.join(paramArrayOfString)) }));
  }
  
  public UserList destroyUserListMembers(String paramString1, String paramString2, String[] paramArrayOfString)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy_all.json", new HttpParameter[] { new HttpParameter("owner_screen_name", paramString1), new HttpParameter("slug", paramString2), new HttpParameter("screen_name", StringUtil.join(paramArrayOfString)) }));
  }
  
  public UserList destroyUserListSubscription(long paramLong)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/subscribers/destroy.json", new HttpParameter[] { new HttpParameter("list_id", paramLong) }));
  }
  
  public UserList destroyUserListSubscription(long paramLong, String paramString)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/subscribers/destroy.json", new HttpParameter[] { new HttpParameter("owner_id", paramLong), new HttpParameter("slug", paramString) }));
  }
  
  public UserList destroyUserListSubscription(String paramString1, String paramString2)
    throws TwitterException
  {
    return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/subscribers/destroy.json", new HttpParameter[] { new HttpParameter("owner_screen_name", paramString1), new HttpParameter("slug", paramString2) }));
  }
  
  public DirectMessagesResources directMessages()
  {
    return this;
  }
  
  public FavoritesResources favorites()
  {
    return this;
  }
  
  public FriendsFollowersResources friendsFollowers()
  {
    return this;
  }
  
  public TwitterAPIConfiguration getAPIConfiguration()
    throws TwitterException
  {
    return this.factory.createTwitterAPIConfiguration(get(this.conf.getRestBaseURL() + "help/configuration.json"));
  }
  
  public AccountSettings getAccountSettings()
    throws TwitterException
  {
    return this.factory.createAccountSettings(get(this.conf.getRestBaseURL() + "account/settings.json"));
  }
  
  public ResponseList<Location> getAvailableTrends()
    throws TwitterException
  {
    return this.factory.createLocationList(get(this.conf.getRestBaseURL() + "trends/available.json"));
  }
  
  public IDs getBlocksIDs()
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "blocks/ids.json"));
  }
  
  public IDs getBlocksIDs(long paramLong)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "blocks/ids.json?cursor=" + paramLong));
  }
  
  public PagableResponseList<User> getBlocksList()
    throws TwitterException
  {
    return getBlocksList(-1L);
  }
  
  public PagableResponseList<User> getBlocksList(long paramLong)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "blocks/list.json?cursor=" + paramLong));
  }
  
  public ResponseList<Location> getClosestTrends(GeoLocation paramGeoLocation)
    throws TwitterException
  {
    return this.factory.createLocationList(get(this.conf.getRestBaseURL() + "trends/closest.json", new HttpParameter[] { new HttpParameter("lat", paramGeoLocation.getLatitude()), new HttpParameter("long", paramGeoLocation.getLongitude()) }));
  }
  
  public ResponseList<User> getContributees(long paramLong)
    throws TwitterException
  {
    return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/contributees.json?user_id=" + paramLong));
  }
  
  public ResponseList<User> getContributees(String paramString)
    throws TwitterException
  {
    return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/contributees.json?screen_name=" + paramString));
  }
  
  public ResponseList<User> getContributors(long paramLong)
    throws TwitterException
  {
    return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/contributors.json?user_id=" + paramLong));
  }
  
  public ResponseList<User> getContributors(String paramString)
    throws TwitterException
  {
    return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/contributors.json?screen_name=" + paramString));
  }
  
  public InputStream getDMImageAsStream(String paramString)
    throws TwitterException
  {
    return get(paramString).asStream();
  }
  
  public ResponseList<DirectMessage> getDirectMessages()
    throws TwitterException
  {
    return this.factory.createDirectMessageList(get(this.conf.getRestBaseURL() + "direct_messages.json"));
  }
  
  public ResponseList<DirectMessage> getDirectMessages(Paging paramPaging)
    throws TwitterException
  {
    return this.factory.createDirectMessageList(get(this.conf.getRestBaseURL() + "direct_messages.json", paramPaging.asPostParameterArray()));
  }
  
  public ResponseList<Status> getFavorites()
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "favorites/list.json"));
  }
  
  public ResponseList<Status> getFavorites(long paramLong)
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "favorites/list.json?user_id=" + paramLong));
  }
  
  public ResponseList<Status> getFavorites(long paramLong, Paging paramPaging)
    throws TwitterException
  {
    ObjectFactory localObjectFactory = this.factory;
    String str = this.conf.getRestBaseURL() + "favorites/list.json";
    HttpParameter localHttpParameter = new HttpParameter("user_id", paramLong);
    paramPaging = paramPaging.asPostParameterArray();
    return localObjectFactory.createStatusList(get(str, mergeParameters(new HttpParameter[] { localHttpParameter }, paramPaging)));
  }
  
  public ResponseList<Status> getFavorites(String paramString)
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "favorites/list.json?screen_name=" + paramString));
  }
  
  public ResponseList<Status> getFavorites(String paramString, Paging paramPaging)
    throws TwitterException
  {
    ObjectFactory localObjectFactory = this.factory;
    String str = this.conf.getRestBaseURL() + "favorites/list.json";
    paramString = new HttpParameter("screen_name", paramString);
    paramPaging = paramPaging.asPostParameterArray();
    return localObjectFactory.createStatusList(get(str, mergeParameters(new HttpParameter[] { paramString }, paramPaging)));
  }
  
  public ResponseList<Status> getFavorites(Paging paramPaging)
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "favorites/list.json", paramPaging.asPostParameterArray()));
  }
  
  public IDs getFollowersIDs(long paramLong)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "followers/ids.json?cursor=" + paramLong));
  }
  
  public IDs getFollowersIDs(long paramLong1, long paramLong2)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "followers/ids.json?user_id=" + paramLong1 + "&cursor=" + paramLong2));
  }
  
  public IDs getFollowersIDs(long paramLong1, long paramLong2, int paramInt)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "followers/ids.json?user_id=" + paramLong1 + "&cursor=" + paramLong2 + "&count=" + paramInt));
  }
  
  public IDs getFollowersIDs(String paramString, long paramLong)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "followers/ids.json?screen_name=" + paramString + "&cursor=" + paramLong));
  }
  
  public IDs getFollowersIDs(String paramString, long paramLong, int paramInt)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "followers/ids.json?screen_name=" + paramString + "&cursor=" + paramLong + "&count=" + paramInt));
  }
  
  public PagableResponseList<User> getFollowersList(long paramLong1, long paramLong2)
    throws TwitterException
  {
    return getFollowersList(paramLong1, paramLong2, 20);
  }
  
  public PagableResponseList<User> getFollowersList(long paramLong1, long paramLong2, int paramInt)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "followers/list.json?user_id=" + paramLong1 + "&cursor=" + paramLong2 + "&count=" + paramInt));
  }
  
  public PagableResponseList<User> getFollowersList(long paramLong1, long paramLong2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "followers/list.json?user_id=" + paramLong1 + "&cursor=" + paramLong2 + "&count=" + paramInt + "&skip_status=" + paramBoolean1 + "&include_user_entities=" + paramBoolean2));
  }
  
  public PagableResponseList<User> getFollowersList(String paramString, long paramLong)
    throws TwitterException
  {
    return getFollowersList(paramString, paramLong, 20);
  }
  
  public PagableResponseList<User> getFollowersList(String paramString, long paramLong, int paramInt)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "followers/list.json?screen_name=" + paramString + "&cursor=" + paramLong + "&count=" + paramInt));
  }
  
  public PagableResponseList<User> getFollowersList(String paramString, long paramLong, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "followers/list.json?screen_name=" + paramString + "&cursor=" + paramLong + "&count=" + paramInt + "&skip_status=" + paramBoolean1 + "&include_user_entities=" + paramBoolean2));
  }
  
  public IDs getFriendsIDs(long paramLong)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friends/ids.json?cursor=" + paramLong));
  }
  
  public IDs getFriendsIDs(long paramLong1, long paramLong2)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friends/ids.json?user_id=" + paramLong1 + "&cursor=" + paramLong2));
  }
  
  public IDs getFriendsIDs(long paramLong1, long paramLong2, int paramInt)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friends/ids.json?user_id=" + paramLong1 + "&cursor=" + paramLong2 + "&count=" + paramInt));
  }
  
  public IDs getFriendsIDs(String paramString, long paramLong)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friends/ids.json?screen_name=" + paramString + "&cursor=" + paramLong));
  }
  
  public IDs getFriendsIDs(String paramString, long paramLong, int paramInt)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friends/ids.json?screen_name=" + paramString + "&cursor=" + paramLong + "&count=" + paramInt));
  }
  
  public PagableResponseList<User> getFriendsList(long paramLong1, long paramLong2)
    throws TwitterException
  {
    return getFriendsList(paramLong1, paramLong2, 20);
  }
  
  public PagableResponseList<User> getFriendsList(long paramLong1, long paramLong2, int paramInt)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "friends/list.json?user_id=" + paramLong1 + "&cursor=" + paramLong2 + "&count=" + paramInt));
  }
  
  public PagableResponseList<User> getFriendsList(long paramLong1, long paramLong2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "friends/list.json?user_id=" + paramLong1 + "&cursor=" + paramLong2 + "&count=" + paramInt + "&skip_status=" + paramBoolean1 + "&include_user_entities=" + paramBoolean2));
  }
  
  public PagableResponseList<User> getFriendsList(String paramString, long paramLong)
    throws TwitterException
  {
    return getFriendsList(paramString, paramLong, 20);
  }
  
  public PagableResponseList<User> getFriendsList(String paramString, long paramLong, int paramInt)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "friends/list.json?screen_name=" + paramString + "&cursor=" + paramLong + "&count=" + paramInt));
  }
  
  public PagableResponseList<User> getFriendsList(String paramString, long paramLong, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "friends/list.json?screen_name=" + paramString + "&cursor=" + paramLong + "&count=" + paramInt + "&skip_status=" + paramBoolean1 + "&include_user_entities=" + paramBoolean2));
  }
  
  public Place getGeoDetails(String paramString)
    throws TwitterException
  {
    return this.factory.createPlace(get(this.conf.getRestBaseURL() + "geo/id/" + paramString + ".json"));
  }
  
  public ResponseList<Status> getHomeTimeline()
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/home_timeline.json", new HttpParameter[] { this.INCLUDE_MY_RETWEET }));
  }
  
  public ResponseList<Status> getHomeTimeline(Paging paramPaging)
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/home_timeline.json", mergeParameters(paramPaging.asPostParameterArray(), new HttpParameter[] { this.INCLUDE_MY_RETWEET })));
  }
  
  public IDs getIncomingFriendships(long paramLong)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friendships/incoming.json?cursor=" + paramLong));
  }
  
  public ResponseList<HelpResources.Language> getLanguages()
    throws TwitterException
  {
    return this.factory.createLanguageList(get(this.conf.getRestBaseURL() + "help/languages.json"));
  }
  
  public ResponseList<User> getMemberSuggestions(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = get(this.conf.getRestBaseURL() + "users/suggestions/" + URLEncoder.encode(paramString, "UTF-8") + "/members.json");
      return this.factory.createUserListFromJSONArray(paramString);
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new RuntimeException(paramString);
    }
  }
  
  public ResponseList<Status> getMentionsTimeline()
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/mentions_timeline.json"));
  }
  
  public ResponseList<Status> getMentionsTimeline(Paging paramPaging)
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/mentions_timeline.json", paramPaging.asPostParameterArray()));
  }
  
  public IDs getMutesIDs(long paramLong)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "mutes/users/ids.json?cursor=" + paramLong));
  }
  
  public PagableResponseList<User> getMutesList(long paramLong)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "mutes/users/list.json?cursor=" + paramLong));
  }
  
  public IDs getNoRetweetsFriendships()
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friendships/no_retweets/ids.json"));
  }
  
  public OEmbed getOEmbed(OEmbedRequest paramOEmbedRequest)
    throws TwitterException
  {
    return this.factory.createOEmbed(get(this.conf.getRestBaseURL() + "statuses/oembed.json", paramOEmbedRequest.asHttpParameterArray()));
  }
  
  public IDs getOutgoingFriendships(long paramLong)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friendships/outgoing.json?cursor=" + paramLong));
  }
  
  public Trends getPlaceTrends(int paramInt)
    throws TwitterException
  {
    return this.factory.createTrends(get(this.conf.getRestBaseURL() + "trends/place.json?id=" + paramInt));
  }
  
  public String getPrivacyPolicy()
    throws TwitterException
  {
    try
    {
      String str = get(this.conf.getRestBaseURL() + "help/privacy.json").asJSONObject().getString("privacy");
      return str;
    }
    catch (JSONException localJSONException)
    {
      throw new TwitterException(localJSONException);
    }
  }
  
  public Map<String, RateLimitStatus> getRateLimitStatus()
    throws TwitterException
  {
    return this.factory.createRateLimitStatuses(get(this.conf.getRestBaseURL() + "application/rate_limit_status.json"));
  }
  
  public Map<String, RateLimitStatus> getRateLimitStatus(String... paramVarArgs)
    throws TwitterException
  {
    return this.factory.createRateLimitStatuses(get(this.conf.getRestBaseURL() + "application/rate_limit_status.json?resources=" + StringUtil.join(paramVarArgs)));
  }
  
  public IDs getRetweeterIds(long paramLong1, int paramInt, long paramLong2)
    throws TwitterException
  {
    return this.factory.createIDs(get(this.conf.getRestBaseURL() + "statuses/retweeters/ids.json?id=" + paramLong1 + "&cursor=" + paramLong2 + "&count=" + paramInt));
  }
  
  public IDs getRetweeterIds(long paramLong1, long paramLong2)
    throws TwitterException
  {
    return getRetweeterIds(paramLong1, 100, paramLong2);
  }
  
  public ResponseList<Status> getRetweets(long paramLong)
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/retweets/" + paramLong + ".json?count=100"));
  }
  
  public ResponseList<Status> getRetweetsOfMe()
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/retweets_of_me.json"));
  }
  
  public ResponseList<Status> getRetweetsOfMe(Paging paramPaging)
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/retweets_of_me.json", paramPaging.asPostParameterArray()));
  }
  
  public ResponseList<SavedSearch> getSavedSearches()
    throws TwitterException
  {
    return this.factory.createSavedSearchList(get(this.conf.getRestBaseURL() + "saved_searches/list.json"));
  }
  
  public ResponseList<DirectMessage> getSentDirectMessages()
    throws TwitterException
  {
    return this.factory.createDirectMessageList(get(this.conf.getRestBaseURL() + "direct_messages/sent.json"));
  }
  
  public ResponseList<DirectMessage> getSentDirectMessages(Paging paramPaging)
    throws TwitterException
  {
    return this.factory.createDirectMessageList(get(this.conf.getRestBaseURL() + "direct_messages/sent.json", paramPaging.asPostParameterArray()));
  }
  
  public ResponseList<Place> getSimilarPlaces(GeoLocation paramGeoLocation, String paramString1, String paramString2, String paramString3)
    throws TwitterException
  {
    ArrayList localArrayList = new ArrayList(3);
    localArrayList.add(new HttpParameter("lat", paramGeoLocation.getLatitude()));
    localArrayList.add(new HttpParameter("long", paramGeoLocation.getLongitude()));
    localArrayList.add(new HttpParameter("name", paramString1));
    if (paramString2 != null) {
      localArrayList.add(new HttpParameter("contained_within", paramString2));
    }
    if (paramString3 != null) {
      localArrayList.add(new HttpParameter("attribute:street_address", paramString3));
    }
    return this.factory.createPlaceList(get(this.conf.getRestBaseURL() + "geo/similar_places.json", (HttpParameter[])localArrayList.toArray(new HttpParameter[localArrayList.size()])));
  }
  
  public ResponseList<Category> getSuggestedUserCategories()
    throws TwitterException
  {
    return this.factory.createCategoryList(get(this.conf.getRestBaseURL() + "users/suggestions.json"));
  }
  
  public String getTermsOfService()
    throws TwitterException
  {
    try
    {
      String str = get(this.conf.getRestBaseURL() + "help/tos.json").asJSONObject().getString("tos");
      return str;
    }
    catch (JSONException localJSONException)
    {
      throw new TwitterException(localJSONException);
    }
  }
  
  public PagableResponseList<User> getUserListMembers(long paramLong1, long paramLong2)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "lists/members.json?list_id=" + paramLong1 + "&cursor=" + paramLong2));
  }
  
  public PagableResponseList<User> getUserListMembers(long paramLong1, String paramString, long paramLong2)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "lists/members.json?owner_id=" + paramLong1 + "&slug=" + paramString + "&cursor=" + paramLong2));
  }
  
  public PagableResponseList<User> getUserListMembers(String paramString1, String paramString2, long paramLong)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "lists/members.json?owner_screen_name=" + paramString1 + "&slug=" + paramString2 + "&cursor=" + paramLong));
  }
  
  public PagableResponseList<UserList> getUserListMemberships(long paramLong)
    throws TwitterException
  {
    return this.factory.createPagableUserListList(get(this.conf.getRestBaseURL() + "lists/memberships.json?cursor=" + paramLong));
  }
  
  public PagableResponseList<UserList> getUserListMemberships(long paramLong1, long paramLong2)
    throws TwitterException
  {
    return getUserListMemberships(paramLong1, paramLong2, false);
  }
  
  public PagableResponseList<UserList> getUserListMemberships(long paramLong1, long paramLong2, boolean paramBoolean)
    throws TwitterException
  {
    return this.factory.createPagableUserListList(get(this.conf.getRestBaseURL() + "lists/memberships.json?user_id=" + paramLong1 + "&cursor=" + paramLong2 + "&filter_to_owned_lists=" + paramBoolean));
  }
  
  public PagableResponseList<UserList> getUserListMemberships(String paramString, long paramLong)
    throws TwitterException
  {
    return getUserListMemberships(paramString, paramLong, false);
  }
  
  public PagableResponseList<UserList> getUserListMemberships(String paramString, long paramLong, boolean paramBoolean)
    throws TwitterException
  {
    return this.factory.createPagableUserListList(get(this.conf.getRestBaseURL() + "lists/memberships.json?screen_name=" + paramString + "&cursor=" + paramLong + "&filter_to_owned_lists=" + paramBoolean));
  }
  
  public ResponseList<Status> getUserListStatuses(long paramLong, String paramString, Paging paramPaging)
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "lists/statuses.json", mergeParameters(paramPaging.asPostParameterArray(Paging.SMCP, "count"), new HttpParameter[] { new HttpParameter("owner_id", paramLong), new HttpParameter("slug", paramString) })));
  }
  
  public ResponseList<Status> getUserListStatuses(long paramLong, Paging paramPaging)
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "lists/statuses.json", mergeParameters(paramPaging.asPostParameterArray(Paging.SMCP, "count"), new HttpParameter("list_id", paramLong))));
  }
  
  public ResponseList<Status> getUserListStatuses(String paramString1, String paramString2, Paging paramPaging)
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "lists/statuses.json", mergeParameters(paramPaging.asPostParameterArray(Paging.SMCP, "count"), new HttpParameter[] { new HttpParameter("owner_screen_name", paramString1), new HttpParameter("slug", paramString2) })));
  }
  
  public PagableResponseList<User> getUserListSubscribers(long paramLong1, long paramLong2)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "lists/subscribers.json?list_id=" + paramLong1 + "&cursor=" + paramLong2));
  }
  
  public PagableResponseList<User> getUserListSubscribers(long paramLong1, String paramString, long paramLong2)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "lists/subscribers.json?owner_id=" + paramLong1 + "&slug=" + paramString + "&cursor=" + paramLong2));
  }
  
  public PagableResponseList<User> getUserListSubscribers(String paramString1, String paramString2, long paramLong)
    throws TwitterException
  {
    return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "lists/subscribers.json?owner_screen_name=" + paramString1 + "&slug=" + paramString2 + "&cursor=" + paramLong));
  }
  
  public PagableResponseList<UserList> getUserListSubscriptions(String paramString, long paramLong)
    throws TwitterException
  {
    return this.factory.createPagableUserListList(get(this.conf.getRestBaseURL() + "lists/subscriptions.json?screen_name=" + paramString + "&cursor=" + paramLong));
  }
  
  public ResponseList<UserList> getUserLists(long paramLong)
    throws TwitterException
  {
    return this.factory.createUserListList(get(this.conf.getRestBaseURL() + "lists/list.json?user_id=" + paramLong));
  }
  
  public ResponseList<UserList> getUserLists(String paramString)
    throws TwitterException
  {
    return this.factory.createUserListList(get(this.conf.getRestBaseURL() + "lists/list.json?screen_name=" + paramString));
  }
  
  public PagableResponseList<UserList> getUserListsOwnerships(long paramLong1, int paramInt, long paramLong2)
    throws TwitterException
  {
    return this.factory.createPagableUserListList(get(this.conf.getRestBaseURL() + "lists/ownerships.json", new HttpParameter[] { new HttpParameter("user_id", paramLong1), new HttpParameter("count", paramInt), new HttpParameter("cursor", paramLong2) }));
  }
  
  public PagableResponseList<UserList> getUserListsOwnerships(String paramString, int paramInt, long paramLong)
    throws TwitterException
  {
    return this.factory.createPagableUserListList(get(this.conf.getRestBaseURL() + "lists/ownerships.json", new HttpParameter[] { new HttpParameter("screen_name", paramString), new HttpParameter("count", paramInt), new HttpParameter("cursor", paramLong) }));
  }
  
  public ResponseList<User> getUserSuggestions(String paramString)
    throws TwitterException
  {
    try
    {
      paramString = get(this.conf.getRestBaseURL() + "users/suggestions/" + URLEncoder.encode(paramString, "UTF-8") + ".json");
      return this.factory.createUserListFromJSONArray_Users(paramString);
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new RuntimeException(paramString);
    }
  }
  
  public ResponseList<Status> getUserTimeline()
    throws TwitterException
  {
    return getUserTimeline(new Paging());
  }
  
  public ResponseList<Status> getUserTimeline(long paramLong)
    throws TwitterException
  {
    return getUserTimeline(paramLong, new Paging());
  }
  
  public ResponseList<Status> getUserTimeline(long paramLong, Paging paramPaging)
    throws TwitterException
  {
    ObjectFactory localObjectFactory = this.factory;
    String str = this.conf.getRestBaseURL() + "statuses/user_timeline.json";
    HttpParameter localHttpParameter1 = new HttpParameter("user_id", paramLong);
    HttpParameter localHttpParameter2 = this.INCLUDE_MY_RETWEET;
    paramPaging = paramPaging.asPostParameterArray();
    return localObjectFactory.createStatusList(get(str, mergeParameters(new HttpParameter[] { localHttpParameter1, localHttpParameter2 }, paramPaging)));
  }
  
  public ResponseList<Status> getUserTimeline(String paramString)
    throws TwitterException
  {
    return getUserTimeline(paramString, new Paging());
  }
  
  public ResponseList<Status> getUserTimeline(String paramString, Paging paramPaging)
    throws TwitterException
  {
    ObjectFactory localObjectFactory = this.factory;
    String str = this.conf.getRestBaseURL() + "statuses/user_timeline.json";
    paramString = new HttpParameter("screen_name", paramString);
    HttpParameter localHttpParameter = this.INCLUDE_MY_RETWEET;
    paramPaging = paramPaging.asPostParameterArray();
    return localObjectFactory.createStatusList(get(str, mergeParameters(new HttpParameter[] { paramString, localHttpParameter }, paramPaging)));
  }
  
  public ResponseList<Status> getUserTimeline(Paging paramPaging)
    throws TwitterException
  {
    ObjectFactory localObjectFactory = this.factory;
    String str = this.conf.getRestBaseURL() + "statuses/user_timeline.json";
    HttpParameter localHttpParameter = this.INCLUDE_MY_RETWEET;
    paramPaging = paramPaging.asPostParameterArray();
    return localObjectFactory.createStatusList(get(str, mergeParameters(new HttpParameter[] { localHttpParameter }, paramPaging)));
  }
  
  public HelpResources help()
  {
    return this;
  }
  
  public ListsResources list()
  {
    return this;
  }
  
  public ResponseList<Status> lookup(long[] paramArrayOfLong)
    throws TwitterException
  {
    return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/lookup.json?id=" + StringUtil.join(paramArrayOfLong)));
  }
  
  public ResponseList<Friendship> lookupFriendships(long[] paramArrayOfLong)
    throws TwitterException
  {
    return this.factory.createFriendshipList(get(this.conf.getRestBaseURL() + "friendships/lookup.json?user_id=" + StringUtil.join(paramArrayOfLong)));
  }
  
  public ResponseList<Friendship> lookupFriendships(String[] paramArrayOfString)
    throws TwitterException
  {
    return this.factory.createFriendshipList(get(this.conf.getRestBaseURL() + "friendships/lookup.json?screen_name=" + StringUtil.join(paramArrayOfString)));
  }
  
  public ResponseList<User> lookupUsers(long[] paramArrayOfLong)
    throws TwitterException
  {
    return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/lookup.json", new HttpParameter[] { new HttpParameter("user_id", StringUtil.join(paramArrayOfLong)) }));
  }
  
  public ResponseList<User> lookupUsers(String[] paramArrayOfString)
    throws TwitterException
  {
    return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/lookup.json", new HttpParameter[] { new HttpParameter("screen_name", StringUtil.join(paramArrayOfString)) }));
  }
  
  public PlacesGeoResources placesGeo()
  {
    return this;
  }
  
  public void removeProfileBanner()
    throws TwitterException
  {
    post(this.conf.getRestBaseURL() + "account/remove_profile_banner.json");
  }
  
  public User reportSpam(long paramLong)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "users/report_spam.json?user_id=" + paramLong));
  }
  
  public User reportSpam(String paramString)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "users/report_spam.json?screen_name=" + paramString));
  }
  
  public Status retweetStatus(long paramLong)
    throws TwitterException
  {
    return this.factory.createStatus(post(this.conf.getRestBaseURL() + "statuses/retweet/" + paramLong + ".json"));
  }
  
  public ResponseList<Place> reverseGeoCode(GeoQuery paramGeoQuery)
    throws TwitterException
  {
    try
    {
      paramGeoQuery = this.factory.createPlaceList(get(this.conf.getRestBaseURL() + "geo/reverse_geocode.json", paramGeoQuery.asHttpParameterArray()));
      return paramGeoQuery;
    }
    catch (TwitterException paramGeoQuery)
    {
      if (paramGeoQuery.getStatusCode() == 404) {
        return this.factory.createEmptyResponseList();
      }
      throw paramGeoQuery;
    }
  }
  
  public SavedSearchesResources savedSearches()
  {
    return this;
  }
  
  public QueryResult search(Query paramQuery)
    throws TwitterException
  {
    if (paramQuery.nextPage() != null) {
      return this.factory.createQueryResult(get(this.conf.getRestBaseURL() + "search/tweets.json" + paramQuery.nextPage()), paramQuery);
    }
    return this.factory.createQueryResult(get(this.conf.getRestBaseURL() + "search/tweets.json", paramQuery.asHttpParameterArray()), paramQuery);
  }
  
  public SearchResource search()
  {
    return this;
  }
  
  public ResponseList<Place> searchPlaces(GeoQuery paramGeoQuery)
    throws TwitterException
  {
    return this.factory.createPlaceList(get(this.conf.getRestBaseURL() + "geo/search.json", paramGeoQuery.asHttpParameterArray()));
  }
  
  public ResponseList<User> searchUsers(String paramString, int paramInt)
    throws TwitterException
  {
    return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/search.json", new HttpParameter[] { new HttpParameter("q", paramString), new HttpParameter("per_page", 20), new HttpParameter("page", paramInt) }));
  }
  
  public DirectMessage sendDirectMessage(long paramLong, String paramString)
    throws TwitterException
  {
    return this.factory.createDirectMessage(post(this.conf.getRestBaseURL() + "direct_messages/new.json", new HttpParameter[] { new HttpParameter("user_id", paramLong), new HttpParameter("text", paramString) }));
  }
  
  public DirectMessage sendDirectMessage(String paramString1, String paramString2)
    throws TwitterException
  {
    return this.factory.createDirectMessage(post(this.conf.getRestBaseURL() + "direct_messages/new.json", new HttpParameter[] { new HttpParameter("screen_name", paramString1), new HttpParameter("text", paramString2) }));
  }
  
  public DirectMessage showDirectMessage(long paramLong)
    throws TwitterException
  {
    return this.factory.createDirectMessage(get(this.conf.getRestBaseURL() + "direct_messages/show.json?id=" + paramLong));
  }
  
  public Relationship showFriendship(long paramLong1, long paramLong2)
    throws TwitterException
  {
    return this.factory.createRelationship(get(this.conf.getRestBaseURL() + "friendships/show.json", new HttpParameter[] { new HttpParameter("source_id", paramLong1), new HttpParameter("target_id", paramLong2) }));
  }
  
  public Relationship showFriendship(String paramString1, String paramString2)
    throws TwitterException
  {
    return this.factory.createRelationship(get(this.conf.getRestBaseURL() + "friendships/show.json", HttpParameter.getParameterArray("source_screen_name", paramString1, "target_screen_name", paramString2)));
  }
  
  public SavedSearch showSavedSearch(int paramInt)
    throws TwitterException
  {
    return this.factory.createSavedSearch(get(this.conf.getRestBaseURL() + "saved_searches/show/" + paramInt + ".json"));
  }
  
  public Status showStatus(long paramLong)
    throws TwitterException
  {
    return this.factory.createStatus(get(this.conf.getRestBaseURL() + "statuses/show/" + paramLong + ".json", new HttpParameter[] { this.INCLUDE_MY_RETWEET }));
  }
  
  public User showUser(long paramLong)
    throws TwitterException
  {
    return this.factory.createUser(get(this.conf.getRestBaseURL() + "users/show.json?user_id=" + paramLong));
  }
  
  public User showUser(String paramString)
    throws TwitterException
  {
    return this.factory.createUser(get(this.conf.getRestBaseURL() + "users/show.json?screen_name=" + paramString));
  }
  
  public UserList showUserList(long paramLong)
    throws TwitterException
  {
    return this.factory.createAUserList(get(this.conf.getRestBaseURL() + "lists/show.json?list_id=" + paramLong));
  }
  
  public UserList showUserList(long paramLong, String paramString)
    throws TwitterException
  {
    return this.factory.createAUserList(get(this.conf.getRestBaseURL() + "lists/show.json?owner_id=" + paramLong + "&slug=" + paramString));
  }
  
  public UserList showUserList(String paramString1, String paramString2)
    throws TwitterException
  {
    return this.factory.createAUserList(get(this.conf.getRestBaseURL() + "lists/show.json?owner_screen_name=" + paramString1 + "&slug=" + paramString2));
  }
  
  public User showUserListMembership(long paramLong1, long paramLong2)
    throws TwitterException
  {
    return this.factory.createUser(get(this.conf.getRestBaseURL() + "lists/members/show.json?list_id=" + paramLong1 + "&user_id=" + paramLong2));
  }
  
  public User showUserListMembership(long paramLong1, String paramString, long paramLong2)
    throws TwitterException
  {
    return this.factory.createUser(get(this.conf.getRestBaseURL() + "lists/members/show.json?owner_id=" + paramLong1 + "&slug=" + paramString + "&user_id=" + paramLong2));
  }
  
  public User showUserListMembership(String paramString1, String paramString2, long paramLong)
    throws TwitterException
  {
    return this.factory.createUser(get(this.conf.getRestBaseURL() + "lists/members/show.json?owner_screen_name=" + paramString1 + "&slug=" + paramString2 + "&user_id=" + paramLong));
  }
  
  public User showUserListSubscription(long paramLong1, long paramLong2)
    throws TwitterException
  {
    return this.factory.createUser(get(this.conf.getRestBaseURL() + "lists/subscribers/show.json?list_id=" + paramLong1 + "&user_id=" + paramLong2));
  }
  
  public User showUserListSubscription(long paramLong1, String paramString, long paramLong2)
    throws TwitterException
  {
    return this.factory.createUser(get(this.conf.getRestBaseURL() + "lists/subscribers/show.json?owner_id=" + paramLong1 + "&slug=" + paramString + "&user_id=" + paramLong2));
  }
  
  public User showUserListSubscription(String paramString1, String paramString2, long paramLong)
    throws TwitterException
  {
    return this.factory.createUser(get(this.conf.getRestBaseURL() + "lists/subscribers/show.json?owner_screen_name=" + paramString1 + "&slug=" + paramString2 + "&user_id=" + paramLong));
  }
  
  public SpamReportingResource spamReporting()
  {
    return this;
  }
  
  public SuggestedUsersResources suggestedUsers()
  {
    return this;
  }
  
  public TimelinesResources timelines()
  {
    return this;
  }
  
  public String toString()
  {
    return "TwitterImpl{INCLUDE_MY_RETWEET=" + this.INCLUDE_MY_RETWEET + '}';
  }
  
  public TrendsResources trends()
  {
    return this;
  }
  
  public TweetsResources tweets()
  {
    return this;
  }
  
  public AccountSettings updateAccountSettings(Integer paramInteger, Boolean paramBoolean, String paramString1, String paramString2, String paramString3, String paramString4)
    throws TwitterException
  {
    ArrayList localArrayList = new ArrayList(6);
    if (paramInteger != null) {
      localArrayList.add(new HttpParameter("trend_location_woeid", paramInteger.intValue()));
    }
    if (paramBoolean != null) {
      localArrayList.add(new HttpParameter("sleep_time_enabled", paramBoolean.toString()));
    }
    if (paramString1 != null) {
      localArrayList.add(new HttpParameter("start_sleep_time", paramString1));
    }
    if (paramString2 != null) {
      localArrayList.add(new HttpParameter("end_sleep_time", paramString2));
    }
    if (paramString3 != null) {
      localArrayList.add(new HttpParameter("time_zone", paramString3));
    }
    if (paramString4 != null) {
      localArrayList.add(new HttpParameter("lang", paramString4));
    }
    return this.factory.createAccountSettings(post(this.conf.getRestBaseURL() + "account/settings.json", (HttpParameter[])localArrayList.toArray(new HttpParameter[localArrayList.size()])));
  }
  
  public Relationship updateFriendship(long paramLong, boolean paramBoolean1, boolean paramBoolean2)
    throws TwitterException
  {
    return this.factory.createRelationship(post(this.conf.getRestBaseURL() + "friendships/update.json", new HttpParameter[] { new HttpParameter("user_id", paramLong), new HttpParameter("device", paramBoolean1), new HttpParameter("retweets", paramBoolean2) }));
  }
  
  public Relationship updateFriendship(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    throws TwitterException
  {
    return this.factory.createRelationship(post(this.conf.getRestBaseURL() + "friendships/update.json", new HttpParameter[] { new HttpParameter("screen_name", paramString), new HttpParameter("device", paramBoolean1), new HttpParameter("retweets", paramBoolean2) }));
  }
  
  public User updateProfile(String paramString1, String paramString2, String paramString3, String paramString4)
    throws TwitterException
  {
    ArrayList localArrayList = new ArrayList(4);
    addParameterToList(localArrayList, "name", paramString1);
    addParameterToList(localArrayList, "url", paramString2);
    addParameterToList(localArrayList, "location", paramString3);
    addParameterToList(localArrayList, "description", paramString4);
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "account/update_profile.json", (HttpParameter[])localArrayList.toArray(new HttpParameter[localArrayList.size()])));
  }
  
  public User updateProfileBackgroundImage(File paramFile, boolean paramBoolean)
    throws TwitterException
  {
    checkFileValidity(paramFile);
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "account/update_profile_background_image.json", new HttpParameter[] { new HttpParameter("image", paramFile), new HttpParameter("tile", paramBoolean) }));
  }
  
  public User updateProfileBackgroundImage(InputStream paramInputStream, boolean paramBoolean)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "account/update_profile_background_image.json", new HttpParameter[] { new HttpParameter("image", "image", paramInputStream), new HttpParameter("tile", paramBoolean) }));
  }
  
  public void updateProfileBanner(File paramFile)
    throws TwitterException
  {
    checkFileValidity(paramFile);
    post(this.conf.getRestBaseURL() + "account/update_profile_banner.json", new HttpParameter[] { new HttpParameter("banner", paramFile) });
  }
  
  public void updateProfileBanner(InputStream paramInputStream)
    throws TwitterException
  {
    post(this.conf.getRestBaseURL() + "account/update_profile_banner.json", new HttpParameter[] { new HttpParameter("banner", "banner", paramInputStream) });
  }
  
  public User updateProfileColors(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws TwitterException
  {
    ArrayList localArrayList = new ArrayList(6);
    addParameterToList(localArrayList, "profile_background_color", paramString1);
    addParameterToList(localArrayList, "profile_text_color", paramString2);
    addParameterToList(localArrayList, "profile_link_color", paramString3);
    addParameterToList(localArrayList, "profile_sidebar_fill_color", paramString4);
    addParameterToList(localArrayList, "profile_sidebar_border_color", paramString5);
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "account/update_profile_colors.json", (HttpParameter[])localArrayList.toArray(new HttpParameter[localArrayList.size()])));
  }
  
  public User updateProfileImage(File paramFile)
    throws TwitterException
  {
    checkFileValidity(paramFile);
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "account/update_profile_image.json", new HttpParameter[] { new HttpParameter("image", paramFile) }));
  }
  
  public User updateProfileImage(InputStream paramInputStream)
    throws TwitterException
  {
    return this.factory.createUser(post(this.conf.getRestBaseURL() + "account/update_profile_image.json", new HttpParameter[] { new HttpParameter("image", "image", paramInputStream) }));
  }
  
  public Status updateStatus(String paramString)
    throws TwitterException
  {
    return this.factory.createStatus(post(this.conf.getRestBaseURL() + "statuses/update.json", new HttpParameter[] { new HttpParameter("status", paramString) }));
  }
  
  public Status updateStatus(StatusUpdate paramStatusUpdate)
    throws TwitterException
  {
    StringBuilder localStringBuilder = new StringBuilder().append(this.conf.getRestBaseURL());
    if (paramStatusUpdate.isForUpdateWithMedia()) {}
    for (String str = "statuses/update_with_media.json";; str = "statuses/update.json")
    {
      str = str;
      return this.factory.createStatus(post(str, paramStatusUpdate.asHttpParameterArray()));
    }
  }
  
  public UserList updateUserList(long paramLong, String paramString1, String paramString2, boolean paramBoolean, String paramString3)
    throws TwitterException
  {
    return updateUserList(paramString2, paramBoolean, paramString3, new HttpParameter[] { new HttpParameter("owner_id", paramLong), new HttpParameter("slug", paramString1) });
  }
  
  public UserList updateUserList(long paramLong, String paramString1, boolean paramBoolean, String paramString2)
    throws TwitterException
  {
    return updateUserList(paramString1, paramBoolean, paramString2, new HttpParameter[] { new HttpParameter("list_id", paramLong) });
  }
  
  public UserList updateUserList(String paramString1, String paramString2, String paramString3, boolean paramBoolean, String paramString4)
    throws TwitterException
  {
    return updateUserList(paramString3, paramBoolean, paramString4, new HttpParameter[] { new HttpParameter("owner_screen_name", paramString1), new HttpParameter("slug", paramString2) });
  }
  
  public UploadedMedia uploadMedia(File paramFile)
    throws TwitterException
  {
    checkFileValidity(paramFile);
    return new UploadedMedia(post(this.conf.getUploadBaseURL() + "media/upload.json", new HttpParameter[] { new HttpParameter("media", paramFile) }).asJSONObject());
  }
  
  public UsersResources users()
  {
    return this;
  }
  
  public User verifyCredentials()
    throws TwitterException
  {
    return super.fillInIDAndScreenName();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\TwitterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */