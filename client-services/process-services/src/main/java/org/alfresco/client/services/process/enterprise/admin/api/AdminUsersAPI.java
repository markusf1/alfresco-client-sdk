/*
 *  Copyright (C) 2005-2016 Alfresco Software Limited.
 *
 *  This file is part of Alfresco Activiti Client.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.alfresco.client.services.process.enterprise.admin.api;

import static org.alfresco.client.services.process.enterprise.ProcessServicesConstant.PROCESS_SERVICE_PATH;
import static org.alfresco.client.services.process.enterprise.common.constant.RequestConstant.*;

import java.util.List;

import org.alfresco.client.services.process.enterprise.admin.model.body.BulkUserUpdateRepresentation;
import org.alfresco.client.services.process.enterprise.admin.model.representation.LightTenantRepresentation;
import org.alfresco.client.services.process.enterprise.core.model.idm.UserRepresentation;

import retrofit2.Call;
import retrofit2.http.*;
import rx.Observable;

/**
 *
 */
public interface AdminUsersAPI
{
    // USERS
    // ///////////////////////////////////////////////////////////////////
    @GET(PROCESS_SERVICE_PATH + "/admin/users")
    Call<List<LightTenantRepresentation>> getUsersCall(@Query(FILTER) String filter, @Query(STATUS) String status,
            @Query(ACCOUNT_TYPE) String accountType, @Query(SORT) String sort, @Query(COMPANY) String company,
            @Query(START) String start, @Query(PAGE) String page, @Query(SIZE) String size,
            @Query(GROUP_ID) String groupId, @Query(TENANT_ID) String tenantId, @Query(SUMMARY) Boolean summary);

    @Headers({ "Content-type: application/json" })
    @POST(PROCESS_SERVICE_PATH + "/admin/users")
    Call<UserRepresentation> bulkUpdateUsersCall(@Body BulkUserUpdateRepresentation userRepresentation);

    @Headers({ "Content-type: application/json" })
    @POST(PROCESS_SERVICE_PATH + "/admin/users")
    Call<UserRepresentation> createNewUserCall(@Body UserRepresentation userRepresentation);

    @GET(PROCESS_SERVICE_PATH + "/admin/users/{userId}")
    Call<UserRepresentation> getUserCall(@Path(USER_ID) String userId, @Query(SUMMARY) Boolean summary);

    @Headers({ "Content-type: application/json" })
    @PUT(PROCESS_SERVICE_PATH + "/admin/users/{userId}")
    Call<UserRepresentation> updateUserDetailsCall(@Path(USER_ID) String tenantId,
            @Body UserRepresentation userRepresentation);

    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////

    // USERS
    // ///////////////////////////////////////////////////////////////////
    @GET(PROCESS_SERVICE_PATH + "/admin/users")
    Observable<List<LightTenantRepresentation>> getUsersObservable(@Query(FILTER) String filter,
            @Query(STATUS) String status, @Query(ACCOUNT_TYPE) String accountType, @Query(SORT) String sort,
            @Query(COMPANY) String company, @Query(START) String start, @Query(PAGE) String page,
            @Query(SIZE) String size, @Query(GROUP_ID) String groupId, @Query(TENANT_ID) String tenantId,
            @Query(SUMMARY) Boolean summary);

    @Headers({ "Content-type: application/json" })
    @POST(PROCESS_SERVICE_PATH + "/admin/users")
    Observable<UserRepresentation> bulkUpdateUsersObservable(@Body BulkUserUpdateRepresentation userRepresentation);

    @Headers({ "Content-type: application/json" })
    @POST(PROCESS_SERVICE_PATH + "/admin/users")
    Observable<UserRepresentation> createNewUserObservable(@Body UserRepresentation userRepresentation);

    @GET(PROCESS_SERVICE_PATH + "/admin/users/{userId}")
    Observable<UserRepresentation> getUserObservable(@Path(USER_ID) String userId, @Query(SUMMARY) Boolean summary);

    @Headers({ "Content-type: application/json" })
    @PUT(PROCESS_SERVICE_PATH + "/admin/users/{userId}")
    Observable<UserRepresentation> updateUserDetailsObservable(@Path(USER_ID) String tenantId,
            @Body UserRepresentation userRepresentation);

}
