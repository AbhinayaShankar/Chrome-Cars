package org.apache.commons.lang.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EnumUtils
{
  public static Enum getEnum(Class paramClass, String paramString)
  {
    return Enum.getEnum(paramClass, paramString);
  }
  
  public static ValuedEnum getEnum(Class paramClass, int paramInt)
  {
    return (ValuedEnum)ValuedEnum.getEnum(paramClass, paramInt);
  }
  
  public static List getEnumList(Class paramClass)
  {
    return Enum.getEnumList(paramClass);
  }
  
  public static Map getEnumMap(Class paramClass)
  {
    return Enum.getEnumMap(paramClass);
  }
  
  public static Iterator iterator(Class paramClass)
  {
    return Enum.getEnumList(paramClass).iterator();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\commons\lang\enums\EnumUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */