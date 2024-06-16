package com.store.fake.proxy;

import com.store.fake.proxy.response.users.DataResponseUsers;

import java.util.List;

public interface IUserServiceProxy {
   List<DataResponseUsers> getAll();

   DataResponseUsers getInformation(String email);

}
