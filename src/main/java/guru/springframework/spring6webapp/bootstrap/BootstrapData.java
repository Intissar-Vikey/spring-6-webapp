package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {




        Author eric = new Author();
        eric.setFirstName("eric");
        eric.setLastName("Mha");


        Author rod = new Author();
        eric.setFirstName("Rod");
        eric.setLastName("Johnson");


        Book firstbook = new Book();
        firstbook.setTitle("Good Book");
        firstbook.setIsbn("123456");

        Book secondBook = new Book();
        secondBook.setTitle("Second Book");
        secondBook.setIsbn("123457");


        Book firstBookSaved = bookRepository.save(firstbook);
        Book secondBookSaved = bookRepository.save(secondBook);
        Author ericSaved = authorRepository.save(eric);
        Author rodSaved = authorRepository.save(rod);

        Publisher stellar = new Publisher();
        stellar.setAddress("40 avenue paix");
        stellar.setCity("Saint-cyr");
        stellar.setZipCode("78101");

        Publisher savedPublisher = publisherRepository.save(stellar);

        secondBook.setPublisher(savedPublisher);
        firstbook.setPublisher(savedPublisher);


        /* building the association between books and authors */
        ericSaved.getBooks().add(firstbook);
        rodSaved.getBooks().add(secondBook);
        firstbook.getAuthors().add(ericSaved);
        secondBook.getAuthors().add(rodSaved);


        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(firstBookSaved);
        bookRepository.save(secondBookSaved);


        System.out.println("In BootStrap ");
        System.out.println("Author count : "+authorRepository.count());
        System.out.println("Book count : "+bookRepository.count());
        System.out.println("Publisher count : "+ publisherRepository.count());


    }
}
