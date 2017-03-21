import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class User{
	private String name;
	private Integer order;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
}

public class CollectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User u1=new User();
		u1.setName("aa");
		u1.setOrder(1);
		User u2=new User();
		u2.setName("bb");
		u2.setOrder(2);
		User u3=new User();
		u3.setName("ac");
		u3.setOrder(3);
		List<User> list=new ArrayList<User>();
		list.add(u1);
		list.add(u2);
		list.add(u3);
		
		Collections.sort(list,new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				//return 0;
				//return o1.getName().compareTo(o2.getName());
				return o1.getOrder().compareTo(o2.getOrder());
			}
		});
		
		for(User user:list){
			System.out.println(user.getName());
		}
	}

}
