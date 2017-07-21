package com.example.ezmilja.libraryapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emirmun on 13/07/2017.
 */

public class BookCache {

    public static final BookCache CACHE = new BookCache();

    private final Map<Integer, Book> books = new HashMap<>();

    private BookCache(){
        books.put(0, new Book(0, "ISBN: 978-1578512669", "2020 ForeSight", R.drawable.foresight, "Hugh Courtney", "Reveals how executives can develop 20/20 foresight - a view of the future that separates what can be known from what can't. This book shows strategists how to tailor various aspects of the decision-making process - from formulation to implementation - to the level of uncertainty faced.", "Page Count: 224", "01/11/01  Harvard Business Review Press", 5, 2, 2, 1));
        books.put(1, new Book(1,"ISBN: 978-0321821720", "A Practical Approach", R.drawable.apracticalapproach, "Gary Gruver, Mike Young, Pat Fulgham", "Today, even the largest development organizations are turning to agile methodologies, seeking major productivity and quality improvements. However, large-scale agile development is difficult, and publicly available case studies have been scarce. Now, three agile pioneers at Hewlett-Packard present a candid, start-to-finish insider's look at how they've succeeded with agile in one of the company's most mission-critical software environments: firmware for HP LaserJet printers.","Page  Count: 183", "25/11/12  Addison-Wesley Professional", 4, 10, 4, 1));
        books.put(2, new Book(2, "ISBN: 978-0596004392", "ADO.NET", R.drawable.ado, "Bill Hamilton", "The ADO.NET Cookbook focuses exclusively on providing developers with easy-to-find coding solutions to real problems.ADO.NET Cookbook is a comprehensive collection of over 150 solutions and best practices for everyday dilemmas. For each problem addressed in the book, there's a solution--a short, focused piece of code that programmers can insert directly into their applications.", "Page Count: 624", "September 2003 O’Reilly Media",3, 7, 7, 1));
        books.put(3, new Book(3, "ISBN: 978-0201379273", "Advanced COBRA programming with C++ ", R.drawable.advancecobra, "Michi Henning and Steve Vinoski", "Written for C++ practitioners, this book is the first to explore advanced Common Object Request Broker Architecture (CORBA) topics and techniques. Michi Henning and Steve Vinoski share the benefits of their extensive experience with CORBA programming, and arm the programmer with the skills necessary to overcome the intricate programming issues that arise in a real-world environment. By learning proven, hands-on explanations for building CORBA applications, the reader will be well on the way to building more successful distributed objects for industrial-strength development projects.", "Page Count: 1120", "12/02/99 Addison-Wesley", 4, 9, 9, 1));
        books.put(4, new Book(4, "ISBN: 978-0977616640", "Agile Retrospectives", R.drawable.agileretrospectives,"Esther Derby, Diana Larsen", "See how to mine the experience of your software development team continually throughout the life of the project. The tools and recipes in this book will help you uncover and solve hidden (and not-so-hidden) problems with your technology, your methodology, and those difficult “people issues” on your team.", "Page Count: 176", "01/07/06  Pragmatic bookshelf",3, 3, 3, 1));
        books.put(5, new Book(5, "ISBN: 978-0321534460", "Agile Testing",R.drawable.agiletesting, "Lisa Crispin", "In Agile Testing, Crispin and Gregory define agile testing and illustrate the tester’s role with examples from real agile teams. They teach you how to use the agile testing quadrants to identify what testing is needed, who should do it, and what tools might help. The book chronicles an agile software development iteration from the viewpoint of a tester and explains the seven key success factors of agile testing.", "Page Count:533", "December 2008 Addison-Wesley", 4, 6, 6, 1));
        books.put(6, new Book(6, "ISBN: 978-1932394801" ,"Ant In Action",R.drawable.antinaction, "Erik Hatcher, Steve Loughran", "Ant in Action introduces Ant and how to use it for test-driven Java application development. Ant itself is moving to v1.7, a major revision, at the end of 2006 so the timing for the book is right. A single application of increasing complexity, followed throughout the book, shows how an application evolves and how to handle the problems of building and testing. Reviewers have praised the book's coverage of large-projects, Ant's advanced features, and the details and depth of the discussion-all unavailable elsewhere.", "Page Count: 600", "June 2007 Manning Publications",3, 4, 4, 1));
        books.put(7, new Book(7, "ISBN: 978-0596802295", "The Art of Readable Code", R.drawable.artofreadablecode, "Dustin Boswell, Trevor Foucher", "This book focuses on basic principles and practical techniques you can apply every time you write code. Using easy-to-digest code examples from different languages, each chapter dives into a different aspect of coding, and demonstrates how you can make your code easy to understand.", "Page Count: 206", "23/11/11 O’Reilly Media",3, 19, 19, 1));
        books.put(8, new Book(8, "ISBN: 978-0007350544", "Bounce", R.drawable.bounce, "Matthew Syed", "Matthew explains why some people thrive under pressure and others choke, He weighs the value of innate ability against that of practice, hard work and will. From sex to maths, from the motivation of children to the culture of big business, Bounce shows how competition provides a master key with which to unlock the mysteries of success. ","Page Count:272", "28/04/11 Fourth Estate",2, 1, 1, 1));
        books.put(9, new Book(9, "ISBN: 978-0929652023", "Business Process Management is a Team sport", R.drawable.businessprocess,"Andrew Spanyi", "This book is best suited to those business leaders who have a burning desire to win. It's a book with a compelling message about the resurgence of business process thinking for competitive advantage. In an easy-to-read format, the book outlines why and how thoughtful CEO’s and leadership teams can manage enterprise business processes as the means to transform their good companies into great ones.Spanyi's book is a must read for business leaders.", "Page Count: 176", "June 2003 Peachpit Press",5,8, 8, 1));
    }

    public Book getBook(final int id){
        return books.get(id);
    }

    public int getNumberOfBooks(){
        return books.size();
    }
}
