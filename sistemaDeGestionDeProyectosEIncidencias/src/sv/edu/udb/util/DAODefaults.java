/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.util;

/**
 *
 * @author Rick
 */
public enum DAODefaults {
    NON_EXISTING_USER("Non-existing user"),
    NON_EXISTING_REQUEST_TYPE("Non-existing request type"),
    NO_LAST_REQUEST_FOUND("No last request was found on the DB");

    private String defaultValue;

    private DAODefaults(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
