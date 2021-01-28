package chapter2;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {
	private final List<BankTransaction> bankTransactions;
	
	public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}
	
	public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
		double result = 0;
		
		for(final BankTransaction bankTransaction : bankTransactions) {
			result = bankTransactionSummarizer.summarize(result, bankTransaction);
		}
		
		return result;
	}
	
	public double calculateTotalAmount() { // 총합
		double total = 0;
		
		for(final BankTransaction bankTransaction : bankTransactions) {
			total += bankTransaction.getAmount();
		}
		
		return total;
	}
	
	public double calculateTotalInMonth(final Month month) { //해당 월
//		double total = 0;
//		for(final BankTransaction bankTransaction : bankTransactions) {
//			if(bankTransaction.getDate().getMonth() == month) {
//				total += bankTransaction.getAmount();
//			}
//		}
//		
//		return total;
		
		return summarizeTransactions((acc, bankTransaction) -> 
			bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc);
	}
	
	public double calculateTotalForCategory(final String category) { //카테고리별
		double total = 0;
		
		for(final BankTransaction bankTransaction : bankTransactions) {
			if(bankTransaction.getDescription().equals(category)) {
				total += bankTransaction.getAmount();
			}
		}
		
		return total;
	}
	
	public List<BankTransaction> findTransations(final BankTransactionFilter bankTransactionFilter){
//		final List<BankTransaction> result = new ArrayList<>();
//		
//		for(final BankTransaction bankTransaction : bankTransactions) {
//			if(bankTransactionFilter.test(bankTransaction)) {
//				result.add(bankTransaction);
//			}
//		}
//		
//		return result;
		final List<BankTransaction> result = new ArrayList<>();
		
		for(final BankTransaction bankTransaction : bankTransactions) {
			if(bankTransactionFilter.test(bankTransaction)) {
				result.add(bankTransaction);
			}
		}
		
		return bankTransactions;
	}
	
	public List<BankTransaction> findTransactionGreaterThanEqual(final int amount) {
		return findTransations(bankTransaction -> bankTransaction.getAmount() >= amount);
	}
		
	
	
}
