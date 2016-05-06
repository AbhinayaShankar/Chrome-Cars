package com.google.zxing.common.reedsolomon;

import java.util.ArrayList;
import java.util.List;

public final class ReedSolomonEncoder
{
  private final List<GenericGFPoly> cachedGenerators;
  private final GenericGF field;
  
  public ReedSolomonEncoder(GenericGF paramGenericGF)
  {
    if (!GenericGF.QR_CODE_FIELD_256.equals(paramGenericGF)) {
      throw new IllegalArgumentException("Only QR Code is supported at this time");
    }
    this.field = paramGenericGF;
    this.cachedGenerators = new ArrayList();
    this.cachedGenerators.add(new GenericGFPoly(paramGenericGF, new int[] { 1 }));
  }
  
  private GenericGFPoly buildGenerator(int paramInt)
  {
    GenericGFPoly localGenericGFPoly;
    int i;
    if (paramInt >= this.cachedGenerators.size())
    {
      localGenericGFPoly = (GenericGFPoly)this.cachedGenerators.get(this.cachedGenerators.size() - 1);
      i = this.cachedGenerators.size();
    }
    for (;;)
    {
      if (i > paramInt) {
        return (GenericGFPoly)this.cachedGenerators.get(paramInt);
      }
      localGenericGFPoly = localGenericGFPoly.multiply(new GenericGFPoly(this.field, new int[] { 1, this.field.exp(i - 1) }));
      this.cachedGenerators.add(localGenericGFPoly);
      i += 1;
    }
  }
  
  public void encode(int[] paramArrayOfInt, int paramInt)
  {
    if (paramInt == 0) {
      throw new IllegalArgumentException("No error correction bytes");
    }
    int i = paramArrayOfInt.length - paramInt;
    if (i <= 0) {
      throw new IllegalArgumentException("No data bytes provided");
    }
    Object localObject = buildGenerator(paramInt);
    int[] arrayOfInt = new int[i];
    System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, i);
    localObject = new GenericGFPoly(this.field, arrayOfInt).multiplyByMonomial(paramInt, 1).divide(localObject)[1].getCoefficients();
    int j = paramInt - localObject.length;
    paramInt = 0;
    for (;;)
    {
      if (paramInt >= j)
      {
        System.arraycopy(localObject, 0, paramArrayOfInt, i + j, localObject.length);
        return;
      }
      paramArrayOfInt[(i + paramInt)] = 0;
      paramInt += 1;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\common\reedsolomon\ReedSolomonEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */