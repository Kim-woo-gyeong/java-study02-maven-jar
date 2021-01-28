package chapter2;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class OverlySpecificBankStatementValidator {
	private String description;
	private String date;
	private String amount;
	
	public OverlySpecificBankStatementValidator(final String description, final String date, final String amount) {
		this.description = Objects.requireNonNull(description);
		this.date = Objects.requireNonNull(date);
		this.amount = Objects.requireNonNull(amount);
	}
	
	/* 너무 세밀한 예외처리 방식 */
//	public boolean validate() throws DescriptionTooLongException, 
//									 InvalidDateFormat,
//									 DateInTheFutureException,
//									 InvalidAmountException {
//		if(this.description.length() > 100) {
//			throw new DescriptionTooLongException();
//		}
//		
//		final LocalDate parseDate;
//		try {
//			parseDate = LocalDate.parse(this.date);
//		} catch(DateTimeParseException e) {
//			throw new InvalidDateFormat();
//		}
//		
//		if(parseDate.isAfter(LocalDate.now())) 
//			throw new DateInTheFutureException();
//		
//		try {
//			double.parseDouble(this.amount);
//		} catch(NumberFormatException e) {
//			throw new InvalidAmountException();
//		}
//		
//		return true;
//	}
	
	public Notification validate() {
		final Notification notification = new Notification();
		
		if(this.description.length() > 100) {
			notification.addError("the description is too long");
		}
		
		final LocalDate parseDate;
		try {
			parseDate = LocalDate.parse(this.date);
			if(parseDate.isAfter(LocalDate.now())) {
				notification.addError("date cannot be in the future");
			}
		} catch (DateTimeParseException e) {
			notification.addError("Invalid format for date");
		}
		
		final double amount;
		try {
			amount = Double.parseDouble(this.amount);
		} catch(NumberFormatException e) {
			notification.addError("Invalid format for amount");
		}
		
		return notification;
	}
}
