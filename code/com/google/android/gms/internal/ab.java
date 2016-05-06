package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.a;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ab
  implements SafeParcelable
{
  public static final ac CREATOR = new ac();
  public final int height;
  public final int heightPixels;
  public final String ln;
  public final boolean lo;
  public final ab[] lp;
  public final int versionCode;
  public final int width;
  public final int widthPixels;
  
  public ab()
  {
    this(2, "interstitial_mb", 0, 0, true, 0, 0, null);
  }
  
  ab(int paramInt1, String paramString, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, int paramInt5, ab[] paramArrayOfab)
  {
    this.versionCode = paramInt1;
    this.ln = paramString;
    this.height = paramInt2;
    this.heightPixels = paramInt3;
    this.lo = paramBoolean;
    this.width = paramInt4;
    this.widthPixels = paramInt5;
    this.lp = paramArrayOfab;
  }
  
  public ab(Context paramContext, AdSize paramAdSize)
  {
    this(paramContext, new AdSize[] { paramAdSize });
  }
  
  public ab(Context paramContext, AdSize[] paramArrayOfAdSize)
  {
    AdSize localAdSize = paramArrayOfAdSize[0];
    this.versionCode = 2;
    this.lo = false;
    this.width = localAdSize.getWidth();
    this.height = localAdSize.getHeight();
    int i;
    int j;
    label62:
    DisplayMetrics localDisplayMetrics;
    int k;
    label98:
    int m;
    if (this.width == -1)
    {
      i = 1;
      if (this.height != -2) {
        break label217;
      }
      j = 1;
      localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
      if (i == 0) {
        break label223;
      }
      this.widthPixels = a(localDisplayMetrics);
      k = (int)(this.widthPixels / localDisplayMetrics.density);
      if (j == 0) {
        break label245;
      }
      m = c(localDisplayMetrics);
      label110:
      this.heightPixels = cz.a(localDisplayMetrics, m);
      if ((i == 0) && (j == 0)) {
        break label254;
      }
    }
    label217:
    label223:
    label245:
    label254:
    for (this.ln = (k + "x" + m + "_as");; this.ln = localAdSize.toString())
    {
      if (paramArrayOfAdSize.length <= 1) {
        break label266;
      }
      this.lp = new ab[paramArrayOfAdSize.length];
      i = n;
      while (i < paramArrayOfAdSize.length)
      {
        this.lp[i] = new ab(paramContext, paramArrayOfAdSize[i]);
        i += 1;
      }
      i = 0;
      break;
      j = 0;
      break label62;
      k = this.width;
      this.widthPixels = cz.a(localDisplayMetrics, this.width);
      break label98;
      m = this.height;
      break label110;
    }
    label266:
    this.lp = null;
  }
  
  public ab(ab paramab, ab[] paramArrayOfab)
  {
    this(2, paramab.ln, paramab.height, paramab.heightPixels, paramab.lo, paramab.width, paramab.widthPixels, paramArrayOfab);
  }
  
  public static int a(DisplayMetrics paramDisplayMetrics)
  {
    return paramDisplayMetrics.widthPixels;
  }
  
  public static int b(DisplayMetrics paramDisplayMetrics)
  {
    return (int)(c(paramDisplayMetrics) * paramDisplayMetrics.density);
  }
  
  private static int c(DisplayMetrics paramDisplayMetrics)
  {
    int i = (int)(paramDisplayMetrics.heightPixels / paramDisplayMetrics.density);
    if (i <= 400) {
      return 32;
    }
    if (i <= 720) {
      return 50;
    }
    return 90;
  }
  
  public AdSize ai()
  {
    return a.a(this.width, this.height, this.ln);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ac.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */