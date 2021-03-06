package com.google.android.gms.ads.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.ak;

public final class SearchAdView
  extends ViewGroup
{
  private final ak kD;
  
  public SearchAdView(Context paramContext)
  {
    super(paramContext);
    this.kD = new ak(this);
  }
  
  public SearchAdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.kD = new ak(this, paramAttributeSet, false);
  }
  
  public SearchAdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.kD = new ak(this, paramAttributeSet, false);
  }
  
  public void destroy()
  {
    this.kD.destroy();
  }
  
  public AdListener getAdListener()
  {
    return this.kD.getAdListener();
  }
  
  public AdSize getAdSize()
  {
    return this.kD.getAdSize();
  }
  
  public String getAdUnitId()
  {
    return this.kD.getAdUnitId();
  }
  
  public void loadAd(SearchAdRequest paramSearchAdRequest)
  {
    this.kD.a(paramSearchAdRequest.N());
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    View localView = getChildAt(0);
    if ((localView != null) && (localView.getVisibility() != 8))
    {
      int i = localView.getMeasuredWidth();
      int j = localView.getMeasuredHeight();
      paramInt1 = (paramInt3 - paramInt1 - i) / 2;
      paramInt2 = (paramInt4 - paramInt2 - j) / 2;
      localView.layout(paramInt1, paramInt2, i + paramInt1, j + paramInt2);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = 0;
    Object localObject = getChildAt(0);
    AdSize localAdSize = getAdSize();
    int j;
    if ((localObject != null) && (((View)localObject).getVisibility() != 8))
    {
      measureChild((View)localObject, paramInt1, paramInt2);
      j = ((View)localObject).getMeasuredWidth();
      i = ((View)localObject).getMeasuredHeight();
    }
    for (;;)
    {
      j = Math.max(j, getSuggestedMinimumWidth());
      i = Math.max(i, getSuggestedMinimumHeight());
      setMeasuredDimension(View.resolveSize(j, paramInt1), View.resolveSize(i, paramInt2));
      return;
      if (localAdSize != null)
      {
        localObject = getContext();
        j = localAdSize.getWidthInPixels((Context)localObject);
        i = localAdSize.getHeightInPixels((Context)localObject);
      }
      else
      {
        j = 0;
      }
    }
  }
  
  public void pause()
  {
    this.kD.pause();
  }
  
  public void resume()
  {
    this.kD.resume();
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    this.kD.setAdListener(paramAdListener);
  }
  
  public void setAdSize(AdSize paramAdSize)
  {
    this.kD.setAdSizes(new AdSize[] { paramAdSize });
  }
  
  public void setAdUnitId(String paramString)
  {
    this.kD.setAdUnitId(paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\ads\search\SearchAdView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */