package com.cc.simba.k;


public class K5_AOP01 {
}


interface BookService {
    void createBook(String book);

    void updateBook(String book);
}

class BookServiceImpl {

    void createBook(String book) {
        //securityCheck();
        //Transaction tx = new Transaction();
        try {
            // book . add ();
            // tx.commit();
        } catch (RuntimeException ex) {
            // tx.rollback();
            throw ex;
        }
        // log.Info( "add book ");
    }

    void updateBook(String book) {
        //securityCheck();
        //Transaction tx = new Transaction();
        try {
            // book . update ();
            // tx.commit();
        } catch (RuntimeException ex) {
            // tx.rollback();
            throw ex;
        }
        // log.Info( "add book ");
    }

    /***
     * Proxy 模式 , 代理模式
     */
    class SecurityCheckBookService implements BookService {

        private final BookService target;

        SecurityCheckBookService(BookService target) {
            this.target = target;
        }


        @Override
        public void createBook(String book) {
            securityCheck();
            target.createBook(book);
        }

        @Override
        public void updateBook(String book) {
            securityCheck();
            target.updateBook(book);
        }

        public void securityCheck() {

        }
    }

}