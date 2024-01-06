package org.comparus.ua.constants;

public class OpenAPI {
    public static final String DEV_ENV = "Development environment";
    public static final String PROD_ENV = "Production environment";
    public static final String DEV_EMAIL = "eugene140823@gmail.com";
    public static final String DEV_NAME = "Eugene Levchenko";
    public static final String TITLE = "Aggregating users data from multiple databases API";
    public static final String VERSION = "0.0.1";
    public static final String DESCRIPTION = "This is a test project which is used for aggregating users data from multiple databases. Databases support: PostgeSQL, MySQL and Oracle";
    public static final String SUCCESS_CODE = "200";
    public static final String NOT_FOUND_CODE = "404";
    public static final String SERVER_ERROR_CODE = "500";
    public static final String SERVER_ERROR_DESCRIPTION = "Internal Server Error in case of incorrect DB config in .yml file";
    public static final String NOT_FOUND_DESCRIPTION = "User by specified id not found";
    public static final String FIND_ALL_SUMMARY = "Find All Users. It's the main endpoint of application which executes the fundamental goal of this API - aggregating users data from multiple databases.";
    public static final String FIND_BY_ID_SUMMARY = "Find User by Id. [Secondary endpoint, using for filter data]";
    public static final String FIND_BY_FILTERS_SUMMARY = "Find Users By Multiple Filters. [Secondary endpoint, using for filter data]";
    public static final String FIND_ALL_OPERATION_DESCRIPTION = "Get a List of User objects. The response a List with User objects with fields: [id, username, name and surname].";
    public static final String FIND_BY_ID_OPERATION_DESCRIPTION = "Get an User object by specifying its id. The response is User's object with fields: [id, username, name and surname].";
    public static final String FIND_BY_FILTERS_OPERATION_DESCRIPTION = "Get a List of User objects according to multiple filters. The Request Body represents all available filters, such as: [id, username, name and surname], you can leave it empty, null or delete it from request body, it'll mean that you don't want to search the User by absent filter. The response is a List with User objects with fields: [id, username, name and surname]. If no User objects are found by your applied filters, you will receive an empty list without any errors.";
}