package entity;

import data_access.DataAccessMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhotoLocationFactoryTest {

    PhotoLocationFactory factory;

    @BeforeEach
    void setUp() {
        factory = new PhotoLocationFactory(new DataAccessMock());
    }

    @Test
    void getRandomLocation() {
        assertInstanceOf(PhotoLocation.class, factory.getRandomLocation());
    }
}