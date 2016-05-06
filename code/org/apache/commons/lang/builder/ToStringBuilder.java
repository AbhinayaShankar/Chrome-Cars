package org.apache.commons.lang.builder;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ObjectUtils;

public class ToStringBuilder
{
  private static volatile ToStringStyle defaultStyle = ToStringStyle.DEFAULT_STYLE;
  private final StringBuffer buffer;
  private final Object object;
  private final ToStringStyle style;
  
  public ToStringBuilder(Object paramObject)
  {
    this(paramObject, null, null);
  }
  
  public ToStringBuilder(Object paramObject, ToStringStyle paramToStringStyle)
  {
    this(paramObject, paramToStringStyle, null);
  }
  
  public ToStringBuilder(Object paramObject, ToStringStyle paramToStringStyle, StringBuffer paramStringBuffer)
  {
    ToStringStyle localToStringStyle = paramToStringStyle;
    if (paramToStringStyle == null) {
      localToStringStyle = getDefaultStyle();
    }
    paramToStringStyle = paramStringBuffer;
    if (paramStringBuffer == null) {
      paramToStringStyle = new StringBuffer(512);
    }
    this.buffer = paramToStringStyle;
    this.style = localToStringStyle;
    this.object = paramObject;
    localToStringStyle.appendStart(paramToStringStyle, paramObject);
  }
  
  public static ToStringStyle getDefaultStyle()
  {
    return defaultStyle;
  }
  
  public static String reflectionToString(Object paramObject)
  {
    return ReflectionToStringBuilder.toString(paramObject);
  }
  
  public static String reflectionToString(Object paramObject, ToStringStyle paramToStringStyle)
  {
    return ReflectionToStringBuilder.toString(paramObject, paramToStringStyle);
  }
  
  public static String reflectionToString(Object paramObject, ToStringStyle paramToStringStyle, boolean paramBoolean)
  {
    return ReflectionToStringBuilder.toString(paramObject, paramToStringStyle, paramBoolean, false, null);
  }
  
  public static String reflectionToString(Object paramObject, ToStringStyle paramToStringStyle, boolean paramBoolean, Class paramClass)
  {
    return ReflectionToStringBuilder.toString(paramObject, paramToStringStyle, paramBoolean, false, paramClass);
  }
  
  public static void setDefaultStyle(ToStringStyle paramToStringStyle)
  {
    if (paramToStringStyle == null) {
      throw new IllegalArgumentException("The style must not be null");
    }
    defaultStyle = paramToStringStyle;
  }
  
  public ToStringBuilder append(byte paramByte)
  {
    this.style.append(this.buffer, null, paramByte);
    return this;
  }
  
  public ToStringBuilder append(char paramChar)
  {
    this.style.append(this.buffer, null, paramChar);
    return this;
  }
  
  public ToStringBuilder append(double paramDouble)
  {
    this.style.append(this.buffer, null, paramDouble);
    return this;
  }
  
  public ToStringBuilder append(float paramFloat)
  {
    this.style.append(this.buffer, null, paramFloat);
    return this;
  }
  
  public ToStringBuilder append(int paramInt)
  {
    this.style.append(this.buffer, null, paramInt);
    return this;
  }
  
  public ToStringBuilder append(long paramLong)
  {
    this.style.append(this.buffer, null, paramLong);
    return this;
  }
  
  public ToStringBuilder append(Object paramObject)
  {
    this.style.append(this.buffer, null, paramObject, null);
    return this;
  }
  
  public ToStringBuilder append(String paramString, byte paramByte)
  {
    this.style.append(this.buffer, paramString, paramByte);
    return this;
  }
  
  public ToStringBuilder append(String paramString, char paramChar)
  {
    this.style.append(this.buffer, paramString, paramChar);
    return this;
  }
  
  public ToStringBuilder append(String paramString, double paramDouble)
  {
    this.style.append(this.buffer, paramString, paramDouble);
    return this;
  }
  
  public ToStringBuilder append(String paramString, float paramFloat)
  {
    this.style.append(this.buffer, paramString, paramFloat);
    return this;
  }
  
  public ToStringBuilder append(String paramString, int paramInt)
  {
    this.style.append(this.buffer, paramString, paramInt);
    return this;
  }
  
  public ToStringBuilder append(String paramString, long paramLong)
  {
    this.style.append(this.buffer, paramString, paramLong);
    return this;
  }
  
  public ToStringBuilder append(String paramString, Object paramObject)
  {
    this.style.append(this.buffer, paramString, paramObject, null);
    return this;
  }
  
  public ToStringBuilder append(String paramString, Object paramObject, boolean paramBoolean)
  {
    this.style.append(this.buffer, paramString, paramObject, BooleanUtils.toBooleanObject(paramBoolean));
    return this;
  }
  
  public ToStringBuilder append(String paramString, short paramShort)
  {
    this.style.append(this.buffer, paramString, paramShort);
    return this;
  }
  
  public ToStringBuilder append(String paramString, boolean paramBoolean)
  {
    this.style.append(this.buffer, paramString, paramBoolean);
    return this;
  }
  
  public ToStringBuilder append(String paramString, byte[] paramArrayOfByte)
  {
    this.style.append(this.buffer, paramString, paramArrayOfByte, null);
    return this;
  }
  
  public ToStringBuilder append(String paramString, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    this.style.append(this.buffer, paramString, paramArrayOfByte, BooleanUtils.toBooleanObject(paramBoolean));
    return this;
  }
  
  public ToStringBuilder append(String paramString, char[] paramArrayOfChar)
  {
    this.style.append(this.buffer, paramString, paramArrayOfChar, null);
    return this;
  }
  
  public ToStringBuilder append(String paramString, char[] paramArrayOfChar, boolean paramBoolean)
  {
    this.style.append(this.buffer, paramString, paramArrayOfChar, BooleanUtils.toBooleanObject(paramBoolean));
    return this;
  }
  
  public ToStringBuilder append(String paramString, double[] paramArrayOfDouble)
  {
    this.style.append(this.buffer, paramString, paramArrayOfDouble, null);
    return this;
  }
  
  public ToStringBuilder append(String paramString, double[] paramArrayOfDouble, boolean paramBoolean)
  {
    this.style.append(this.buffer, paramString, paramArrayOfDouble, BooleanUtils.toBooleanObject(paramBoolean));
    return this;
  }
  
  public ToStringBuilder append(String paramString, float[] paramArrayOfFloat)
  {
    this.style.append(this.buffer, paramString, paramArrayOfFloat, null);
    return this;
  }
  
  public ToStringBuilder append(String paramString, float[] paramArrayOfFloat, boolean paramBoolean)
  {
    this.style.append(this.buffer, paramString, paramArrayOfFloat, BooleanUtils.toBooleanObject(paramBoolean));
    return this;
  }
  
  public ToStringBuilder append(String paramString, int[] paramArrayOfInt)
  {
    this.style.append(this.buffer, paramString, paramArrayOfInt, null);
    return this;
  }
  
  public ToStringBuilder append(String paramString, int[] paramArrayOfInt, boolean paramBoolean)
  {
    this.style.append(this.buffer, paramString, paramArrayOfInt, BooleanUtils.toBooleanObject(paramBoolean));
    return this;
  }
  
  public ToStringBuilder append(String paramString, long[] paramArrayOfLong)
  {
    this.style.append(this.buffer, paramString, paramArrayOfLong, null);
    return this;
  }
  
  public ToStringBuilder append(String paramString, long[] paramArrayOfLong, boolean paramBoolean)
  {
    this.style.append(this.buffer, paramString, paramArrayOfLong, BooleanUtils.toBooleanObject(paramBoolean));
    return this;
  }
  
  public ToStringBuilder append(String paramString, Object[] paramArrayOfObject)
  {
    this.style.append(this.buffer, paramString, paramArrayOfObject, null);
    return this;
  }
  
  public ToStringBuilder append(String paramString, Object[] paramArrayOfObject, boolean paramBoolean)
  {
    this.style.append(this.buffer, paramString, paramArrayOfObject, BooleanUtils.toBooleanObject(paramBoolean));
    return this;
  }
  
  public ToStringBuilder append(String paramString, short[] paramArrayOfShort)
  {
    this.style.append(this.buffer, paramString, paramArrayOfShort, null);
    return this;
  }
  
  public ToStringBuilder append(String paramString, short[] paramArrayOfShort, boolean paramBoolean)
  {
    this.style.append(this.buffer, paramString, paramArrayOfShort, BooleanUtils.toBooleanObject(paramBoolean));
    return this;
  }
  
  public ToStringBuilder append(String paramString, boolean[] paramArrayOfBoolean)
  {
    this.style.append(this.buffer, paramString, paramArrayOfBoolean, null);
    return this;
  }
  
  public ToStringBuilder append(String paramString, boolean[] paramArrayOfBoolean, boolean paramBoolean)
  {
    this.style.append(this.buffer, paramString, paramArrayOfBoolean, BooleanUtils.toBooleanObject(paramBoolean));
    return this;
  }
  
  public ToStringBuilder append(short paramShort)
  {
    this.style.append(this.buffer, null, paramShort);
    return this;
  }
  
  public ToStringBuilder append(boolean paramBoolean)
  {
    this.style.append(this.buffer, null, paramBoolean);
    return this;
  }
  
  public ToStringBuilder append(byte[] paramArrayOfByte)
  {
    this.style.append(this.buffer, null, paramArrayOfByte, null);
    return this;
  }
  
  public ToStringBuilder append(char[] paramArrayOfChar)
  {
    this.style.append(this.buffer, null, paramArrayOfChar, null);
    return this;
  }
  
  public ToStringBuilder append(double[] paramArrayOfDouble)
  {
    this.style.append(this.buffer, null, paramArrayOfDouble, null);
    return this;
  }
  
  public ToStringBuilder append(float[] paramArrayOfFloat)
  {
    this.style.append(this.buffer, null, paramArrayOfFloat, null);
    return this;
  }
  
  public ToStringBuilder append(int[] paramArrayOfInt)
  {
    this.style.append(this.buffer, null, paramArrayOfInt, null);
    return this;
  }
  
  public ToStringBuilder append(long[] paramArrayOfLong)
  {
    this.style.append(this.buffer, null, paramArrayOfLong, null);
    return this;
  }
  
  public ToStringBuilder append(Object[] paramArrayOfObject)
  {
    this.style.append(this.buffer, null, paramArrayOfObject, null);
    return this;
  }
  
  public ToStringBuilder append(short[] paramArrayOfShort)
  {
    this.style.append(this.buffer, null, paramArrayOfShort, null);
    return this;
  }
  
  public ToStringBuilder append(boolean[] paramArrayOfBoolean)
  {
    this.style.append(this.buffer, null, paramArrayOfBoolean, null);
    return this;
  }
  
  public ToStringBuilder appendAsObjectToString(Object paramObject)
  {
    ObjectUtils.identityToString(getStringBuffer(), paramObject);
    return this;
  }
  
  public ToStringBuilder appendSuper(String paramString)
  {
    if (paramString != null) {
      this.style.appendSuper(this.buffer, paramString);
    }
    return this;
  }
  
  public ToStringBuilder appendToString(String paramString)
  {
    if (paramString != null) {
      this.style.appendToString(this.buffer, paramString);
    }
    return this;
  }
  
  public Object getObject()
  {
    return this.object;
  }
  
  public StringBuffer getStringBuffer()
  {
    return this.buffer;
  }
  
  public ToStringStyle getStyle()
  {
    return this.style;
  }
  
  public String toString()
  {
    if (getObject() == null) {
      getStringBuffer().append(getStyle().getNullText());
    }
    for (;;)
    {
      return getStringBuffer().toString();
      this.style.appendEnd(getStringBuffer(), getObject());
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\commons\lang\builder\ToStringBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */