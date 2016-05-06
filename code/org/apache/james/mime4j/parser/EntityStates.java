package org.apache.james.mime4j.parser;

public abstract interface EntityStates
{
  public static final int T_BODY = 12;
  public static final int T_END_BODYPART = 11;
  public static final int T_END_HEADER = 5;
  public static final int T_END_MESSAGE = 1;
  public static final int T_END_MULTIPART = 7;
  public static final int T_END_OF_STREAM = -1;
  public static final int T_EPILOGUE = 9;
  public static final int T_FIELD = 4;
  public static final int T_PREAMBLE = 8;
  public static final int T_RAW_ENTITY = 2;
  public static final int T_START_BODYPART = 10;
  public static final int T_START_HEADER = 3;
  public static final int T_START_MESSAGE = 0;
  public static final int T_START_MULTIPART = 6;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\parser\EntityStates.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */