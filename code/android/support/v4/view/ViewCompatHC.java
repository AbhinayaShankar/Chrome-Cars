package android.support.v4.view;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.View;

class ViewCompatHC
{
  static long getFrameTime()
  {
    return ValueAnimator.getFrameDelay();
  }
  
  public static int getLayerType(View paramView)
  {
    return paramView.getLayerType();
  }
  
  public static void setLayerType(View paramView, int paramInt, Paint paramPaint)
  {
    paramView.setLayerType(paramInt, paramPaint);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\android\support\v4\view\ViewCompatHC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */