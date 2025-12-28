package data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TestUserData {
    private String employeeName;
    private String displayName;
    private String username;
    private String password;
    private String role;
    private String status;
}