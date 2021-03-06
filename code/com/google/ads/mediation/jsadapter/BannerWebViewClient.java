package com.google.ads.mediation.jsadapter;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.da;
import java.net.URI;
import java.net.URISyntaxException;

public final class BannerWebViewClient
  extends WebViewClient
{
  private final String A = c(paramString);
  private boolean B;
  private final JavascriptAdapter r;
  
  public BannerWebViewClient(JavascriptAdapter paramJavascriptAdapter, String paramString)
  {
    this.r = paramJavascriptAdapter;
    this.B = false;
  }
  
  private boolean b(String paramString)
  {
    paramString = c(paramString);
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      try
      {
        Object localObject1 = new URI(paramString);
        if ("passback".equals(((URI)localObject1).getScheme()))
        {
          da.s("Passback received");
          this.r.sendAdNotReceivedUpdate();
          return true;
        }
        if (!TextUtils.isEmpty(this.A))
        {
          Object localObject2 = new URI(this.A);
          paramString = ((URI)localObject2).getHost();
          String str = ((URI)localObject1).getHost();
          localObject2 = ((URI)localObject2).getPath();
          localObject1 = ((URI)localObject1).getPath();
          if ((equals(paramString, str)) && (equals(localObject2, localObject1)))
          {
            da.s("Passback received");
            this.r.sendAdNotReceivedUpdate();
            return true;
          }
        }
      }
      catch (URISyntaxException paramString)
      {
        da.t(paramString.getMessage());
      }
    }
    return false;
  }
  
  private String c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return paramString;
      try
      {
        if (paramString.endsWith("/"))
        {
          String str = paramString.substring(0, paramString.length() - 1);
          return str;
        }
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
        da.t(localIndexOutOfBoundsException.getMessage());
      }
    }
    return paramString;
  }
  
  private static boolean equals(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  public void onLoadResource(WebView paramWebView, String paramString)
  {
    da.v("onLoadResource: " + paramString);
    if (!b(paramString)) {
      super.onLoadResource(paramWebView, paramString);
    }
  }
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    da.v("onPageFinished: " + paramString);
    super.onPageFinished(paramWebView, paramString);
    if (!this.B)
    {
      this.r.startCheckingForAd();
      this.B = true;
    }
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    da.v("shouldOverrideUrlLoading: " + paramString);
    if (b(paramString))
    {
      da.s("shouldOverrideUrlLoading: received passback url");
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\ads\mediation\jsadapter\BannerWebViewClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */