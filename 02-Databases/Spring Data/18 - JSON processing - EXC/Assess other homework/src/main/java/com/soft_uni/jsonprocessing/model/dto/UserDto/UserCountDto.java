package com.soft_uni.jsonprocessing.model.dto.UserDto;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UserCountDto {
    @Expose
    private Integer usersCount;
    @Expose
    private List<UserNameAgeAndSoldProductsDto> users;

    public UserCountDto() {
    }

    public UserCountDto(Integer count, List<UserNameAgeAndSoldProductsDto> users) {
        this.usersCount = count;
        this.users = users;
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserNameAgeAndSoldProductsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserNameAgeAndSoldProductsDto> users) {
        this.users = users;
    }
}
