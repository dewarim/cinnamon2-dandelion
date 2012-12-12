package eu.hornerproject.dandelion.webtest

import org.grails.plugins.webtest.WebTestCase as WTC

/**
 *
 */
class BaseTest extends grails.util.WebTest{

    final String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

    def login() {
        ant.group(description:"login") {
  	        invoke 			'/'
        	setSelectField 	name:'environment', text:'cmn_test', description:"select repository 'cmn_test'"
			setInputField 	name:'j_username', value:'admin'
			setInputField 	name:'j_password', value:'admin'
 	        clickButton 	'login'
        }
    }

    def logout() {
        ant.group(description:"log out") {
  		  invoke 		'/logout/index'
  		  verifyText 	'login'
        }
    }

    def verifyListSize(int size) {
        WTC.ant.group(description: "verify list view with $size row(s)") {
            verifyXPath xpath: ROW_COUNT_XPATH,
                    text: size,
                    description: "$size row(s) of data expected"
        }
    }

    def showFirstElementDetails(elementText) {
        WTC.ant.clickLink description: 'go to detail view',
                xpath: "//div[@class='list']//tbody/tr[td='$elementText']/td[1]/a"
    }
}
