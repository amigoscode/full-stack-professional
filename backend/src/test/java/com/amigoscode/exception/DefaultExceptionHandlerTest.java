package com.amigoscode.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

class DefaultExceptionHandlerTest {
    /**
     * Method under test: {@link DefaultExceptionHandler#handleException(Exception, HttpServletRequest)}
     */
    @Test
    void testHandleException() {
        DefaultExceptionHandler defaultExceptionHandler = new DefaultExceptionHandler();
        Exception e = new Exception("foo");
        ResponseEntity<ApiError> actualHandleExceptionResult = defaultExceptionHandler.handleException(e,
                new MockHttpServletRequest());
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
        assertEquals(500, actualHandleExceptionResult.getStatusCodeValue());
        ApiError body = actualHandleExceptionResult.getBody();
        assertEquals("", body.path());
        assertEquals(500, body.statusCode());
        assertEquals("foo", body.message());
    }

    /**
     * Method under test: {@link DefaultExceptionHandler#handleException(Exception, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleException2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Exception.getMessage()" because "e" is null
        //       at com.amigoscode.exception.DefaultExceptionHandler.handleException(DefaultExceptionHandler.java:56)
        //   See https://diff.blue/R013 to resolve this issue.

        DefaultExceptionHandler defaultExceptionHandler = new DefaultExceptionHandler();
        defaultExceptionHandler.handleException((Exception) null, new MockHttpServletRequest());
    }

    /**
     * Method under test: {@link DefaultExceptionHandler#handleException(Exception, HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHandleException3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "jakarta.servlet.http.HttpServletRequest.getRequestURI()" because "request" is null
        //       at com.amigoscode.exception.DefaultExceptionHandler.handleException(DefaultExceptionHandler.java:55)
        //   See https://diff.blue/R013 to resolve this issue.

        DefaultExceptionHandler defaultExceptionHandler = new DefaultExceptionHandler();
        defaultExceptionHandler.handleException(new Exception("foo"), null);
    }
}

