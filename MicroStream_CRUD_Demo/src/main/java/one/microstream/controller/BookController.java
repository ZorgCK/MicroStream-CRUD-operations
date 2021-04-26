package one.microstream.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import one.microstream.domain.Author;
import one.microstream.domain.Book;
import one.microstream.storage.DB;
import one.microstream.utils.MockupUtils;


@Controller("/books")
public class BookController
{
	@Get("/create")
	public HttpResponse<?> createBooks()
	{
		List<Book> allCreatedBooks = MockupUtils.loadMockupData();
		
		DB.root.getBooks().addAll(allCreatedBooks);
		DB.storageManager.store(DB.root.getBooks());
		
		return HttpResponse.ok("Books successfully created!");
	}
	
	@Get("/createSingle")
	public HttpResponse<?> createSingleBook()
	{
		Author author = new Author("100", "John", "Doe", "j.doe@example.com", "Male");
		Book book = new Book("123456789", "Single Book", LocalDate.now(), new BigDecimal(13.32), author);
		
		DB.root.getBooks().add(book);
		DB.storageManager.store(DB.root.getBooks());
		
		return HttpResponse.ok("Book successfully created!");
	}
	
	@Get
	public List<Book> getBook()
	{
		return DB.root.getBooks();
	}
	
	@Get("/startsWith_A")
	public List<Book> getBooksWithA()
	{
		return DB.root.getBooks().stream().filter(b -> b.getName().startsWith("A")).collect(Collectors.toList());
	}
	
	@Get("/clear")
	public HttpResponse<?> clearBooks()
	{
		DB.root.getBooks().clear();
		DB.storageManager.store(DB.root.getBooks());
		
		return HttpResponse.ok("Books successfully cleared!");
	}
	
	@Get("/updateSingle")
	public HttpResponse<?> updateSingleBook()
	{
		Book book = DB.root.getBooks().stream().findFirst().get();
		book.setRelease(LocalDate.now());
		
		DB.storageManager.store(book);
		
		return HttpResponse.ok("Book successfully updated!");
	}
	
	@Get("/updateMulti")
	public HttpResponse<?> updateMultiBooks()
	{
		DB.root.getBooks().forEach(b ->
		{
			// Reduces price of all books by 10%
			b.setPrice(b.getPrice().multiply(new BigDecimal(0.9)));
		});
		
		DB.storageManager.storeAll(DB.root.getBooks());
		
		return HttpResponse.ok("Bookss successfully updated!");
	}
}
