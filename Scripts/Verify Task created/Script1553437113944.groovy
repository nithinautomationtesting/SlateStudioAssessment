import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.util.internal.PathUtil as PathUtil

Mobile.comment('Story: Verify test task created')

Mobile.comment('Given that user has started an application')

'Get full directory\'s path of android application'
def appPath = PathUtil.relativeToAbsolutePath(GlobalVariable.G_AndroidApp, RunConfiguration.getProjectDir())

Mobile.startApplication(appPath, false)

Mobile.comment('And he login to the App')

Mobile.tap(findTestObject('Application/android.widget.TextView - App'), 10) // to sign in

Mobile.setText(findTestObject('Application/android.widget.TextView - App'), 'test@test.com', 10) //to enter email id

Mobile.tap(findTestObject('Application/android.widget.TextView - App'), 10) // click next to enter password

Mobile.setText(findTestObject('Application/android.widget.TextView - App'), 'testpass', 10) //to enter password

Mobile.tap(findTestObject('Application/App/android.widget.TextView-Activity'), 10) //click enter to login

Mobile.comment('Then he verifies project created')

Mobile.tap(findTestObject('Application/App/Activity/android.widget.TextView-Custom Dialog'), 10) //to navigate to project view

Mobile.comment('And he navigates to create task')

Mobile.tap(findTestObject('Application/android.widget.TextView - Graphics'), GlobalVariable.G_Timeout) //click create task

Mobile.setText(findTestObject('Application/android.widget.TextView - App'), 'Task Name', 10) //to enter task name

Mobile.tap(findTestObject('Application/App/android.widget.TextView-Activity'), 10) //click to create

'Get tasks label'
def taskText = Mobile.getText(findTestObject('Application/Graphics/android.widget.TextView - Xfermodes'), GlobalVariable.G_Timeout)

Mobile.verifyEqual(taskText, 'Task Name')

WS.sendRequest(findTestObject('API - ToDoList/Verify Task Created'))

Mobile.closeApplication()

