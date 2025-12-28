package data;

import java.util.UUID;

public class UserDataFactory {
    public static TestUserData createAdminUser() {

        String randomUser = "autoUser_" + UUID.randomUUID().toString().substring(0,6);

        return TestUserData.builder()
                .employeeName(null)
                .displayName(null)
                .username(randomUser)
                .password("Admin123!")
                .role("Admin")
                .status("Enabled")
                .build();
    }
}
