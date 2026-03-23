/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.math.BigDecimal;

public class LoanApplication {
    private long applicationId;
    private String fullName;
    private String product;
    private BigDecimal amount;
    private String purpose;
    private String status;
    private String priorityLevel;
    
    public long getApplicationId() {
        return applicationId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProduct() {
        return product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getStatus() {
        return status;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }
    
    // Add these setters (and corresponding getters if needed)
    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    // Optionally add getters too if you need them
}

