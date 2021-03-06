package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.c.c;
import com.google.android.gms.internal.c.d;
import com.google.android.gms.internal.c.i;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ai
{
  private static void a(DataLayer paramDataLayer, c.d paramd)
  {
    paramd = paramd.fd;
    int j = paramd.length;
    int i = 0;
    while (i < j)
    {
      paramDataLayer.bg(di.j(paramd[i]));
      i += 1;
    }
  }
  
  public static void a(DataLayer paramDataLayer, c.i parami)
  {
    if (parami.fT == null)
    {
      bh.w("supplemental missing experimentSupplemental");
      return;
    }
    a(paramDataLayer, parami.fT);
    b(paramDataLayer, parami.fT);
    c(paramDataLayer, parami.fT);
  }
  
  private static void b(DataLayer paramDataLayer, c.d paramd)
  {
    paramd = paramd.fc;
    int j = paramd.length;
    int i = 0;
    while (i < j)
    {
      Map localMap = c(paramd[i]);
      if (localMap != null) {
        paramDataLayer.push(localMap);
      }
      i += 1;
    }
  }
  
  private static Map<String, Object> c(d.a parama)
  {
    parama = di.o(parama);
    if (!(parama instanceof Map))
    {
      bh.w("value: " + parama + " is not a map value, ignored.");
      return null;
    }
    return (Map)parama;
  }
  
  private static void c(DataLayer paramDataLayer, c.d paramd)
  {
    c.c[] arrayOfc = paramd.fe;
    int j = arrayOfc.length;
    int i = 0;
    while (i < j)
    {
      c.c localc = arrayOfc[i];
      if (localc.eX == null)
      {
        bh.w("GaExperimentRandom: No key");
        i += 1;
      }
      else
      {
        Object localObject = paramDataLayer.get(localc.eX);
        if (!(localObject instanceof Number))
        {
          paramd = null;
          label64:
          long l1 = localc.eY;
          long l2 = localc.eZ;
          if ((!localc.fa) || (paramd == null) || (paramd.longValue() < l1) || (paramd.longValue() > l2))
          {
            if (l1 > l2) {
              break label237;
            }
            localObject = Long.valueOf(Math.round(Math.random() * (l2 - l1) + l1));
          }
          paramDataLayer.bg(localc.eX);
          paramd = paramDataLayer.b(localc.eX, localObject);
          if (localc.fb > 0L)
          {
            if (paramd.containsKey("gtm")) {
              break label245;
            }
            paramd.put("gtm", DataLayer.mapOf(new Object[] { "lifetime", Long.valueOf(localc.fb) }));
          }
        }
        for (;;)
        {
          paramDataLayer.push(paramd);
          break;
          paramd = Long.valueOf(((Number)localObject).longValue());
          break label64;
          label237:
          bh.w("GaExperimentRandom: random range invalid");
          break;
          label245:
          localObject = paramd.get("gtm");
          if ((localObject instanceof Map)) {
            ((Map)localObject).put("lifetime", Long.valueOf(localc.fb));
          } else {
            bh.w("GaExperimentRandom: gtm not a map");
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\ai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */