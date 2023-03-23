package io.cucumber.skeleton;

//Hamcrest Imports
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

// Java Imports
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

// Cucumbers Imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;  
import io.cucumber.datatable.DataTable;

// Model Imports
import io.cucumber.skeleton.Book;
import io.cucumber.skeleton.Library;

public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>(); 

    @Given("I have the following books in the library") 
    public void insertBooks(DataTable table)  throws ParseException { 
        List<List<String>> rows = table.asLists(String.class); 

        for (List<String> columns : rows.subList(1, rows.size())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date published = dateFormat.parse(columns.get(2));
            library.addBook(new Book(columns.get(0), columns.get(1), published));
        } 
    }

    @When("the customer searches for books published between {string} and {string}")
    public void setSearchParameters(String year1, String year2)
            throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date from = dateFormat.parse(year1);
        Date to = dateFormat.parse(year2);
        result = library.findBooks(from, to);
    }

    @Then("{int} books should have been found")
    public void verifyAmountOfBooksFound(int booksFound) { 
        assertThat(result.size(), is(booksFound)); 
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookAtPosition(int position, String title) { 
        assertThat(result.get(position - 1).getTitle(), is(title)); 
    }

}
