package one.microstream.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import one.microstream.domain.Book;


public class DataRoot
{
	private final List<Book>		books			= new ArrayList<Book>();
	private final List<Book>		syncListBooks	= Collections.synchronizedList(new ArrayList<Book>());
	private final Set<Book>			syncSetBooks	= Collections.synchronizedSet(new HashSet<Book>());
	private final Map<String, Book>	syncBooks		= Collections.synchronizedMap(new HashMap<String, Book>());
	
	public List<Book> getBooks()
	{
		return books;
	}
}
