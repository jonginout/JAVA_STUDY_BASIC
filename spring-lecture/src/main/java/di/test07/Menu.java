package di.test07;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Menu {
	
	//////////////////////////////////////////////////////

	private List<ISand> sandList;
	public void setSandList(List<ISand> sandList) {
		this.sandList = sandList;
	}
	
	public void printList() {
		for (ISand iSand : sandList) {
			iSand.info();
		}
	}

	//////////////////////////////////////////////////////
	
	// set은 중복 비허용
	private Set<ISand> sandSet;
	public void setSandSet(Set<ISand> sandSet) {
		this.sandSet = sandSet;
	}
	
	public void printSet() {
		for (ISand iSand : sandSet) {
			iSand.info();
		}
	}

	//////////////////////////////////////////////////////
	
	private Map<String, ISand> sandMap;
	public void setSandMap(Map<String, ISand> sandMap) {
		this.sandMap = sandMap;
	}
	
	public void printMap() {
		for (String key : sandMap.keySet()) {
			System.out.println(key);
			sandMap.get(key).info();
		}
	}

	//////////////////////////////////////////////////////

	// Properties는 스트링, 스트링 맵이라고 보면된다.
	private Properties sandProp;
	public void setSandProp(Properties sandProp) {
		this.sandProp = sandProp;
	}
	
	public void printProp() {
		for (Object key : sandProp.keySet()) {
			System.out.println(sandProp.get(key));
		}
	}
	
}
