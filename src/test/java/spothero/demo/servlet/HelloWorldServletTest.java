package spothero.demo.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import spothero.demo.client.ApiException;
import spothero.demo.client.api.HelloApi;
import spothero.demo.client.model.Greeting;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HelloWorldServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private RequestDispatcher requestDispatcher;

    @Test
    public void doGet() throws Exception {

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);

        new HelloWorldServlet().doGet(request, response);

        assertEquals("Hello, World!", stringWriter.toString());
    }

    @Test
    public void testWithSwaggerClient() throws ApiException {
        HelloApi apiInstance = new HelloApi();

        String result = apiInstance.helloWorld();
        assertThat(result, is("Hello, World!"));

    }


    @Test
    public void testJSONWithSwaggerClient() throws ApiException {
        HelloApi apiInstance = new HelloApi();

        Greeting result = apiInstance.greeting();
        assertThat(result, is("Hello, World!"));

    }

}