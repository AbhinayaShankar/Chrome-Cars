package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;

public class d
  implements Parcelable.Creator<DriveId>
{
  static void a(DriveId paramDriveId, Parcel paramParcel, int paramInt)
  {
    paramInt = b.p(paramParcel);
    b.c(paramParcel, 1, paramDriveId.wj);
    b.a(paramParcel, 2, paramDriveId.Dc, false);
    b.a(paramParcel, 3, paramDriveId.Dd);
    b.a(paramParcel, 4, paramDriveId.De);
    b.D(paramParcel, paramInt);
  }
  
  public DriveId[] ae(int paramInt)
  {
    return new DriveId[paramInt];
  }
  
  public DriveId z(Parcel paramParcel)
  {
    long l1 = 0L;
    int j = a.o(paramParcel);
    int i = 0;
    String str = null;
    long l2 = 0L;
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
        str = a.m(paramParcel, k);
        break;
      case 3: 
        l2 = a.h(paramParcel, k);
        break;
      case 4: 
        l1 = a.h(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new DriveId(i, str, l2, l1);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */