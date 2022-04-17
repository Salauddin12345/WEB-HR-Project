import com.thinking.machines.hr.dl.*;

class psp
{
public static void main(String gg[])
{
try
{
AdministratorDTO administratorDTO=(new AdministratorDAO()).getByUserName("admin");
System.out.println("data saved");
System.out.println("user Name : "+administratorDTO.getUserName());
System.out.println("password  : "+administratorDTO.getPassword());
}catch(Exception e)
{
System.out.println("cool;");
System.out.println(e);
}
}
}
