package io.vov.vitamio.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RemoteViews.RemoteView;

@RemoteViews.RemoteView
public class CenterLayout
  extends ViewGroup
{
  private int mHeight;
  private int mPaddingBottom = 0;
  private int mPaddingLeft = 0;
  private int mPaddingRight = 0;
  private int mPaddingTop = 0;
  private int mWidth;
  
  public CenterLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public CenterLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public CenterLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new LayoutParams(paramLayoutParams);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt4 = getChildCount();
    this.mWidth = getMeasuredWidth();
    this.mHeight = getMeasuredHeight();
    paramInt1 = 0;
    if (paramInt1 >= paramInt4) {
      return;
    }
    View localView = getChildAt(paramInt1);
    if (localView.getVisibility() != 8)
    {
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      paramInt2 = this.mPaddingLeft + localLayoutParams.x;
      if (localLayoutParams.width <= 0) {
        break label167;
      }
      paramInt2 += (int)((this.mWidth - localLayoutParams.width) / 2.0D);
      label96:
      paramInt3 = this.mPaddingTop + localLayoutParams.y;
      if (localLayoutParams.height <= 0) {
        break label189;
      }
      paramInt3 += (int)((this.mHeight - localLayoutParams.height) / 2.0D);
    }
    for (;;)
    {
      localView.layout(paramInt2, paramInt3, localView.getMeasuredWidth() + paramInt2, localView.getMeasuredHeight() + paramInt3);
      paramInt1 += 1;
      break;
      label167:
      paramInt2 += (int)((this.mWidth - localView.getMeasuredWidth()) / 2.0D);
      break label96;
      label189:
      paramInt3 += (int)((this.mHeight - localView.getMeasuredHeight()) / 2.0D);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i1 = getChildCount();
    int j = 0;
    int i = 0;
    measureChildren(paramInt1, paramInt2);
    int k = 0;
    for (;;)
    {
      if (k >= i1)
      {
        k = this.mPaddingLeft;
        m = this.mPaddingRight;
        j = Math.max(j + (this.mPaddingTop + this.mPaddingBottom), getSuggestedMinimumHeight());
        setMeasuredDimension(resolveSize(Math.max(i + (k + m), getSuggestedMinimumWidth()), paramInt1), resolveSize(j, paramInt2));
        return;
      }
      View localView = getChildAt(k);
      int n = j;
      int m = i;
      if (localView.getVisibility() != 8)
      {
        LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
        m = localLayoutParams.x;
        int i3 = localView.getMeasuredWidth();
        n = localLayoutParams.y;
        int i2 = localView.getMeasuredHeight();
        m = Math.max(i, m + i3);
        n = Math.max(j, n + i2);
      }
      k += 1;
      j = n;
      i = m;
    }
  }
  
  public static class LayoutParams
    extends ViewGroup.LayoutParams
  {
    public int x;
    public int y;
    
    public LayoutParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      super(paramInt2);
      this.x = paramInt3;
      this.y = paramInt4;
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\widget\CenterLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */