package test4;

import java.util.ArrayList;

abstract class Animal{
	public void move() {
		System.out.println("동물이 움직입니다");
	}
	
	abstract public void act();
	
}

class Human extends Animal{
	public void move() {
		System.out.println("사람이 두 발로 걷습니다");
	}
	
	@Override
	public void act() {
		System.out.println("사람이 책을 읽습니다.");
	}
	
	// '@Override' 표기는 필수는 아니지만, 사용을 권장함.
	// 1. 부모 클래스 메서드와 이름이 다른 경우 오버라이딩이 잘못 일어나는 현상 방지
	// 2. 코드 가독성 증가
	// 3. 일관성 유지
}


class Tiger extends Animal{
	public void move() {
		System.out.println("호랑이가 네 발로 뜁니다");
	}
	
	@Override
	public void act() {
		System.out.println("호랑이가 사냥을 합니다");
	}
} 

class Eagle extends Animal{
	public void move() {
		System.out.println("독수리가 하늘을 납니다");
	}
	
	@Override
	public void act() {
		System.out.println("독수리가 날개를 쭉펴고 멀리 날아갑니다.");
	}
}
public class AnimalTest2 {
ArrayList<Animal> aniList = new ArrayList<Animal>();
	
	public static void main(String[] args) {
		AnimalTest2 aTest = new AnimalTest2();
		aTest.addAnimal();
		System.out.println("원래 형으로 다운 케스팅");
		aTest.testCasting();
		// TODO Auto-generated method stub
	}
	
	public void addAnimal() {
		aniList.add(new Human());
		aniList.add(new Tiger());
		aniList.add(new Eagle());
		
		for(Animal ani: aniList) {
			ani.move();
		}
	}
	
	public void testCasting() {
		for(int i =0; i<aniList.size(); i++) {
			Animal ani = aniList.get(i);
			ani.act();
			
			if (ani instanceof Human) {
				Human h = (Human)ani;
			}
			
			else if (ani instanceof Tiger) {
				Tiger t = (Tiger)ani;
			}
			
			else if (ani instanceof Eagle) {
				Eagle e = (Eagle)ani;
			}
			
			else {
				System.out.println("지원되지 않는 형입니다.");
			}
		}
	}
}
