package com.qa.ecs.enums;

/**
 * This Enum used to provide Easily Accessed UserDefined Enum for App Defined Values
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2023-06-20
 */

public enum DashboardLinks {
    HOME("Home (Automation user)");

    public final String value;

    private DashboardLinks(String linkName) {
        this.value = linkName;
    }
}