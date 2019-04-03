import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.util.internal.PathUtil as PathUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW

Mobile.comment('Test : Create Project')

Mobile.comment('Given that user has created project and application is started')

'Get full directory\'s path of android application'
def appPath = PathUtil.relativeToAbsolutePath(GlobalVariable.G_AndroidApp, RunConfiguration.getProjectDir())

Mobile.startApplication(appPath, false)

Mobile.comment('And he login to the App')

Mobile.tap(findTestObject('Application/android.widget.TextView - App'), 10) // to sign in


Mobile.setText(findTestObject('Application/android.widget.TextView - App'), 'test@test.com', 10) //to enter email id


Mobile.tap(findTestObject('Application/android.widget.TextView - App'), 10) // click next to enter password


Mobile.setText(findTestObject('Application/android.widget.TextView - App'), 'testpass', 10) //to enter password
    

Mobile.tap(findTestObject('Application/App/android.widget.TextView-Activity'), 10) //click enter to login

Mobile.comment('Then opens test project')

Mobile.tap(findTestObject('Application/App/Activity/android.widget.TextView-Custom Dialog'), 10) //to navigate to project
    

Mobile.scrollToText(findTestObject('Application/android.widget.TextView - App'), 'Test Task', 10) //scroll to created task
    

Mobile.tap(findTestObject('Application/App/Activity/android.widget.TextView-Custom Dialog'), 10) //to open created task to project
    

Mobile.tap(findTestObject('Application/App/Activity/android.widget.TextView-Custom Dialog'), 10) //click on complete

WS.sendRequest(findTestObject('API - ToDoList/Reopen Task'))

Mobile.tap(findTestObject('Application/App/Activity/android.widget.TextView-Custom Dialog'), 10) //click on task

def message = Mobile.getText(findTestObject('Application/App/Activity/Custom Dialog/android.widget.TextViewCustomDialog'),
	10) //to get task name

Mobile.verifyEqual(message, 'Test Task') //to verify task

Mobile.closeApplication()

