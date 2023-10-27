package com.amigoscode.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.amigoscode.customer.dto.CustomerDTO;
import com.amigoscode.customer.dto.CustomerDTOMapper;
import com.amigoscode.customer.dto.CustomerDao;
import com.amigoscode.customer.entity.Customer;
import com.amigoscode.customer.request.Gender;
import com.amigoscode.exception.DuplicateResourceException;
import com.amigoscode.exception.ResourceNotFoundException;
import com.amigoscode.s3.S3Buckets;
import com.amigoscode.s3.S3Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerService.class, S3Buckets.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {
    @MockBean
    private CustomerDTOMapper customerDTOMapper;

    @MockBean(name = "jdbc")
    private CustomerDao customerDao;

    @Autowired
    private CustomerService customerService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private S3Buckets s3Buckets;

    @MockBean
    private S3Service s3Service;

    /**
     * Method under test: {@link CustomerService#getCustomer(Integer)}
     */
    @Test
    void testGetCustomer() {
        Customer customer = new Customer();
        customer.setAge(1);
        customer.setEmail("jane.doe@example.org");
        customer.setGender(Gender.MALE);
        customer.setId(1);
        customer.setName("Name");
        customer.setProfileImageId("42");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerDao.selectCustomerById(Mockito.<Integer>any())).thenReturn(ofResult);
        CustomerDTO customerDTO = new CustomerDTO(1, "Name", "jane.doe@example.org", Gender.MALE, 1, new ArrayList<>(),
                "janedoe", "42");

        when(customerDTOMapper.apply(Mockito.<Customer>any())).thenReturn(customerDTO);
        assertSame(customerDTO, customerService.getCustomer(1));
        verify(customerDao).selectCustomerById(Mockito.<Integer>any());
        verify(customerDTOMapper).apply(Mockito.<Customer>any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomer(Integer)}
     */
    @Test
    void testGetCustomer2() {
        Customer customer = new Customer();
        customer.setAge(1);
        customer.setEmail("jane.doe@example.org");
        customer.setGender(Gender.MALE);
        customer.setId(1);
        customer.setName("Name");
        customer.setProfileImageId("42");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerDao.selectCustomerById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(customerDTOMapper.apply(Mockito.<Customer>any()))
                .thenThrow(new DuplicateResourceException("An error occurred"));
        assertThrows(DuplicateResourceException.class, () -> customerService.getCustomer(1));
        verify(customerDao).selectCustomerById(Mockito.<Integer>any());
        verify(customerDTOMapper).apply(Mockito.<Customer>any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomer(Integer)}
     */
    @Test
    void testGetCustomer3() {
        Optional<Customer> emptyResult = Optional.empty();
        when(customerDao.selectCustomerById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomer(1));
        verify(customerDao).selectCustomerById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomerProfileImage(Integer)}
     */
    @Test
    void testGetCustomerProfileImage() throws UnsupportedEncodingException {
        Customer customer = new Customer();
        customer.setAge(1);
        customer.setEmail("jane.doe@example.org");
        customer.setGender(Gender.MALE);
        customer.setId(1);
        customer.setName("Name");
        customer.setProfileImageId("42");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerDao.selectCustomerById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(customerDTOMapper.apply(Mockito.<Customer>any())).thenReturn(
                new CustomerDTO(1, "Name", "jane.doe@example.org", Gender.MALE, 1, new ArrayList<>(), "janedoe", "42"));
        when(s3Service.getObject(Mockito.<String>any(), Mockito.<String>any())).thenReturn("AXAXAXAX".getBytes("UTF-8"));
        byte[] actualCustomerProfileImage = customerService.getCustomerProfileImage(1);
        assertEquals(8, actualCustomerProfileImage.length);
        assertEquals('A', actualCustomerProfileImage[0]);
        assertEquals('X', actualCustomerProfileImage[1]);
        assertEquals('A', actualCustomerProfileImage[2]);
        assertEquals('X', actualCustomerProfileImage[3]);
        assertEquals('A', actualCustomerProfileImage[4]);
        assertEquals('X', actualCustomerProfileImage[5]);
        assertEquals('A', actualCustomerProfileImage[6]);
        assertEquals('X', actualCustomerProfileImage[7]);
        verify(customerDao).selectCustomerById(Mockito.<Integer>any());
        verify(customerDTOMapper).apply(Mockito.<Customer>any());
        verify(s3Service).getObject(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomerProfileImage(Integer)}
     */
    @Test
    void testGetCustomerProfileImage2() {
        Customer customer = new Customer();
        customer.setAge(1);
        customer.setEmail("jane.doe@example.org");
        customer.setGender(Gender.MALE);
        customer.setId(1);
        customer.setName("Name");
        customer.setProfileImageId("42");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerDao.selectCustomerById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(customerDTOMapper.apply(Mockito.<Customer>any())).thenReturn(
                new CustomerDTO(1, "Name", "jane.doe@example.org", Gender.MALE, 1, new ArrayList<>(), "janedoe", "42"));
        when(s3Service.getObject(Mockito.<String>any(), Mockito.<String>any()))
                .thenThrow(new DuplicateResourceException("An error occurred"));
        assertThrows(DuplicateResourceException.class, () -> customerService.getCustomerProfileImage(1));
        verify(customerDao).selectCustomerById(Mockito.<Integer>any());
        verify(customerDTOMapper).apply(Mockito.<Customer>any());
        verify(s3Service).getObject(Mockito.<String>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomerProfileImage(Integer)}
     */
    @Test
    void testGetCustomerProfileImage3() {
        Optional<Customer> emptyResult = Optional.empty();
        when(customerDao.selectCustomerById(Mockito.<Integer>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerProfileImage(1));
        verify(customerDao).selectCustomerById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomerProfileImage(Integer)}
     */
    @Test
    void testGetCustomerProfileImage4() {
        Customer customer = new Customer();
        customer.setAge(1);
        customer.setEmail("jane.doe@example.org");
        customer.setGender(Gender.MALE);
        customer.setId(1);
        customer.setName("Name");
        customer.setProfileImageId("42");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerDao.selectCustomerById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(customerDTOMapper.apply(Mockito.<Customer>any())).thenReturn(
                new CustomerDTO(1, "Name", "jane.doe@example.org", Gender.MALE, 1, new ArrayList<>(), "janedoe", ""));
        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerProfileImage(1));
        verify(customerDao).selectCustomerById(Mockito.<Integer>any());
        verify(customerDTOMapper).apply(Mockito.<Customer>any());
    }
}

