package asd.iit;

import asd.iit.budget.Budget;
import asd.iit.budget.BudgetModel;
import asd.iit.category.CategoryModel;
import asd.iit.category.TransactionCategory;
import asd.iit.transaction.RecurrentType;
import asd.iit.transaction.Transaction;
import asd.iit.transaction.TransactionModel;
import asd.iit.transaction.TransactionModelUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ExpenseTrackerController {
    private final ExpenseTrackerImpl expenseTrackerImpl;

    @Autowired
    public ExpenseTrackerController(ExpenseTrackerImpl expenseTrackerImpl) {
        this.expenseTrackerImpl = expenseTrackerImpl;


//        expenseTrackerImpl.saveTransaction("test", 1000d, "Salary", "desc", LocalDateTime.now(), null);
    }

    //1. Allow a user to see a list of recent transactions
    @GetMapping("/transaction")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = expenseTrackerImpl.printAllTransactions();
        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
    }


    //2. Allow a user to enter a new transaction. At a minimum, this should include amount, whether it is an income or
    // expense transaction and which category it falls under. A more advanced application will allow the user to enter a
    // note and to specify whether it is recurring)
    @PostMapping("/transaction")
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionModel transactionModel) {
        System.out.println(transactionModel.toString());
        expenseTrackerImpl.createTransaction(transactionModel.getTitle(), transactionModel.getAmount(), transactionModel.getCategory(), transactionModel.getDesc(), transactionModel.getDateTime(), transactionModel.getRecurrentType());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //3. Allow the user to edit/delete transactions
    @DeleteMapping("delete/transaction/{id}")
    public ResponseEntity deleteTransaction(@PathVariable String id) {
        expenseTrackerImpl.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    //3.1. Allow the user to edit/delete transactions
    @PutMapping("edit/transaction")
    public ResponseEntity editTransaction(@RequestBody TransactionModelUpdate transactionModelUpdate) {
        expenseTrackerImpl.updateTransaction(transactionModelUpdate);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    //4. Allow the user to see a list of categories. The application should come with some preset categories.
    @GetMapping("/category")
    public ResponseEntity<List<TransactionCategory>> getAllCategory() {
        List<TransactionCategory> category = expenseTrackerImpl.printAllCategories();
        return new ResponseEntity<List<TransactionCategory>>(category, HttpStatus.OK);
    }

    //4.1 An advanced application will allow the user to add new categories.
    @PostMapping("/category")
    public ResponseEntity<Budget> createCategory(@RequestBody CategoryModel categoryModel) {
        expenseTrackerImpl.createCategory(categoryModel.getType(), categoryModel.getName(), categoryModel.getIconUrl(), categoryModel.getBudget());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //5. Allow the user to enter a budget, specifying amounts for each category.
    @PostMapping("/budget")
    public ResponseEntity<Budget> createBudget(@RequestBody BudgetModel budgetModel) {
        expenseTrackerImpl.createABudget(budgetModel.getName(), budgetModel.getAlertAmount(), budgetModel.getTotalBudgetAmount());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //5.1
    @GetMapping("/budget")
    public ResponseEntity<List<Budget>> getAllBudgets() {
        List<Budget> budgets = expenseTrackerImpl.getAllBudgets();
        return new ResponseEntity<List<Budget>>(budgets, HttpStatus.OK);
    }


    //6. Allow the user to track their progress against their budget by seeing how much they have spent in each category
    // against the budget for that category, as well as overall spending against overall budget.
    @GetMapping("/progress")
    public ResponseEntity<List<String>> checkProgress() {
        List<String> analyseTransactions = expenseTrackerImpl.analyseTransactions();
        return new ResponseEntity<List<String>>(analyseTransactions, HttpStatus.OK);
    }

    //6.1  as well as overall spending against overall budget.
    @GetMapping("/usage")
    public ResponseEntity<String> checkBudgetUsage() {
        String budgetConsumption = expenseTrackerImpl.calculateOverallBudgetConsumption();
        return new ResponseEntity<String>(budgetConsumption, HttpStatus.OK);
    }

    @GetMapping("/recurrent-types")
    public ResponseEntity<List<RecurrentType>> getAllRecurrentTypes() {
        List<RecurrentType> recurrentTypes = Arrays.asList(RecurrentType.values());
        return ResponseEntity.ok(recurrentTypes);
    }

}



