package one.microstream.controller;

import java.util.List;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import one.microstream.domain.Book;
import one.microstream.storage.DB;
import one.microstream.utils.MockupUtils;


@Controller("/books")
public class BookController
{
	@Get("/create")
	public HttpResponse<?> createBooks()
	{
		List<Book> loadMockupData = MockupUtils.loadMockupData();
		
		// Enter your code here
		
		return HttpResponse.ok("Books successfully created!");
	}
	
	@Get
	public List<Book> getBook()
	{
		return DB.root.getBooks();
	}
	
	@Get("/startsWith_A")
	public List<Book> getBooksWithA()
	{
		// Enter your code here
		
		return null;
	}
	
	@Get("/clear")
	public HttpResponse<?> clearBooks()
	{
		// Enter your code here
		
		return HttpResponse.ok("Books successfully cleared!");
	}
	
	@Get("/updateSingle")
	public HttpResponse<?> updateSingleBook()
	{
		// Enter your code here
		
		return HttpResponse.ok("Book successfully updated!");
	}
	
	@Get("/updateMulti")
	public HttpResponse<?> updateMultiBooks()
	{
		// Enter your code here
		
		return HttpResponse.ok("Bookss successfully updated!");
	}
}
