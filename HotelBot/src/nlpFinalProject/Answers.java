package nlpFinalProject;

public class Answers {
	public enum ClassAttributesValues {
		reservation, spa, room_service, complaints, location
	}

	public static final String openingMessage = "Hello, welcome to the W&R hotel.\n"
			+ "In case you are not satisfied with our\n"
			+ "bot's answer please try to ask him your question answer";
	public static String getTextForAttribute(ClassAttributesValues attribute) {
		switch (attribute) {
		case reservation:
			return "It looks like you are looking to make a reservation for a room.\n"
			+ "Please insert phone number and your name and last name and \n"
			+ "our receptionist will get back to you as soon as posible.\n"
			+ "Thank you and have a lovely day! \n";

		case spa:
			return "It looks like you are interested in our Spa.\n"
			+ "The spa is open everyday between 09:00-18:00.\n"
			+ "We are doing different types of massages  and other treatments.\n"
			+ "In addition the spa includes Swimming Pool, Jacuzzi, Sauna, Turkish \nHamam and a Hot Tub.\n"
			+ "We hope the information ws helpful for you !\n";

		case room_service:
			return "It seems you are interested in room service\n"
			+ "Our hotel room service is available 24/7. \n"
			+ "The room service menu is located on desk in your room.\n"
			+ "If you are interested in a room clening please press 1.\n"
			+ "To make an order, please type the word ORDER \nand write the dish name.\n"
			+ "In any other issue you can always call the reception by dial 111 and \nthey will take care for you in everything you need for your stay in \nW&R hotel.\n";

		case complaints:
			return "It looks like you are not happy in one of our services,\n"
			+ "Before you write a bad reccomendation in booking or hotel.com, \nplease let us know what made you feel upset and we will fix it!\n"
			+ "Please write us your complaint and your personal details:\n"
			+ "Room Number\n"
			+ "Name & Last Name\n"
			+ "Phone Number\n"
			+ "W&R hotel is making the efforts to get his guests satisfied, We will \nget back to you as soon as posible.\n"
			+ "Thank you for your Collaboration.\n";
			
		case location:
			return "It looks like you can't find our hotel. If that's not the case \nplease reenter your query in a different way.\n"
			+ "The W&R hotel address is Hayarkon 101 St. Tel-Aviv. \n"
			+ "For other info and parking tickets for the parking lot down the street \nplease call 03-5556667.\n"
			+ "Thank you and have a lovely day! \n";

		default: 
			return "";
		}
	}
}
