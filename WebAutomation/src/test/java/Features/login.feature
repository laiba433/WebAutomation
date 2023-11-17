Feature: Login

 Scenario: Login With Invalid Credentials
    Given Fill TextBox "Username" "standard_user"
    Given Fill TextBox "Password" "InvalidPassword"
    And Click Button "LoginButton"
    Then Validate the Presence of element "error"


  Scenario: Login With Correct Credentials
    Given Fill TextBox "Username" "standard_user"
    Given Fill TextBox "Password" "secret_sauce"
    And Click Button "LoginButton"
    Then Validate the Presence of element "Logo"

            

