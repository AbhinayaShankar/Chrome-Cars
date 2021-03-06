package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class ce
  implements Parcelable.Creator<cd>
{
  static void a(cd paramcd, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramcd.versionCode);
    b.a(paramParcel, 2, paramcd.ob, false);
    b.a(paramParcel, 3, paramcd.oc, paramInt, false);
    b.a(paramParcel, 4, paramcd.kQ, paramInt, false);
    b.a(paramParcel, 5, paramcd.adUnitId, false);
    b.a(paramParcel, 6, paramcd.applicationInfo, paramInt, false);
    b.a(paramParcel, 7, paramcd.od, paramInt, false);
    b.a(paramParcel, 8, paramcd.oe, false);
    b.a(paramParcel, 9, paramcd.of, false);
    b.a(paramParcel, 10, paramcd.og, false);
    b.a(paramParcel, 11, paramcd.kN, paramInt, false);
    b.a(paramParcel, 12, paramcd.oh, false);
    b.D(paramParcel, i);
  }
  
  public cd f(Parcel paramParcel)
  {
    int j = a.o(paramParcel);
    int i = 0;
    Bundle localBundle2 = null;
    z localz = null;
    ab localab = null;
    String str4 = null;
    ApplicationInfo localApplicationInfo = null;
    PackageInfo localPackageInfo = null;
    String str3 = null;
    String str2 = null;
    String str1 = null;
    db localdb = null;
    Bundle localBundle1 = null;
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
        break;
      case 2: 
        localBundle2 = a.o(paramParcel, k);
        break;
      case 3: 
        localz = (z)a.a(paramParcel, k, z.CREATOR);
        break;
      case 4: 
        localab = (ab)a.a(paramParcel, k, ab.CREATOR);
        break;
      case 5: 
        str4 = a.m(paramParcel, k);
        break;
      case 6: 
        localApplicationInfo = (ApplicationInfo)a.a(paramParcel, k, ApplicationInfo.CREATOR);
        break;
      case 7: 
        localPackageInfo = (PackageInfo)a.a(paramParcel, k, PackageInfo.CREATOR);
        break;
      case 8: 
        str3 = a.m(paramParcel, k);
        break;
      case 9: 
        str2 = a.m(paramParcel, k);
        break;
      case 10: 
        str1 = a.m(paramParcel, k);
        break;
      case 11: 
        localdb = (db)a.a(paramParcel, k, db.CREATOR);
        break;
      case 12: 
        localBundle1 = a.o(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new cd(i, localBundle2, localz, localab, str4, localApplicationInfo, localPackageInfo, str3, str2, str1, localdb, localBundle1);
  }
  
  public cd[] k(int paramInt)
  {
    return new cd[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */