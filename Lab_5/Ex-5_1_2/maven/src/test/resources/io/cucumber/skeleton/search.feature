Feature: Book search
  To allow customers to find books, the library must offer seek methods.

  Scenario: Search books by publication year
     
    Given I have the following books in the library
      | title                                | author           | p_date |
      | Naruto                               | Kishimoto        | 01-11-2002|
      | The Call of Cthulhu                  | H.P. Lovecraft   | 01-03-1960|
      | The Darkest Soul                     | J.G Teles        | 01-02-2024|
        
    
    When the customer searches for books published between '1950' and '2003'
    
    Then 2 books should have been found
      And Book 1 should have the title 'Naruto'
      And Book 2 should have the title 'The Call of Cthulhu'
