package in.vikram.expensetrackerapi.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.vikram.expensetrackerapi.Service.ExpenseService;
import in.vikram.expensetrackerapi.entity.Expense;
import in.vikram.expensetrackerapi.exception.ResourceNotFoundException;
import in.vikram.expensetrackerapi.repo.ExpenseRepository;


@Service
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	private ExpenseRepository repo;
	
	@Override
	public Page<Expense> getAllExpenses(Pageable page) {
		return repo.findAll(page);
	}

	@Override
	public Expense getExpenseByExpenseId(Long id) {
		Optional<Expense> expense = repo.findById(id);
		if(expense.isPresent()) {
			return expense.get();
		}
		throw new ResourceNotFoundException("Resource not found exception for the id " +id);
		
	}

	@Override
	public void deleteByExpensesId(Long id) {
		repo.deleteById(id);
		throw new ResourceNotFoundException("There is no resource to delete with id  "+id);
	}

	@Override
	public void saveExpenseDetails(Expense expense) {
		repo.save(expense);
	}

	@Override
	public Expense updateExpenseDetails(Long id, Expense expense) {
		Expense existingExpense  = getExpenseByExpenseId(id);
		existingExpense.setName(expense.getName()!=null ? expense.getName() :existingExpense.getName());
		existingExpense.setDescription(expense.getDescription()!=null ? expense.getDescription():existingExpense.getDescription());
	    existingExpense.setAmount(expense.getAmount()!=null?expense.getAmount():existingExpense.getAmount());
	    existingExpense.setCategory(expense.getCategory()!=null ? expense.getCategory():existingExpense.getCategory());
	    existingExpense.setDate(expense.getDate()!=null?expense.getDate():existingExpense.getDate());
	
	    return repo.save(existingExpense);
	
	
	}

	@Override
	public List<Expense> getExpenseByCategory(String category, Pageable page) {
		return repo.findByCategory(category, page).toList();
	}

	@Override
	public List<Expense> getExpensesByName(String keyword, Pageable page) {
		return repo.findByNameContaining(keyword, page).toList();
	}

	@Override
	public List<Expense> readByDate(Date startDate, Date endDate, Pageable page) {
		// TODO Auto-generated method stub
		if(startDate==null) {
			startDate = new Date(0);
		}
		if(endDate==null) {
			endDate = new Date(System.currentTimeMillis());
		}
		return repo.findByDateBetween(startDate, endDate, page).toList();
	}


}
