package pageUIs;

public class AdminPageUI {
    public static final String ADMIN_HEADER= "xpath=//h6[text()='Admin']";

    public static final String ADD_BUTTON = "xpath=//button[contains(.,'Add')]";
    public static final String SEARCH_USERNAME = "xpath=//label[normalize-space()='Username']/ancestor::div[contains(@class,'oxd-input-group')]//input";
    public static final String SEARCH_BUTTON = "xpath=//button[normalize-space()='Search']";
    public static final String FORM_LOADER = "xpath=//div[contains(@class,'oxd-form-loader')]";

    public static final String RESULT_1_RECORD_FOUND = "xpath=//span[text()='(1) Record Found']";
    public static final String DYNAMIC_RESULT_ROW = "xpath=//div[@class='oxd-table-card']//div[text()='%s']";

    public static final String ADD_USER_ROLE_PARENT_DROPDOWN = "xpath=//label[contains(.,'User Role')]/parent::div/following-sibling::div//div[contains(text(),'Select')]";
    public static final String ADD_USER_ROLE_CHILDREN_ITEMS = "xpath=//div[@role='option']/span";

    public static final String STATUS_PARENT_DROPDOWN = "xpath=//label[contains(.,'Status')]/parent::div/following-sibling::div//div[contains(text(),'Select')]";
    public static final String STATUS_CHILDREN_ITEMS = "xpath=//div[@role='option']/span";

    public static final String USERNAME_TEXTBOX = "xpath=//label[contains(.,'Username')]/parent::div/following-sibling::div/input";
    public static final String PASSWORD_TEXTBOX = "xpath=//label[text()='Password']/parent::div/following-sibling::div/input";
    public static final String CONFIRM_PASSWORD_TEXTBOX = "xpath=//label[text()='Confirm Password']/parent::div/following-sibling::div/input";
    public static final String SAVE_BUTTON = "css=button[type='submit']";
    public static final String SUCCESS_TOAST = "xpath=//div[contains(@class,'oxd-toast')]//p[text()='Successfully Saved']";

    public static final String EMPLOYEE_NAME = "xpath=//label[text()='Employee Name']/parent::div/following-sibling::div//input";
    public static final String EMPLOYEE_SUGGEST_FIRST_ITEM = "xpath=(//div[@role='listbox']//div[@role='option'])[1]";
    public static final String SELECTED_EMPLOYEE_FULL_NAME_DISPLAY = "xpath=//label[text()='Employee Name']/parent::div/following-sibling::div//input";
}
