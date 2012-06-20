INSERT INTO publisher VALUES (1, 'Princeton University Press');
INSERT INTO publisher VALUES (2, 'Que');
INSERT INTO publisher VALUES (3, 'Workman Publishing Company');
INSERT INTO publisher VALUES (4, 'Manning Publications');
INSERT INTO publisher VALUES (5, 'Cambridge University Press');
INSERT INTO publisher VALUES (6, 'Simon & Schuster');

-- (ids: 1-9: members, 10-23: authors)
INSERT INTO person VALUES (1, 'Albinus', 'Ludofl');
INSERT INTO person VALUES (2, 'Tomas', 'Mano');
INSERT INTO person VALUES (3, 'Karl', 'Murriel');
INSERT INTO person VALUES (4, 'Ealisaid', 'Paaie');
INSERT INTO person VALUES (5, 'Riona', 'Moyna');
INSERT INTO person VALUES (6, 'Ronan', 'Collin');
INSERT INTO person VALUES (7, 'Borislav', 'Tikhon');
INSERT INTO person VALUES (8, 'Mars', 'Tyr');
INSERT INTO person VALUES (9, 'Pegasus', 'Pherick');
INSERT INTO person VALUES (10, 'Immanuel', 'Kant');
INSERT INTO person VALUES (11, 'Ron', 'Black');
INSERT INTO person VALUES (12, 'Timothy', 'Downs');
INSERT INTO person VALUES (13, 'Michael', 'Miller');
INSERT INTO person VALUES (14, 'Jenny', 'Jackson');
INSERT INTO person VALUES (15, 'Morris', 'Rosennal');
INSERT INTO person VALUES (16, 'Donna', 'Wallace');
INSERT INTO person VALUES (17, 'Mikko', 'Handler');
INSERT INTO person VALUES (18, 'Wilma', 'Krakauer');
INSERT INTO person VALUES (19, 'Susan', 'Sebasti');
INSERT INTO person VALUES (20, 'Frederik', 'Mountain');
INSERT INTO person VALUES (21, 'Jully', 'Snow');
INSERT INTO person VALUES (22, 'Mike', 'Clicker');
INSERT INTO person VALUES (23, 'Don', 'Sunshine');

-- acc id + addr + email + hashpass + phone + salt + username + person_id
-- 1: january123, february123, march123, april123, maymay123, junejune123, julyjuly123, august123, september123 --

INSERT INTO memberaccount VALUES (1, 'Prague', 'albi@gmail.com', 'uY8Wd6Hf+R2i1G8aiKVw0pzUNfEIWrQsZBnpWSqmofI=', '123456789', '9a8i9jtnigv57kjcav9t2tsa5s', 'albinus', 1);
INSERT INTO memberaccount VALUES (2, 'Bratislava', 'tomasmano@gmail.com', 'W+vzeR8RiRNz666LqFclFoTgon+Jq6cwUuY2/QHb2lU=', '234453489', '12q0eo8g6nrocsim2uhlhfegau', 'tommy', 2);
INSERT INTO memberaccount VALUES (3, 'Wien', 'karlmurriel@gmail.com', '+2skyBVekXtb50kb8CCGRT8ejw3w1+1jGu4O6uL+q+g=', '189853489', 'p0eelct3ulhcsd25v1vbidl7lq', 'karlm', 3);
INSERT INTO memberaccount VALUES (4, 'London', 'ealisaidpaaie@gmail.com', 'vwRFWR/WgOn5z2hiS5B8Mg2xIjksq2lqDhhd2DdVVBM=', '897853489', 'e060dejql6hj122fvlhcl1t00f', 'elisa', 4);
INSERT INTO memberaccount VALUES (5, 'Paris', 'rionka2542@gmail.com', 'qeMLXXAG2NsspkQ7iA/D68kQlxb+xWx8h7GPTD8fSpk=', '897853489', '7lrl6jh97g2nllmou5mk997cmg', 'rionka', 5);
INSERT INTO memberaccount VALUES (6, 'Tokyo', 'ronancollin@gmail.com', 'Sb8TLawpPc6+e09xzPHx12d6xotDMm+emrbJn0gvi3g=', '879453487', '93lflcl0i35p80nrnemumnsj73', 'ronan', 6);
INSERT INTO memberaccount VALUES (7, 'Casablanca', 'borisboris@gmail.com', 'zmGekH3UZYzPqcXmBA7BW9rEwB8LV7fTo/iRcRJdSho=', '145683489', 'hf1c185s7l5f7pqh9bfflsq7qo', 'boris', 7);
INSERT INTO memberaccount VALUES (8, 'Dakar', 'marstyr@gmail.com', 'bBjsaQyQyhY02JhJv+qATh9quUTNoA0jkG4E3aRoC1c=', '123098765', 'f7q7a3pihbsmmupiqhpfi5pdfo', 'marstyr', 8);
INSERT INTO memberaccount VALUES (9, 'Rio', 'pegasuspherick@gmail.com', '4FPZKEuehvz7jrTpmh+By9oMM35D73PzuSGv8jDCWro=', '198765489', '712ks1q23ac008er2ic0ak0qdo', 'pegasuslikehorses', 9);

--author id + person id
INSERT INTO author VALUES (1, 10);
INSERT INTO author VALUES (2, 11);
INSERT INTO author VALUES (3, 12);
INSERT INTO author VALUES (4, 13);
INSERT INTO author VALUES (5, 14);
INSERT INTO author VALUES (6, 15);
INSERT INTO author VALUES (7, 16);
INSERT INTO author VALUES (8, 17);
INSERT INTO author VALUES (9, 18);
INSERT INTO author VALUES (10, 19);
INSERT INTO author VALUES (11, 20);
INSERT INTO author VALUES (12, 21);

INSERT INTO librarydocument VALUES (1, 'book', 'I Will Teach You To Be Rich', 1);
INSERT INTO librarydocument VALUES (2, 'book', 'How Computers Work (9th Edition)', 2);
INSERT INTO librarydocument VALUES (3, 'book', 'Is This Thing On?, revised edition: A Computer Handbook for Late Bloomers, Technophobes, and the Kicking & Screaming', 2);
INSERT INTO librarydocument VALUES (4, 'book', 'Hello World! Computer Programming for Kids and Other Beginners', 3);
INSERT INTO librarydocument VALUES (5, 'book', 'Nine Algorithms That Changed the Future: The Ingenious Ideas That Drive Todays Computers', 1);
INSERT INTO librarydocument VALUES (6, 'book', 'My Life on Mars: A Novel', 1);
INSERT INTO librarydocument VALUES (7, 'book', 'Zodiac Unmasked', 1);
INSERT INTO librarydocument VALUES (8, 'book', 'Java Guru', 1);
INSERT INTO librarydocument VALUES (9, 'book', 'The Philosophical Writings of Descartes: Volume 1', 5);
INSERT INTO librarydocument VALUES (10, 'book', 'The Philosophical Writings of Descartes: Volume 2', 5);
INSERT INTO librarydocument VALUES (11, 'book', 'The Philosophical Writings of Descartes: Volume 3', 5);
INSERT INTO librarydocument VALUES (12, 'book', 'Philosophical Essays', 5);
INSERT INTO librarydocument VALUES (13, 'book', 'An Essay Concerning Human Understanding ', 1);
INSERT INTO librarydocument VALUES (14, 'book', 'A Treatise of Human Nature', 5);
INSERT INTO librarydocument VALUES (15, 'book', 'The Critique of Pure Reason', 5);
INSERT INTO librarydocument VALUES (16, 'book', 'When A Man Loves A Woman', 2);
INSERT INTO librarydocument VALUES (17, 'book', 'Why Men Marry Bitches: A Womans Guide to Winning Her Mans Heart', 6);
INSERT INTO librarydocument VALUES (18, 'book', 'For Women Only: What You Need to Know about the Inner Lives of Men', 5);

INSERT INTO book VALUES (1, 'PAPERBACK', 'english', 356);
INSERT INTO book VALUES (2, 'HARDCOVER', 'english', 828);
INSERT INTO book VALUES (3, 'PAPERBACK', 'english', 299);
INSERT INTO book VALUES (4, 'HARDCOVER', 'english', 769);
INSERT INTO book VALUES (5, 'HARDCOVER', 'english', 320);
INSERT INTO book VALUES (6, 'PAPERBACK', 'english', 623);
INSERT INTO book VALUES (7, 'HARDCOVER', 'english', 543);
INSERT INTO book VALUES (8, 'HARDCOVER', 'english', 817);
INSERT INTO book VALUES (9, 'PAPERBACK', 'english', 130);
INSERT INTO book VALUES (10, 'HARDCOVER', 'english', 961);
INSERT INTO book VALUES (11, 'HARDCOVER', 'english', 384);
INSERT INTO book VALUES (12, 'PAPERBACK', 'english', 968);
INSERT INTO book VALUES (13, 'HARDCOVER', 'english', 280);
INSERT INTO book VALUES (14, 'PAPERBACK', 'english', 1031);
INSERT INTO book VALUES (15, 'HARDCOVER', 'english', 972);
INSERT INTO book VALUES (16, 'PAPERBACK', 'english', 789);
INSERT INTO book VALUES (17, 'PAPERBACK', 'english', 185);
INSERT INTO book VALUES (18, 'HARDCOVER', 'english', 115);

--librarydoc id + author id
INSERT INTO librarydocument_author VALUES(1, 1);
INSERT INTO librarydocument_author VALUES(1, 7);
INSERT INTO librarydocument_author VALUES(2, 2);
INSERT INTO librarydocument_author VALUES(2, 3);
INSERT INTO librarydocument_author VALUES(3, 4);
INSERT INTO librarydocument_author VALUES(4, 4);
INSERT INTO librarydocument_author VALUES(5, 4);
INSERT INTO librarydocument_author VALUES(5, 5);
INSERT INTO librarydocument_author VALUES(6, 7);
INSERT INTO librarydocument_author VALUES(6, 8);
INSERT INTO librarydocument_author VALUES(6, 9);
INSERT INTO librarydocument_author VALUES(7, 9);
INSERT INTO librarydocument_author VALUES(7, 10);
INSERT INTO librarydocument_author VALUES(8, 11);
INSERT INTO librarydocument_author VALUES(8, 12);
INSERT INTO librarydocument_author VALUES(8, 13);
INSERT INTO librarydocument_author VALUES(9, 13);
INSERT INTO librarydocument_author VALUES(9, 14);
INSERT INTO librarydocument_author VALUES(9, 15);
INSERT INTO librarydocument_author VALUES(10, 16);
INSERT INTO librarydocument_author VALUES(11, 17);
INSERT INTO librarydocument_author VALUES(11, 18);
INSERT INTO librarydocument_author VALUES(12, 17);
INSERT INTO librarydocument_author VALUES(12, 18);

INSERT INTO genre VALUES(1, 'ROMANCE');
INSERT INTO genre VALUES(2, 'ACTION');
INSERT INTO genre VALUES(3, 'TRAGEDY');
INSERT INTO genre VALUES(4, 'BIOGRAPHY');
INSERT INTO genre VALUES(5, 'EDUCATIONAL');
INSERT INTO genre VALUES(6, 'TRUE_STORY');
INSERT INTO genre VALUES(7, 'FANTASY');

INSERT INTO librarydocument_genre VALUES(1, 5);
INSERT INTO librarydocument_genre VALUES(2, 5);
INSERT INTO librarydocument_genre VALUES(3, 5);
INSERT INTO librarydocument_genre VALUES(4, 5);
INSERT INTO librarydocument_genre VALUES(5, 5);
INSERT INTO librarydocument_genre VALUES(6, 2);
INSERT INTO librarydocument_genre VALUES(7, 7);
INSERT INTO librarydocument_genre VALUES(8, 5);
INSERT INTO librarydocument_genre VALUES(9, 5);
INSERT INTO librarydocument_genre VALUES(10, 5);
INSERT INTO librarydocument_genre VALUES(11, 5);
INSERT INTO librarydocument_genre VALUES(12, 5);
INSERT INTO librarydocument_genre VALUES(13, 5);
INSERT INTO librarydocument_genre VALUES(14, 5);
INSERT INTO librarydocument_genre VALUES(15, 5);
INSERT INTO librarydocument_genre VALUES(16, 1);
INSERT INTO librarydocument_genre VALUES(17, 1);
INSERT INTO librarydocument_genre VALUES(18, 1);

-- INSERT INTO review VALUES(1, 'A fun read! This is why started buying books! Im also going to start searching by the authors for more of their work! I had been programming for about 7 years and this book gave me some great new ideas. Perfect book for teaching old dogs new tricks!', '2012-7-4 04:13:54', 8, 5);
-- INSERT INTO review VALUES(2, 'I am not going to presume to review this great classic work, except to say that my teenage son needed some more serious works to read for school and found that this was his favorite. We originally checked the Norton Critical Edition out from the library, and when we couldnt renew it any longer I picked up a used paperback that was another translation. My son felt he could really tell the difference, so he got this one for Christmas.', '2012-2-5 12:53:02', 11, 8);
-- INSERT INTO review VALUES(3, 'I usually wont give a review a 5 star because there is always something to improve. But this book is what Literature is at its best. ', '2012-1-12 04:13:54', 6, 2);

-- DATETIME - format: YYYY-MM-DD HH:MM:SS

--id reserv + until + reserved book id + member id
-- out of date....
-- INSERT INTO reservation VALUES(1, '2012-4-4 05:13:50', 4, 4);
-- INSERT INTO reservation VALUES(2, '2012-4-5 16:14:24', 17, 7);
-- INSERT INTO reservation VALUES(3, '2012-4-2 04:34:17', 11, 1);
-- INSERT INTO reservation VALUES(4, '2012-4-4 11:59:14', 9, 2);
-- INSERT INTO reservation VALUES(5, '2012-4-1 19:23:16', 10, 2);
-- INSERT INTO reservation VALUES(6, '2012-4-7 14:12:47', 10, 4);
-- INSERT INTO reservation VALUES(7, '2012-4-8 14:42:17', 10, 9);
-- INSERT INTO reservation VALUES(8, '2012-4-6 13:42:27', 2, 5);
-- INSERT INTO reservation VALUES(9, '2012-4-9 11:22:47', 13, 3);
-- INSERT INTO reservation VALUES(10, '2012-4-1 18:52:33', 18, 5);
-- INSERT INTO reservation VALUES(11, '2012-4-6 19:22:24', 17, 8);
-- INSERT INTO reservation VALUES(12, '2012-4-1 12:32:27', 7, 7);
-- INSERT INTO reservation VALUES(13, '2012-4-5 19:12:17', 5, 3);
-- INSERT INTO reservation VALUES(14, '2012-4-11 17:12:27', 13, 3);
-- INSERT INTO reservation VALUES(15, '2012-4-12 17:12:44', 13, 1);

INSERT INTO item VAlUES(1, TRUE, 1);
INSERT INTO item VAlUES(2, TRUE, 1);
INSERT INTO item VAlUES(3, TRUE, 1);
INSERT INTO item VAlUES(4, TRUE, 2);
INSERT INTO item VAlUES(5, TRUE, 2);
INSERT INTO item VAlUES(6, TRUE, 3);
INSERT INTO item VAlUES(7, TRUE, 3);
INSERT INTO item VAlUES(8, TRUE, 3);
INSERT INTO item VAlUES(9, TRUE, 4);
INSERT INTO item VAlUES(10, TRUE, 4);
INSERT INTO item VAlUES(11, TRUE, 5);
INSERT INTO item VAlUES(12, TRUE, 6);
INSERT INTO item VAlUES(13, TRUE, 6);
INSERT INTO item VAlUES(14, TRUE, 6);
INSERT INTO item VAlUES(15, TRUE, 7);
INSERT INTO item VAlUES(16, TRUE, 7);
INSERT INTO item VAlUES(17, TRUE, 8);
INSERT INTO item VAlUES(18, TRUE, 8);
INSERT INTO item VAlUES(19, TRUE, 8);
INSERT INTO item VAlUES(20, TRUE, 9);
INSERT INTO item VAlUES(21, TRUE, 9);
INSERT INTO item VAlUES(22, TRUE, 10);
INSERT INTO item VAlUES(23, TRUE, 10);
INSERT INTO item VAlUES(24, TRUE, 10);
INSERT INTO item VAlUES(25, TRUE, 11);
INSERT INTO item VAlUES(26, TRUE, 12);
INSERT INTO item VAlUES(27, TRUE, 13);
INSERT INTO item VAlUES(28, TRUE, 14);
INSERT INTO item VAlUES(30, TRUE, 15);
INSERT INTO item VAlUES(31, TRUE, 16);
INSERT INTO item VAlUES(32, TRUE, 17);
INSERT INTO item VAlUES(33, TRUE, 18);

--penalty id
-- INSERT INTO penalty VAlUES(1, '2012-6-4');

--issue id + since + member id + item id + penalty id
-- INSERT INTO issue VAlUES(1, '2012-3-4' , 2, 2, null);
-- INSERT INTO issue VAlUES(2, '2012-3-5' ,4, 14, null);
-- INSERT INTO issue VAlUES(3, '2012-3-3' ,5, 11, null);
-- INSERT INTO issue VAlUES(4, '2012-3-8' ,7, 12, null);
-- INSERT INTO issue VAlUES(5, '2012-3-9' ,1, 13, null);
-- INSERT INTO issue VAlUES(6, '2012-3-1' ,8, 4, 1);
-- INSERT INTO issue VAlUES(7, '2012-3-8' ,8, 5, null);
-- INSERT INTO issue VAlUES(8, '2012-3-6' ,9, 8, null);
-- INSERT INTO issue VAlUES(9, '2012-3-7' ,1, 9, null);
-- INSERT INTO issue VAlUES(10, '2012-3-14' ,7, 10, null);
 
-- UPDATE  item SET ISAVAILABLE=TRUE