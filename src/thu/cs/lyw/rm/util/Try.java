package thu.cs.lyw.rm.util;

import java.lang.reflect.Field;

class Try2{
	@SuppressWarnings("unused")
	private String a,b,c;
}

public class Try{
	public static void main(String[] args) {
		try {
			Class<?> demo = Class.forName("thu.cs.lyw.rm.util.Try2");
			Field fields[] = demo.getDeclaredFields();
			for (int i = 0 ; i < fields.length ; i++){
				System.out.print(fields[i].getName()+" ");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
