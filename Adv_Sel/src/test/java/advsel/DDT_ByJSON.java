package advsel;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DDT_ByJSON {

	public static void main(String[] args) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader file = new FileReader("C:\\Users\\sa287\\OneDrive - DXC Production\\Desktop\\J_Run\\Adv_Sel\\src\\test\\resources\\Json_file.json");
		Object javaobj = parser.parse(file);
		
		JSONObject obj = (JSONObject)javaobj;
		String name = obj.get("name").toString();
		String id = obj.get("id").toString();
		Object id1 = obj.get("id");
		String branch  = obj.get("Branch").toString();
		String Age = obj.get("Age").toString();
		String isStudent = obj.get("isStudent").toString();
		//will get error because cannot convert null value to String 
		//String backlogs = obj.get("backlog").toString();
		Object backlogs = obj.get("backlog");
		
		System.out.println("Name = " + name);
		System.out.println("ID = " + id);
		System.out.println("ID1 = " + id1);
		System.out.println("Branch = " + branch);
		System.out.println("Age = " + Age);
		System.out.println("Is student? = " + isStudent);
		System.out.println("Has backlog = " + backlogs);

	}

}
