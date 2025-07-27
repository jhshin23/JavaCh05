package ch05;
//Page327, P12
abstract class PairMap {
	protected String keyArray[];
	protected String valueArray[];
	abstract public String get(String key);
	abstract public void put(String key, String value);
	abstract public String delete(String key);
	abstract public int length();
}

class Dictionary extends PairMap {
	public Dictionary(int size) {
		keyArray = new String[size];
		valueArray = new String[size];
		for(int i = 0 ; i < keyArray.length ; i++) {
			keyArray[i] = null;
			valueArray[i] = null;
		}
	}
	@Override
	public String get(String key) {
		int i;
		for(i = 0 ; i < keyArray.length ; i++) {
			if (keyArray[i] != null && keyArray[i].equals(key))
				break;
		}
		
		if(i == keyArray.length) return null;

		return valueArray[i];
	}

	@Override
	public void put(String key, String value) {
		int i;
		for (i = 0 ; i < keyArray.length ; i++) {
			if (keyArray[i] == null || keyArray[i].equals(key))
				break;
		}
		if(i == keyArray.length) return;
		keyArray[i] = key;
		valueArray[i] = value;
		
	}

	@Override
	public String delete(String key) {
		int i;
		String s;
		for(i = 0 ; i < keyArray.length ; i++) {
			if (keyArray[i] != null && keyArray[i].equals(key))
				break;
		}
		if(i == keyArray.length) return null;
		
		s = valueArray[i];
		keyArray[i] = null;
		valueArray[i] = null;
		return s;
	}

	@Override
	public int length() {
		 int cnt = 0;;
		for (int i = 0 ; i < keyArray.length ; i++) {
			if (keyArray[i] != null)
				cnt++;
		}
		return cnt;
	}
	
}
public class DictionaryApp {

	public static void main(String[] args) {
		Dictionary dic = new Dictionary(10);
		
		dic.put("황기태", "자바");
		dic.put("이재문", "파이선");
		dic.put("이재문", "C++");
		
		System.out.println("이재문의 값은 " + dic.get("이재문"));
		System.out.println("황기태의 값은 " + dic.get("황기태"));
		dic.delete("황기태");
		System.out.println("황기태의 값은 " + dic.get("황기태"));
	}

}
