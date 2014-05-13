package test.objectio;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.util.List;

public class Writer {

	public static void main(String[] args) throws Exception {
		String filename = "obj.out";
		
//		ObjectOutput out = new ObjectOutputStream(new FileOutputStream(filename));
//		List<String> list = new ArrayList<String>();
//		list.add("aa");
//		list.add("bb");
//		list.add("cc");
//		Obj o = new Obj("test", 11, list);
//		o.writeExternal(out);
//		out.flush();
//		out.close();
		
		ObjectInput in = new ObjectInputStream(new FileInputStream(filename));
		Obj o1 = new Obj();
		o1.readExternal(in);
		System.out.println(o1);
	}

}

class Obj implements Externalizable {

	private String name;
	private int age;
	private List<String> friends;
	
	public Obj() {
	}

	public Obj(String name, int age, List<String> friends) {
		this.name = name;
		this.age = age;
		this.friends = friends;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(name);
		out.writeInt(age);
		out.writeObject(friends);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		name = in.readUTF();
		age = in.readInt();
		friends = (List<String>)in.readObject();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("name:")
			.append(name).append(";age:").append(age);
		sb.append(";firends:");
		for (String s : friends) {
			sb.append(s).append(",");
		}
		return sb.toString();
	}
}