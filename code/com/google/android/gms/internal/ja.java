package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.HashSet;
import java.util.Set;

public class ja
  implements Parcelable.Creator<ir.g>
{
  static void a(ir.g paramg, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    Set localSet = paramg.hB();
    if (localSet.contains(Integer.valueOf(1))) {
      b.c(paramParcel, 1, paramg.getVersionCode());
    }
    if (localSet.contains(Integer.valueOf(2))) {
      b.a(paramParcel, 2, paramg.isPrimary());
    }
    if (localSet.contains(Integer.valueOf(3))) {
      b.a(paramParcel, 3, paramg.getValue(), true);
    }
    b.D(paramParcel, paramInt);
  }
  
  public ir.g aQ(Parcel paramParcel)
  {
    boolean bool = false;
    int j = a.o(paramParcel);
    HashSet localHashSet = new HashSet();
    String str = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        i = a.g(paramParcel, k);
        localHashSet.add(Integer.valueOf(1));
        break;
      case 2: 
        bool = a.c(paramParcel, k);
        localHashSet.add(Integer.valueOf(2));
        break;
      case 3: 
        str = a.m(paramParcel, k);
        localHashSet.add(Integer.valueOf(3));
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ir.g(localHashSet, i, bool, str);
  }
  
  public ir.g[] bN(int paramInt)
  {
    return new ir.g[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ja.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */