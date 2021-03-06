package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.pdf417.decoder.Decoder;
import com.google.zxing.pdf417.detector.Detector;
import java.util.Map;

public final class PDF417Reader
  implements Reader
{
  private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
  private final Decoder decoder = new Decoder();
  
  private static BitMatrix extractPureBits(BitMatrix paramBitMatrix)
    throws NotFoundException
  {
    Object localObject = paramBitMatrix.getTopLeftOnBit();
    int[] arrayOfInt = paramBitMatrix.getBottomRightOnBit();
    if ((localObject == null) || (arrayOfInt == null)) {
      throw NotFoundException.getNotFoundInstance();
    }
    int k = moduleSize((int[])localObject, paramBitMatrix);
    int m = localObject[1];
    int i = arrayOfInt[1];
    int n = findPatternStart(localObject[0], m, paramBitMatrix);
    int i1 = (findPatternEnd(localObject[0], m, paramBitMatrix) - n + 1) / k;
    int i2 = (i - m + 1) / k;
    if ((i1 <= 0) || (i2 <= 0)) {
      throw NotFoundException.getNotFoundInstance();
    }
    int i3 = k >> 1;
    localObject = new BitMatrix(i1, i2);
    i = 0;
    if (i >= i2) {
      return (BitMatrix)localObject;
    }
    int j = 0;
    for (;;)
    {
      if (j >= i1)
      {
        i += 1;
        break;
      }
      if (paramBitMatrix.get(j * k + (n + i3), m + i3 + i * k)) {
        ((BitMatrix)localObject).set(j, i);
      }
      j += 1;
    }
  }
  
  private static int findPatternEnd(int paramInt1, int paramInt2, BitMatrix paramBitMatrix)
    throws NotFoundException
  {
    int i = paramBitMatrix.getWidth() - 1;
    int j;
    boolean bool1;
    int k;
    if ((i <= paramInt1) || (paramBitMatrix.get(i, paramInt2)))
    {
      j = 0;
      bool1 = true;
      k = i;
    }
    for (;;)
    {
      if ((k <= paramInt1) || (j >= 9))
      {
        if (k != paramInt1) {
          break label100;
        }
        throw NotFoundException.getNotFoundInstance();
        i -= 1;
        break;
      }
      k -= 1;
      boolean bool2 = paramBitMatrix.get(k, paramInt2);
      i = j;
      if (bool1 != bool2) {
        i = j + 1;
      }
      bool1 = bool2;
      j = i;
    }
    label100:
    return k;
  }
  
  private static int findPatternStart(int paramInt1, int paramInt2, BitMatrix paramBitMatrix)
    throws NotFoundException
  {
    int k = paramBitMatrix.getWidth();
    int i = paramInt1;
    paramInt1 = 0;
    boolean bool1 = true;
    int j = i;
    for (;;)
    {
      if ((j >= k - 1) || (paramInt1 >= 8))
      {
        if (j != k - 1) {
          break;
        }
        throw NotFoundException.getNotFoundInstance();
      }
      j += 1;
      boolean bool2 = paramBitMatrix.get(j, paramInt2);
      i = paramInt1;
      if (bool1 != bool2) {
        i = paramInt1 + 1;
      }
      bool1 = bool2;
      paramInt1 = i;
    }
    return j;
  }
  
  private static int moduleSize(int[] paramArrayOfInt, BitMatrix paramBitMatrix)
    throws NotFoundException
  {
    int i = paramArrayOfInt[0];
    int j = paramArrayOfInt[1];
    int k = paramBitMatrix.getWidth();
    for (;;)
    {
      if ((i >= k) || (!paramBitMatrix.get(i, j)))
      {
        if (i != k) {
          break;
        }
        throw NotFoundException.getNotFoundInstance();
      }
      i += 1;
    }
    i = i - paramArrayOfInt[0] >>> 3;
    if (i == 0) {
      throw NotFoundException.getNotFoundInstance();
    }
    return i;
  }
  
  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, FormatException, ChecksumException
  {
    return decode(paramBinaryBitmap, null);
  }
  
  public Result decode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException, FormatException, ChecksumException
  {
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.PURE_BARCODE)))
    {
      paramBinaryBitmap = extractPureBits(paramBinaryBitmap.getBlackMatrix());
      paramBinaryBitmap = this.decoder.decode(paramBinaryBitmap);
    }
    for (paramMap = NO_POINTS;; paramMap = paramMap.getPoints())
    {
      return new Result(paramBinaryBitmap.getText(), paramBinaryBitmap.getRawBytes(), paramMap, BarcodeFormat.PDF_417);
      paramMap = new Detector(paramBinaryBitmap).detect();
      paramBinaryBitmap = this.decoder.decode(paramMap.getBits());
    }
  }
  
  public void reset() {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\pdf417\PDF417Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */