Code Review
------------------------------------------

1. All test cases can confirm to an abstract class so that they all have access to common and useful codes, thereby eliminating the need of code duplication.
2. WebDriver is initialised before setting system properties which results in NPE. WebDriver configuration can be moved to Abstract class.
3. Most WebElements can be globalised as their value do not change across different users/scenarios.
4. Fluent wait and other conditional waits can be used instead of thread.sleep() as thread.sleep() is a hard wait and does not provide any assurance of expected condition being met.
5. All Data input values which are hardcoded can be globalised and can be declared as static and final since they do not change throughout the execution. In addition to that, it is easy to edit the input values this way, contributing to code maintainabilty.
6. Signin modal is in different frame and it is required first to findout the frame corresponding to the the modal and then switching to it for the execution of test cases.
7. File paths for all chromedrivers can be made to get from property file so that the different versions of chromedrivers can be used without having to compile the code again.
8. The Notification alert in website is interfering with the auto-completion feature in input forms, It can be handled by blocking the notification popups while initialising webDriver options in the abstract class.