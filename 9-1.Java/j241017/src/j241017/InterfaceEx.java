package j241017;

public class InterfaceEx {

	public static void main(String[] args) {
		SamsungPhone phone = new SamsungPhone();
		
		phone.printLogo();
		phone.sendCall();
		phone.receiveCall();
		phone.flash();
		phone.recieveMessage();
		
		System.out.println("-----------------------");
		SamsungPhone sphone = new SamsungPhone();
		int sum = sphone.calculate(100, 200);
		System.out.println("100과 200를 더하면 "+sum);
		
		sphone.printLogo();
		sphone.sendCall();
		sphone.receiveCall();
		sphone.flash();
		sphone.recieveMessage();
	}

}
