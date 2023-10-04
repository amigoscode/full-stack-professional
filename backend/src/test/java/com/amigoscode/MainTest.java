package com.amigoscode;

import static org.mockito.Mockito.mock;

import com.amigoscode.customer.repository.CustomerRepository;
import com.amigoscode.s3.FakeS3;
import com.amigoscode.s3.S3Service;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class MainTest {
    /**
     * Method under test: {@link Main#runner(CustomerRepository, PasswordEncoder, S3Service)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRunner() throws Exception {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files (file '/Users/ppdm/.amigoscode/s3/null/foo/bar', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        Main main = new Main();
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        main.runner(customerRepository, passwordEncoder, new S3Service(new FakeS3())).run("Args");
    }

    /**
     * Method under test: {@link Main#runner(CustomerRepository, PasswordEncoder, S3Service)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRunner2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files (file '/Users/ppdm/.amigoscode/s3/null/foo/bar', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        Main main = new Main();
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        LdapShaPasswordEncoder passwordEncoder = new LdapShaPasswordEncoder();
        main.runner(customerRepository, passwordEncoder, new S3Service(new FakeS3())).run("Args");
    }
}

