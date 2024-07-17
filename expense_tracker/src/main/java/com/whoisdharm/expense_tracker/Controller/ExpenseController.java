package com.whoisdharm.expense_tracker.Controller;

import com.whoisdharm.expense_tracker.Entity.Expense;
import com.whoisdharm.expense_tracker.Service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public String getAllExpenses(Model model) {
        List<Expense> expenses = expenseService.getAllExpenses();
        model.addAttribute("expenses", expenses);
        model.addAttribute("expense", new Expense());
        return "expenses";
    }

    @GetMapping("/edit/{id}")
    public String getExpenseById(@PathVariable Long id, Model model) {
        Optional<Expense> expense = expenseService.getExpenseById(id);
        if (expense.isPresent()) {
            model.addAttribute("expense", expense.get());
            return "expenses";  // You can create a separate template for editing if necessary
        } else {
            return "redirect:/expenses";
        }
    }

    @PostMapping
    public String createExpense(@RequestBody Expense expense) {
        expenseService.saveExpense(expense);
        return "redirect:/expenses";
    }

    @PutMapping("/update/{id}")
    public String updateExpense(@PathVariable Long id, @ModelAttribute Expense expense) {
        expense.setId(id);
        expenseService.saveExpense(expense);
        return "redirect:/expenses";
    }

    @PostMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }

}
