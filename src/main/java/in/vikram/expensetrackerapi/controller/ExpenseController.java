package in.vikram.expensetrackerapi.controller;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.vikram.expensetrackerapi.Service.ExpenseService;
import in.vikram.expensetrackerapi.entity.Expense;
import jakarta.validation.Valid;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService eService;
	
	//Adding the expenses in the DB
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/expenses")
	public void saveExpenseDetails(@Valid @RequestBody Expense expense) {
		eService.saveExpenseDetails(expense);
		System.out.println("Printing the expence object:: "+ expense);
	}
	
	// Fetching all expenses details from DB
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/expenses")
	public List<Expense> getExpenses(Pageable page){
		return eService.getAllExpenses(page).toList();
	}
	
	// Filtering expense details based on Id
	@GetMapping("/expenses/{id}")
	public Expense getExpensesById(@PathVariable Long id) {
		return eService.getExpenseByExpenseId(id);
	}
	
	
	// Deleting the Expense Resource from the DB
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/expenses")
	public String deleteById(@RequestParam("id") Long id) {
		eService.deleteByExpensesId(id);
		return "Delete by id " + id;
	}
	
	// Updating the Expense based on Id
	@PutMapping("/expenses/{id}")
	public Expense updateExpenseDetails(@PathVariable Long id ,@RequestBody Expense expense) {
		return eService.updateExpenseDetails(id, expense);
	}
	
	
	// Filtering the expense on the basis of category
	@GetMapping("/expenses/category")
	public List<Expense> getExpensesBasedOnCategory(@RequestParam String category ,Pageable page){
		return eService.getExpenseByCategory(category,page);
	}
	
	
	// Filtering the expense based on name
	@GetMapping("/expenses/name")
	public List<Expense> getExpensesByName(@RequestParam String keyword,Pageable page){
		return eService.getExpensesByName(keyword, page);
	}
	
	// Filtering the expense based on date
	@GetMapping("/expenses/date")
	public List<Expense> getByDatExpenses(@RequestParam(required = false) Date startDate,@RequestParam(required = false) Date endDate,Pageable page){
		return eService.readByDate(startDate, endDate, page);
	}
	
	
	
	
	
}
