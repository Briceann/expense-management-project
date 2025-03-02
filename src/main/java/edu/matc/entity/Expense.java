package edu.matc.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Class for expenses
 * @author Btaneh
 */
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int expenseId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private String category;
    private int amount;
    private LocalDate date;
    private String description;

    /**
     * Get category
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set category
     *
     * @param category the category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * get amount
     *
     * @return the anount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Set amount
     *
     * @param amount the amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * get date
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * set date
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Get description
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get user
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set user
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", user=" + user +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}

