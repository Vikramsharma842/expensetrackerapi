package in.vikram.expensetrackerapi.Service;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.vikram.expensetrackerapi.entity.Expense;

public interface ExpenseService {


	public Expense getExpenseByExpenseId(Long id);

	public void deleteByExpensesId(Long id);

	public void saveExpenseDetails(Expense expense);
	
	public Expense updateExpenseDetails(Long id, Expense expense);

	Page<Expense> getAllExpenses(Pageable page);

	public List<Expense> getExpenseByCategory(String category, Pageable page);
	
	public List<Expense> getExpensesByName(String keyword,Pageable page);
	
	public List<Expense> readByDate(Date startDate,Date endDate,Pageable page);

}
