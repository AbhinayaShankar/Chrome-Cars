package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class cg
  implements Parcelable.Creator<cf>
{
  static void a(cf paramcf, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.c(paramParcel, 1, paramcf.versionCode);
    b.a(paramParcel, 2, paramcf.nw, false);
    b.a(paramParcel, 3, paramcf.oi, false);
    b.a(paramParcel, 4, paramcf.mt, false);
    b.c(paramParcel, 5, paramcf.errorCode);
    b.a(paramParcel, 6, paramcf.mu, false);
    b.a(paramParcel, 7, paramcf.oj);
    b.a(paramParcel, 8, paramcf.ok);
    b.a(paramParcel, 9, paramcf.ol);
    b.a(paramParcel, 10, paramcf.om, false);
    b.a(paramParcel, 11, paramcf.mx);
    b.c(paramParcel, 12, paramcf.orientation);
    b.a(paramParcel, 13, paramcf.on, false);
    b.a(paramParcel, 14, paramcf.oo);
    b.D(paramParcel, paramInt);
  }
  
  public cf g(Parcel paramParcel)
  {
    int m = a.o(paramParcel);
    int k = 0;
    String str3 = null;
    String str2 = null;
    ArrayList localArrayList3 = null;
    int j = 0;
    ArrayList localArrayList2 = null;
    long l4 = 0L;
    boolean bool = false;
    long l3 = 0L;
    ArrayList localArrayList1 = null;
    long l2 = 0L;
    int i = 0;
    String str1 = null;
    long l1 = 0L;
    while (paramParcel.dataPosition() < m)
    {
      int n = a.n(paramParcel);
      switch (a.S(n))
      {
      default: 
        a.b(paramParcel, n);
        break;
      case 1: 
        k = a.g(paramParcel, n);
        break;
      case 2: 
        str3 = a.m(paramParcel, n);
        break;
      case 3: 
        str2 = a.m(paramParcel, n);
        break;
      case 4: 
        localArrayList3 = a.y(paramParcel, n);
        break;
      case 5: 
        j = a.g(paramParcel, n);
        break;
      case 6: 
        localArrayList2 = a.y(paramParcel, n);
        break;
      case 7: 
        l4 = a.h(paramParcel, n);
        break;
      case 8: 
        bool = a.c(paramParcel, n);
        break;
      case 9: 
        l3 = a.h(paramParcel, n);
        break;
      case 10: 
        localArrayList1 = a.y(paramParcel, n);
        break;
      case 11: 
        l2 = a.h(paramParcel, n);
        break;
      case 12: 
        i = a.g(paramParcel, n);
        break;
      case 13: 
        str1 = a.m(paramParcel, n);
        break;
      case 14: 
        l1 = a.h(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new cf(k, str3, str2, localArrayList3, j, localArrayList2, l4, bool, l3, localArrayList1, l2, i, str1, l1);
  }
  
  public cf[] l(int paramInt)
  {
    return new cf[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\cg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */