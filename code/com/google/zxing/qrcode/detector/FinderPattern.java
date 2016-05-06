package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

public final class FinderPattern
  extends ResultPoint
{
  private int count;
  private final float estimatedModuleSize;
  
  FinderPattern(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this(paramFloat1, paramFloat2, paramFloat3, 1);
  }
  
  private FinderPattern(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt)
  {
    super(paramFloat1, paramFloat2);
    this.estimatedModuleSize = paramFloat3;
    this.count = paramInt;
  }
  
  boolean aboutEquals(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if ((Math.abs(paramFloat2 - getY()) <= paramFloat1) && (Math.abs(paramFloat3 - getX()) <= paramFloat1)) {
      paramFloat1 = Math.abs(paramFloat1 - this.estimatedModuleSize);
    }
    return (paramFloat1 <= 1.0F) || (paramFloat1 <= this.estimatedModuleSize);
  }
  
  FinderPattern combineEstimate(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    int i = this.count + 1;
    return new FinderPattern((this.count * getX() + paramFloat2) / i, (this.count * getY() + paramFloat1) / i, (this.count * this.estimatedModuleSize + paramFloat3) / i, i);
  }
  
  int getCount()
  {
    return this.count;
  }
  
  public float getEstimatedModuleSize()
  {
    return this.estimatedModuleSize;
  }
  
  void incrementCount()
  {
    this.count += 1;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\detector\FinderPattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */