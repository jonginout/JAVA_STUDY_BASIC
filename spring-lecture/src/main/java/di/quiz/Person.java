package di.quiz;

public class Person{
	
	private Animal animal;
	
	public void setAnimal(Animal animal) {
		System.out.println("setAnimal");
		this.animal = animal;
	}
	
	public Animal getAnimal() {
		System.out.println("getAnimal");
		return animal;
	}
	
}
