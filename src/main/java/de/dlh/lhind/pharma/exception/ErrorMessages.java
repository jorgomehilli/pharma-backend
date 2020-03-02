package de.dlh.lhind.pharma.exception;

public enum ErrorMessages {

    PRODUCT_OUT_OF_STOCK("This product is out of stock"),
    EMAIL_TAKEN("This email is already taken!"),
    BAD_CREDENTIALS("Wrong Username or Password!");
    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
