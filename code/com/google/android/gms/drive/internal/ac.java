package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ConflictEvent;

public class ac
  implements Parcelable.Creator<OnEventResponse>
{
  static void a(OnEventResponse paramOnEventResponse, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramOnEventResponse.wj);
    b.c(paramParcel, 2, paramOnEventResponse.Dm);
    b.a(paramParcel, 3, paramOnEventResponse.Eb, paramInt, false);
    b.a(paramParcel, 4, paramOnEventResponse.Ec, paramInt, false);
    b.D(paramParcel, i);
  }
  
  public OnEventResponse Q(Parcel paramParcel)
  {
    ConflictEvent localConflictEvent = null;
    int j = 0;
    int m = a.o(paramParcel);
    ChangeEvent localChangeEvent = null;
    int i = 0;
    if (paramParcel.dataPosition() < m)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      default: 
        a.b(paramParcel, k);
        k = j;
        j = i;
        i = k;
      }
      for (;;)
      {
        k = j;
        j = i;
        i = k;
        break;
        k = a.g(paramParcel, k);
        i = j;
        j = k;
        continue;
        k = a.g(paramParcel, k);
        j = i;
        i = k;
        continue;
        localChangeEvent = (ChangeEvent)a.a(paramParcel, k, ChangeEvent.CREATOR);
        k = i;
        i = j;
        j = k;
        continue;
        localConflictEvent = (ConflictEvent)a.a(paramParcel, k, ConflictEvent.CREATOR);
        k = i;
        i = j;
        j = k;
      }
    }
    if (paramParcel.dataPosition() != m) {
      throw new a.a("Overread allowed size end=" + m, paramParcel);
    }
    return new OnEventResponse(i, j, localChangeEvent, localConflictEvent);
  }
  
  public OnEventResponse[] av(int paramInt)
  {
    return new OnEventResponse[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\internal\ac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */