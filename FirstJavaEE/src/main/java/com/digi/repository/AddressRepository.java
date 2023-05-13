package com.digi.repository;

import com.digi.model.Address;

public interface AddressRepository {

    void saveAddress(Address address);

    Address getByUserId(int userId);

    void deleteAddress(Address address);
}
