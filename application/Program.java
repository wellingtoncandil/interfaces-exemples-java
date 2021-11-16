package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:ss");
		
		System.out.println("Enter rental data: ");
		System.out.print("Car model: ");
		String carModel = sc.nextLine();
		System.out.print("Pickup (dd/MM/yyyy hh:ss): ");
		Date start =  sdf.parse(sc.nextLine());
		System.out.println("Return (dd/MM/yyyy hh:ss):");
		Date finish = sdf.parse(sc.nextLine());
		System.out.println("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.println("Enter price per day: ");
		double pricePerDay = sc.nextDouble();
		
		CarRental carRental = new CarRental(start, finish, new Vehicle(carModel));
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		rentalService.processInvoice(carRental);
		
		System.out.println("INVOICE");
		System.out.println("Basic Payment: "+carRental.getInvoice().getBasicPayment());
		System.out.println("Tax: "+carRental.getInvoice().getTax());
		System.out.println("Total Payment: "+carRental.getInvoice().getTotalPayment());
		
		sc.close();
	}
}
