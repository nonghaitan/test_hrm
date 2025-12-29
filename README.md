# Test HRM UI – GitHub API Automation

This project contains **UI automation tests** for **OrangeHRM** and **API automation tests** for **GitHub (SeleniumHQ organization)**.

Built using:

- **Java 17**
- **Maven**
- **Selenium WebDriver**
- **RestAssured**
- **Cucumber (BDD)**
- **TestNG**
- **Extent Reports**

---

##  Setup Instructions

### 1️⃣ Install Java
Java 17 recommended  
https://www.oracle.com/java/technologies/downloads/

### 2️⃣ Install Maven  
https://maven.apache.org/install.html

### 3️⃣ ChromeDriver
Download version matching your Chrome  
https://chromedriver.chromium.org/downloads  
Add to **System PATH**

### 4️⃣ Clone Repository
```bash
git clone https://github.com/nonghaitan/test_hrm.git
```

### 5️⃣ Install Dependencies
```bash
mvn clean install
```

##  How to Run Tests
### 1️⃣ Run UI Tests Only
```bash
mvn clean test -Dtest=UITestRunner
```
   - Report:
   ```bash
   target/extent-report/UI-Report.html
   ```
   - ![img.png](img.png)
### 2️⃣ Run API Tests Only

```bash
mvn clean test -Dtest=APITestRunner
```

   - Report:
   ```bash
   target/extent-report/API-Report.html
   ```
   - ![img_1.png](img_1.png)

### 3️⃣ Run All Tests (UI + API)

```bash
mvn clean test
```

   - Reports are available in:
   ```bash
   target/extent-report/
   ```

## **Test execution results**
   - Please check report in target/extent-report/
