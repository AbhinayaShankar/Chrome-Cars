package twitter4j;

public class JSONException
  extends Exception
{
  private static final long serialVersionUID = -4144585377907783745L;
  private Throwable cause;
  
  public JSONException(String paramString)
  {
    super(paramString);
  }
  
  public JSONException(Throwable paramThrowable)
  {
    super(paramThrowable.getMessage());
    this.cause = paramThrowable;
  }
  
  public Throwable getCause()
  {
    return this.cause;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\JSONException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */