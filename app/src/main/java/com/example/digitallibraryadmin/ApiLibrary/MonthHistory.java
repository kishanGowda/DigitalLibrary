package com.example.digitallibraryadmin.ApiLibrary;

import java.util.ArrayList;

public class MonthHistory {
    public int id;
    public int userId;
    public int standardId;
    public String date;
    public String dueDate;
    public String paymentDate;
    public String refundDate;
    public Object transferDate;
    public String paymentId;
    public String amount;
    public double transactionFee;
    public double settlementFee;
    public double amountPayable;
    public double processignPaidByIns;
    public String org;
    public String orgId;
    public String orgName;
    public String payment_method;
    public String attempt;
    public String status;
    public ArrayList<String> trnsDetails;
    public String receipt;
    public Object callback;
    public String note;
    public String linkSentBy;
    public Object refundedBy;
    public Object transferedBy;
    public String transactionPaidBy;
    public Object gst;
    public boolean gstApplicable;
    public Object discount_details;
    public Object addition_details;
    public int total_discount;
    public int total_addition;
    public String payment_type;
    public String cheque_no;
    public User user;
    public String invoice;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getStandardId() {
        return standardId;
    }

    public String getDate() {
        return date;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getRefundDate() {
        return refundDate;
    }

    public Object getTransferDate() {
        return transferDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getAmount() {
        return amount;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public double getSettlementFee() {
        return settlementFee;
    }

    public double getAmountPayable() {
        return amountPayable;
    }

    public double getProcessignPaidByIns() {
        return processignPaidByIns;
    }

    public String getOrg() {
        return org;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public String getAttempt() {
        return attempt;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<String> getTrnsDetails() {
        return trnsDetails;
    }

    public String getReceipt() {
        return receipt;
    }

    public Object getCallback() {
        return callback;
    }

    public String getNote() {
        return note;
    }

    public String getLinkSentBy() {
        return linkSentBy;
    }

    public Object getRefundedBy() {
        return refundedBy;
    }

    public Object getTransferedBy() {
        return transferedBy;
    }

    public String getTransactionPaidBy() {
        return transactionPaidBy;
    }

    public Object getGst() {
        return gst;
    }

    public boolean isGstApplicable() {
        return gstApplicable;
    }

    public Object getDiscount_details() {
        return discount_details;
    }

    public Object getAddition_details() {
        return addition_details;
    }

    public int getTotal_discount() {
        return total_discount;
    }

    public int getTotal_addition() {
        return total_addition;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public String getCheque_no() {
        return cheque_no;
    }

    public User getUser() {
        return user;
    }

    public String getInvoice() {
        return invoice;
    }
}
