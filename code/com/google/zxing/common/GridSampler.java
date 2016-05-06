package com.google.zxing.common;

import com.google.zxing.NotFoundException;

public abstract class GridSampler
{
  private static GridSampler gridSampler = new DefaultGridSampler();
  
  protected static void checkAndNudgePoints(BitMatrix paramBitMatrix, float[] paramArrayOfFloat)
    throws NotFoundException
  {
    int k = paramBitMatrix.getWidth();
    int m = paramBitMatrix.getHeight();
    int i = 1;
    int j = 0;
    if ((j >= paramArrayOfFloat.length) || (i == 0))
    {
      i = 1;
      j = paramArrayOfFloat.length - 2;
      if ((j >= 0) && (i != 0)) {}
    }
    else
    {
      i1 = (int)paramArrayOfFloat[j];
      n = (int)paramArrayOfFloat[(j + 1)];
      if ((i1 < -1) || (i1 > k) || (n < -1) || (n > m)) {
        throw NotFoundException.getNotFoundInstance();
      }
      i = 0;
      if (i1 == -1)
      {
        paramArrayOfFloat[j] = 0.0F;
        i = 1;
        label100:
        if (n != -1) {
          break label141;
        }
        paramArrayOfFloat[(j + 1)] = 0.0F;
        i = 1;
      }
      for (;;)
      {
        j += 2;
        break;
        if (i1 != k) {
          break label100;
        }
        paramArrayOfFloat[j] = (k - 1);
        i = 1;
        break label100;
        label141:
        if (n == m)
        {
          paramArrayOfFloat[(j + 1)] = (m - 1);
          i = 1;
        }
      }
    }
    int i1 = (int)paramArrayOfFloat[j];
    int n = (int)paramArrayOfFloat[(j + 1)];
    if ((i1 < -1) || (i1 > k) || (n < -1) || (n > m)) {
      throw NotFoundException.getNotFoundInstance();
    }
    i = 0;
    if (i1 == -1)
    {
      paramArrayOfFloat[j] = 0.0F;
      i = 1;
      label221:
      if (n != -1) {
        break label262;
      }
      paramArrayOfFloat[(j + 1)] = 0.0F;
      i = 1;
    }
    for (;;)
    {
      j -= 2;
      break;
      if (i1 != k) {
        break label221;
      }
      paramArrayOfFloat[j] = (k - 1);
      i = 1;
      break label221;
      label262:
      if (n == m)
      {
        paramArrayOfFloat[(j + 1)] = (m - 1);
        i = 1;
      }
    }
  }
  
  public static GridSampler getInstance()
  {
    return gridSampler;
  }
  
  public static void setGridSampler(GridSampler paramGridSampler)
  {
    gridSampler = paramGridSampler;
  }
  
  public abstract BitMatrix sampleGrid(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16)
    throws NotFoundException;
  
  public abstract BitMatrix sampleGrid(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, PerspectiveTransform paramPerspectiveTransform)
    throws NotFoundException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\GridSampler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */