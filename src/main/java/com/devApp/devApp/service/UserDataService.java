package com.devApp.devApp.service;

import com.devApp.devApp.model.domain.UserData;
import com.devApp.devApp.model.dto.UrlDataRequestDto;
import com.devApp.devApp.model.dto.UrlDataResponseDto;
import com.devApp.devApp.repository.UserDataRepository;
import com.devApp.devApp.util.UniqueIdGenerator;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UniqueIdGenerator uniqueIdGenerator;

    @Transactional
    public UrlDataResponseDto submitUserUrlData(UrlDataRequestDto urlDataRequestDto, String accountId) throws Exception{

        if (ObjectUtils.isEmpty(urlDataRequestDto) || ObjectUtils.isEmpty(urlDataRequestDto.getUrl())) {
            throw new Exception("Please enter a proper url.");
        }

        UserData userData = userDataRepository.findByAccountId(accountId);

        if (ObjectUtils.isEmpty(userData)) {
            userData = UserData.Builder.userData()
                .withUserDataId(uniqueIdGenerator.getUniqueId())
                .withAccountId(accountId)
                .withUrlData(urlDataRequestDto.getUrl())
                .build();
        } else {
            userData.setUrlData(urlDataRequestDto.getUrl());
        }

        UserData updatedUserData = userDataRepository.save(userData);

        return UrlDataResponseDto.Builder.urlDataResponseDto()
            .withUrl(updatedUserData.getUrlData())
            .withAccountId(updatedUserData.getAccountId())
            .withUserDataId(updatedUserData.getUserDataId())
            .build();
    }

    public UrlDataResponseDto getUserUrlData(@NotNull String accountId) {
        UserData userData = userDataRepository.findByAccountId(accountId);

        UrlDataResponseDto urlDataResponseDto = UrlDataResponseDto.Builder.urlDataResponseDto()
            .withUrl(userData.getUrlData())
            .withAccountId(userData.getAccountId())
            .withUserDataId(userData.getUserDataId())
            .build();

        return urlDataResponseDto;
    }
}
