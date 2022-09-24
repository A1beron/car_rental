package com.books.dao.address;

import com.books.dao.EntityDao;
import com.books.dao.EntityLoader;
import com.books.model.Address;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddressDao implements AddressLoader {

    private final EntityLoader<Address> addressCreator;

    public AddressDao() {
        addressCreator = new EntityDao<>();
    }

    @Override
    public Address createAddress(Address address) {
        return addressCreator.createEntity(address);
    }

}
