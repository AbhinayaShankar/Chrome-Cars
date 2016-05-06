package com.google.zxing;

public final class PlanarYUVLuminanceSource
  extends LuminanceSource
{
  private final int dataHeight;
  private final int dataWidth;
  private final int left;
  private final int top;
  private final byte[] yuvData;
  
  public PlanarYUVLuminanceSource(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean)
  {
    super(paramInt5, paramInt6);
    if ((paramInt3 + paramInt5 > paramInt1) || (paramInt4 + paramInt6 > paramInt2)) {
      throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
    }
    this.yuvData = paramArrayOfByte;
    this.dataWidth = paramInt1;
    this.dataHeight = paramInt2;
    this.left = paramInt3;
    this.top = paramInt4;
    if (paramBoolean) {
      reverseHorizontal(paramInt5, paramInt6);
    }
  }
  
  private void reverseHorizontal(int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = this.yuvData;
    int k = 0;
    int j = this.top * this.dataWidth + this.left;
    if (k >= paramInt2) {
      return;
    }
    int i1 = paramInt1 / 2;
    int n = j;
    int m = j + paramInt1 - 1;
    for (;;)
    {
      if (n >= j + i1)
      {
        k += 1;
        j += this.dataWidth;
        break;
      }
      int i = arrayOfByte[n];
      arrayOfByte[n] = arrayOfByte[m];
      arrayOfByte[m] = i;
      n += 1;
      m -= 1;
    }
  }
  
  public LuminanceSource crop(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return new PlanarYUVLuminanceSource(this.yuvData, this.dataWidth, this.dataHeight, this.left + paramInt1, this.top + paramInt2, paramInt3, paramInt4, false);
  }
  
  public byte[] getMatrix()
  {
    int k = getWidth();
    int m = getHeight();
    Object localObject;
    if ((k == this.dataWidth) && (m == this.dataHeight))
    {
      localObject = this.yuvData;
      return (byte[])localObject;
    }
    int i = k * m;
    byte[] arrayOfByte1 = new byte[i];
    int j = this.top * this.dataWidth + this.left;
    if (k == this.dataWidth)
    {
      System.arraycopy(this.yuvData, j, arrayOfByte1, 0, i);
      return arrayOfByte1;
    }
    byte[] arrayOfByte2 = this.yuvData;
    i = 0;
    for (;;)
    {
      localObject = arrayOfByte1;
      if (i >= m) {
        break;
      }
      System.arraycopy(arrayOfByte2, j, arrayOfByte1, i * k, k);
      j += this.dataWidth;
      i += 1;
    }
  }
  
  public byte[] getRow(int paramInt, byte[] paramArrayOfByte)
  {
    if ((paramInt < 0) || (paramInt >= getHeight())) {
      throw new IllegalArgumentException("Requested row is outside the image: " + paramInt);
    }
    int i = getWidth();
    byte[] arrayOfByte;
    if (paramArrayOfByte != null)
    {
      arrayOfByte = paramArrayOfByte;
      if (paramArrayOfByte.length >= i) {}
    }
    else
    {
      arrayOfByte = new byte[i];
    }
    int j = this.top;
    int k = this.dataWidth;
    int m = this.left;
    System.arraycopy(this.yuvData, (j + paramInt) * k + m, arrayOfByte, 0, i);
    return arrayOfByte;
  }
  
  public boolean isCropSupported()
  {
    return true;
  }
  
  public int[] renderCroppedGreyscaleBitmap()
  {
    int m = getWidth();
    int n = getHeight();
    int[] arrayOfInt = new int[m * n];
    byte[] arrayOfByte = this.yuvData;
    int j = this.top * this.dataWidth + this.left;
    int i = 0;
    if (i >= n) {
      return arrayOfInt;
    }
    int k = 0;
    for (;;)
    {
      if (k >= m)
      {
        j += this.dataWidth;
        i += 1;
        break;
      }
      arrayOfInt[(i * m + k)] = (0xFF000000 | 65793 * (arrayOfByte[(j + k)] & 0xFF));
      k += 1;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\PlanarYUVLuminanceSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */