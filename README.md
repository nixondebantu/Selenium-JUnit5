## **Class resources:**

[**Software_Testing_Lab**](https://github.com/emon-swe-sust/Software_Testing_Lab)  
[**V&V Lab Exam Final Question 1**](https://docs.google.com/document/d/1oJ9uI1W8ujhsbPqnvYRATR3BcgXp8cxV3iDnMKz_0sQ/edit?usp=sharing)

- Question 1: Selenium Web Automation: [DemoBlaze.java](./src/test/java/DemoBlaze.java)
- Question 2: JUnit [LibraryTest.java](./src/test/java/LibraryTest.java)

## **Official Documentation:**

- [**Selenium Getting Started**](https://www.selenium.dev/documentation/webdriver/getting_started/)
- [**JUnit 5**](https://junit.org/junit5/)

## **Prerequisites:**

- Confirm Java is installed in your machine.

```bash
java -version
```

- [Install VS code IDE](https://code.visualstudio.com/download)

- Add [**`Extension Pack for Java`**](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) extension in VS Code

- Start a new empty maven project
  ![image.png](https://res.cloudinary.com/djx7nzzzq/image/upload/v1747586667/o8kfofiuxi8q4qtefdef.png)

![image.png](https://res.cloudinary.com/djx7nzzzq/image/upload/v1747586676/spid6cizct492ogohsah.png)

![image.png](https://res.cloudinary.com/djx7nzzzq/image/upload/v1747586672/zbisjl4c7jcqc61slqnq.png)

- Replace the `<properties>` dependency in `pom.xml`

```xml
<properties>
	<maven.compiler.source>17</maven.compiler.source>
	<maven.compiler.target>17</maven.compiler.target>
	<selenium.version>4.31.0</selenium.version>
</properties>
<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.junit</groupId>
			<artifactId>junit-bom</artifactId>
			<version>5.12.2</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
<dependencies>
	<dependency>
		<groupId>org.seleniumhq.selenium</groupId>
		<artifactId>selenium-java</artifactId>
		<version>${selenium.version}</version>
	</dependency>
	<dependency>
		<groupId>org.junit.jupiter</groupId>
		<artifactId>junit-jupiter</artifactId>
		<scope>test</scope>
	</dependency>
</dependencies>
```

- Prepare the test cases as you please.

## 🧪 **Practice Sites:**

- [Practice Test Automation](https://practicetestautomation.com/practice-test-login/)

- [DEMOQA](https://demoqa.com/)

- [UI Test Automation Playground](http://uitestingplayground.com/)
