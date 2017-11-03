package common;

public class Search {

	private String field;
	private String word;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	@Override
	public String toString() {
		return "Search [field=" + field + ", word=" + word + "]";
	}
	
	
	
}
