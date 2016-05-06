package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.DriveId;

public class x
  implements Parcelable.Creator<ListParentsRequest>
{
  static void a(ListParentsRequest paramListParentsRequest, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramListParentsRequest.wj);
    b.a(paramParcel, 2, paramListParentsRequest.DT, paramInt, false);
    b.D(paramParcel, i);
  }
  
  public ListParentsRequest M(Parcel paramParcel)
  {
    int j = a.o(paramParcel);
    int i = 0;
    DriveId localDriveId = null;
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
        localDriveId = (DriveId)a.a(paramParcel, k, DriveId.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ListParentsRequest(i, localDriveId);
  }
  
  public ListParentsRequest[] ar(int paramInt)
  {
    return new ListParentsRequest[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\internal\x.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */