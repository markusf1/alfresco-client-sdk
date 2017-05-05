/*
 *   Copyright (C) 2005-2016 Alfresco Software Limited.
 *
 *   This file is part of Alfresco Java Client.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.alfresco.client.services.content.core.api;

import static org.alfresco.client.services.common.constant.ApiConstant.*;

import org.alfresco.client.services.common.model.parameters.FieldsParam;
import org.alfresco.client.services.common.model.representation.ResultPaging;
import org.alfresco.client.services.content.core.CoreConstant;
import org.alfresco.client.services.content.core.model.body.GroupBodyCreate;
import org.alfresco.client.services.content.core.model.body.GroupBodyUpdate;
import org.alfresco.client.services.content.core.model.body.GroupMemberBodyAdd;
import org.alfresco.client.services.content.core.model.representation.GroupMemberRepresentation;
import org.alfresco.client.services.content.core.model.representation.GroupRepresentation;

import retrofit2.Call;
import retrofit2.http.*;
import rx.Observable;

public interface GroupsAPI
{
    // ///////////////////////////////////////////////////////////////////////////
    // LIST GROUPS
    // ///////////////////////////////////////////////////////////////////////////
    /**
     * List groups **Note:** this endpoint is available in Alfresco 5.2 and
     * newer versions. Gets a list of groups. You can use the **where**
     * parameter to filter the returned groups by **isRoot**. For example, the
     * following **where** clause will return just the root groups:
     * &#x60;&#x60;&#x60; (isRoot&#x3D;true) &#x60;&#x60;&#x60; The default sort
     * order for the returned list is for groups to be sorted by ascending
     * displayName. You can override the default by using the **orderBy**
     * parameter. You can specify one or more of the following fields in the
     * **orderBy** parameter: * id * displayName
     * 
     * @return GroupPaging
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups")
    Call<ResultPaging<GroupRepresentation>> listGroupsCall();

    /**
     * List groups **Note:** this endpoint is available in Alfresco 5.2 and
     * newer versions. Gets a list of groups. You can use the **where**
     * parameter to filter the returned groups by **isRoot**. For example, the
     * following **where** clause will return just the root groups:
     * &#x60;&#x60;&#x60; (isRoot&#x3D;true) &#x60;&#x60;&#x60; The default sort
     * order for the returned list is for groups to be sorted by ascending
     * displayName. You can override the default by using the **orderBy**
     * parameter. You can specify one or more of the following fields in the
     * **orderBy** parameter: * id * displayName
     * 
     * @param skipCount The number of entities that exist in the collection
     *            before those included in this list. (optional)
     * @param maxItems The maximum number of items to return in the list.
     *            (optional)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupPaging
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups")
    Call<ResultPaging<GroupRepresentation>> listGroupsCall(@Query(SKIP_COUNT) Integer skipCount,
            @Query(MAX_ITEMS) Integer maxItems, @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // GROUP INFO
    // ///////////////////////////////////////////////////////////////////////////
    /**
     * Get group details **Note:** this endpoint is available in Alfresco 5.2
     * and newer versions. Get details for group **groupId**.
     * 
     * @param groupId The identifier of a group. (required)
     * @return ApiResponse&lt;GroupEntry&gt;
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}")
    Call<GroupRepresentation> getGroupCall(@Path(GROUP_ID) String groupId);

    /**
     * Get group details **Note:** this endpoint is available in Alfresco 5.2
     * and newer versions. Get details for group **groupId**.
     * 
     * @param groupId The identifier of a group. (required)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return ApiResponse&lt;GroupEntry&gt;
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}")
    Call<GroupRepresentation> getGroupCall(@Path(GROUP_ID) String groupId, @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // CREATE GROUP
    // ///////////////////////////////////////////////////////////////////////////
    /**
     * Create a group **Note:** this endpoint is available in Alfresco 5.2 and
     * newer versions. Create a group. If the parent group is not specified then
     * this will be a root group (with no parents) otherwise the group will
     * become a member of the specified parent group. You must have admin rights
     * to create a group.
     *
     * @param groupBodyCreate The group to create. (required)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupEntry
     */
    @POST(CoreConstant.CORE_PUBLIC_API_V1 + "/groups")
    Call<GroupRepresentation> addGroupCall(@Body() GroupBodyCreate groupBodyCreate, @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // UPDATE GROUP
    // ///////////////////////////////////////////////////////////////////////////
    /**
     * Update group details **Note:** this endpoint is available in Alfresco 5.2
     * and newer versions. Update details (displayName) for group **groupId**.
     * You must have admin rights to update a group.
     * 
     * @param groupId The identifier of a group. (required)
     * @param groupBodyUpdate The group information to update. (required)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupEntry
     */
    @PUT(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}")
    Call<GroupRepresentation> updateGroupCall(@Path(GROUP_ID) String groupId, @Body() GroupBodyUpdate groupBodyUpdate,
            @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // DELETE GROUP
    // ///////////////////////////////////////////////////////////////////////////
    /**
     * Delete a group **Note:** this endpoint is available in Alfresco 5.2 and
     * newer versions. Delete group **groupId**. This applies recursively to any
     * hierarchy of group members. Removing a group member does not delete the
     * person or sub-group itself. If a removed sub-group no longer has any
     * parent groups then it becomes a root group. You must have admin rights to
     * delete a group.
     * 
     * @param groupId The identifier of a group. (required)
     */
    @DELETE(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}")
    Call<Void> deleteGroupCall(@Path(GROUP_ID) String groupId);

    // ///////////////////////////////////////////////////////////////////////////
    // LIST PARENT GROUP
    // ///////////////////////////////////////////////////////////////////////////
    /**
     * List parents of a group **Note:** this endpoint is available in Alfresco
     * 5.2 and newer versions. Gets a list of parent groups for group
     * **groupId**. A root group will have no parents. To list the groups that a
     * person belongs, see \&quot;listGroupMembershipsForPerson\&quot;. The
     * default sort order for the returned list is for the parent groups to be
     * sorted by ascending displayName. You can override the default by using
     * the **orderBy** parameter. You can specify one or more of the following
     * fields in the **orderBy** parameter: * id * displayName
     *
     * @param groupId The identifier of a group. (required)
     * @return GroupPaging
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/parents")
    Call<ResultPaging<GroupRepresentation>> listGroupParentsCall(@Path(GROUP_ID) String groupId);

    /**
     * List parents of a group **Note:** this endpoint is available in Alfresco
     * 5.2 and newer versions. Gets a list of parent groups for group
     * **groupId**. A root group will have no parents. To list the groups that a
     * person belongs, see \&quot;listGroupMembershipsForPerson\&quot;. The
     * default sort order for the returned list is for the parent groups to be
     * sorted by ascending displayName. You can override the default by using
     * the **orderBy** parameter. You can specify one or more of the following
     * fields in the **orderBy** parameter: * id * displayName
     * 
     * @param groupId The identifier of a group. (required)
     * @param skipCount The number of entities that exist in the collection
     *            before those included in this list. (optional)
     * @param maxItems The maximum number of items to return in the list.
     *            (optional)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupPaging
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/parents")
    Call<ResultPaging<GroupRepresentation>> listGroupParentsCall(@Path(GROUP_ID) String groupId,
            @Query(SKIP_COUNT) Integer skipCount, @Query(MAX_ITEMS) Integer maxItems,
            @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // LIST GROUP MEMBERS
    // ///////////////////////////////////////////////////////////////////////////
    /**
     * List members of a group **Note:** this endpoint is available in Alfresco
     * 5.2 and newer versions. Gets a list of members (persons or sub-groups)
     * for group **groupId**. The default sort order for the returned list is
     * for group members to be sorted by ascending displayName. You can override
     * the default by using the **orderBy** parameter. You can specify one or
     * more of the following fields in the **orderBy** parameter: * id *
     * displayName
     * 
     * @param groupId The identifier of a group. (required)
     * @return GroupMemberPaging
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/members")
    Call<ResultPaging<GroupMemberRepresentation>> listGroupMembersCall(@Path(GROUP_ID) String groupId);

    /**
     * List members of a group **Note:** this endpoint is available in Alfresco
     * 5.2 and newer versions. Gets a list of members (persons or sub-groups)
     * for group **groupId**. The default sort order for the returned list is
     * for group members to be sorted by ascending displayName. You can override
     * the default by using the **orderBy** parameter. You can specify one or
     * more of the following fields in the **orderBy** parameter: * id *
     * displayName
     * 
     * @param groupId The identifier of a group. (required)
     * @param skipCount The number of entities that exist in the collection
     *            before those included in this list. (optional)
     * @param maxItems The maximum number of items to return in the list.
     *            (optional)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupMemberPaging
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/members")
    Call<ResultPaging<GroupMemberRepresentation>> listGroupMembersCall(@Path(GROUP_ID) String groupId,
            @Query(SKIP_COUNT) Integer skipCount, @Query(MAX_ITEMS) Integer maxItems,
            @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // GET GROUP MEMBER
    // ///////////////////////////////////////////////////////////////////////////
    /**
     * Get a group member **Note:** this endpoint is available in Alfresco 5.2
     * and newer versions. Get group member information for **groupMemberId**
     * (person or sub-group) in group **groupId**.
     * 
     * @param groupId The identifier of a group. (required)
     * @param groupMemberId The identifier of a person or group. (required)
     * @return GroupMemberEntry
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/members/{groupMemberId}")
    Call<GroupRepresentation> getGroupMemberCall(@Path(GROUP_ID) String groupId,
            @Path(GROUP_MEMBER_ID) String groupMemberId);

    /**
     * Get a group member **Note:** this endpoint is available in Alfresco 5.2
     * and newer versions. Get group member information for **groupMemberId**
     * (person or sub-group) in group **groupId**.
     * 
     * @param groupId The identifier of a group. (required)
     * @param groupMemberId The identifier of a person or group. (required)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupMemberEntry
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/members/{groupMemberId}")
    Call<GroupRepresentation> getGroupMemberCall(@Path(GROUP_ID) String groupId,
            @Path(GROUP_MEMBER_ID) String groupMemberId, @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // ADD GROUP MEMBER
    // //////////////////////////////////////////////////////////////////////////
    /**
     * Add a group member **Note:** this endpoint is available in Alfresco 5.2
     * and newer versions. Add a group member (person or sub-group) to group
     * **groupId**. If the added sub-group was previously a root group then it
     * becomes a non-root group since it now has a parent. You must have admin
     * rights to add a group member.
     * 
     * @param groupId The identifier of a group. (required)
     * @param groupMemberBodyAdd The group member to add (person or sub-group).
     *            (required)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupMemberEntry
     */
    @POST(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/members")
    Call<GroupMemberRepresentation> addGroupMemberCall(@Path(GROUP_ID) String groupId,
            @Body() GroupMemberBodyAdd groupMemberBodyAdd, @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // DELETE GROUP MEMBER
    // //////////////////////////////////////////////////////////////////////////
    /**
     * Delete a group member **Note:** this endpoint is available in Alfresco
     * 5.2 and newer versions. Delete group member **groupMemberId** (person or
     * sub-group) from group **groupId**. Removing a group member does not
     * delete the person or sub-group itself. If a removed sub-group no longer
     * has any parent groups then it becomes a root group. You must have admin
     * rights to delete a group member.
     * 
     * @param groupId The identifier of a group. (required)
     * @param groupMemberId The identifier of a person or group. (required)
     * @return ApiResponse&lt;Void&gt;
     */
    @DELETE(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/members/{groupMemberId}")
    Call<Void> deleteGroupMemberCall(@Path(GROUP_ID) String groupId, @Path(GROUP_MEMBER_ID) String groupMemberId);

    // ///////////////////////////////////////////////////////////////////////////
    // LIST GROUP MEMBERSHIP
    // //////////////////////////////////////////////////////////////////////////
    /**
     * List group memberships Gets a list of group membership information for
     * person **personId**. You can use the &#x60;-me-&#x60; string in place of
     * &#x60;&lt;personId&gt;&#x60; to specify the currently authenticated user.
     * The default sort order for the returned list is for groups to be sorted
     * by ascending displayName. You can override the default by using the
     * **orderBy** parameter. You can specify one or more of the following
     * fields in the **orderBy** parameter: * id * displayName
     * 
     * @param personId The identifier of a person. (required)
     * @return ApiResponse&lt;GroupPaging&gt;
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/people/{personId}/groups")
    Call<ResultPaging<GroupRepresentation>> listGroupsCall(@Path(PERSON_ID) String personId);

    /**
     * List group memberships Gets a list of group membership information for
     * person **personId**. You can use the &#x60;-me-&#x60; string in place of
     * &#x60;&lt;personId&gt;&#x60; to specify the currently authenticated user.
     * The default sort order for the returned list is for groups to be sorted
     * by ascending displayName. You can override the default by using the
     * **orderBy** parameter. You can specify one or more of the following
     * fields in the **orderBy** parameter: * id * displayName
     * 
     * @param personId The identifier of a person. (required)
     * @param skipCount The number of entities that exist in the collection
     *            before those included in this list. (optional)
     * @param maxItems The maximum number of items to return in the list.
     *            (optional)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return ApiResponse&lt;GroupPaging&gt;
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/people/{personId}/groups")
    Call<ResultPaging<GroupRepresentation>> listGroupsCall(@Path(PERSON_ID) String personId,
            @Query(SKIP_COUNT) Integer skipCount, @Query(MAX_ITEMS) Integer maxItems,
            @Query(FIELDS) FieldsParam fields);

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

    // ///////////////////////////////////////////////////////////////////////////
    // LIST GROUPS
    // ///////////////////////////////////////////////////////////////////////////

    /**
     * List groups **Note:** this endpoint is available in Alfresco 5.2 and
     * newer versions. Gets a list of groups. You can use the **where**
     * parameter to filter the returned groups by **isRoot**. For example, the
     * following **where** clause will return just the root groups:
     * &#x60;&#x60;&#x60; (isRoot&#x3D;true) &#x60;&#x60;&#x60; The default sort
     * order for the returned list is for groups to be sorted by ascending
     * displayName. You can override the default by using the **orderBy**
     * parameter. You can specify one or more of the following fields in the
     * **orderBy** parameter: * id * displayName
     *
     * @return GroupPaging
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups")
    Observable<ResultPaging<GroupRepresentation>> listGroupsObservable();

    /**
     * List groups **Note:** this endpoint is available in Alfresco 5.2 and
     * newer versions. Gets a list of groups. You can use the **where**
     * parameter to filter the returned groups by **isRoot**. For example, the
     * following **where** clause will return just the root groups:
     * &#x60;&#x60;&#x60; (isRoot&#x3D;true) &#x60;&#x60;&#x60; The default sort
     * order for the returned list is for groups to be sorted by ascending
     * displayName. You can override the default by using the **orderBy**
     * parameter. You can specify one or more of the following fields in the
     * **orderBy** parameter: * id * displayName
     *
     * @param skipCount The number of entities that exist in the collection
     *            before those included in this list. (optional)
     * @param maxItems The maximum number of items to return in the list.
     *            (optional)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupPaging
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups")
    Observable<ResultPaging<GroupRepresentation>> listGroupsObservable(@Query(SKIP_COUNT) Integer skipCount,
            @Query(MAX_ITEMS) Integer maxItems, @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // GROUP INFO
    // ///////////////////////////////////////////////////////////////////////////

    /**
     * Get group details **Note:** this endpoint is available in Alfresco 5.2
     * and newer versions. Get details for group **groupId**.
     *
     * @param groupId The identifier of a group. (required)
     * @return ApiResponse&lt;GroupEntry&gt;
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}")
    Observable<GroupRepresentation> getGroupObservable(@Path(GROUP_ID) String groupId);

    /**
     * Get group details **Note:** this endpoint is available in Alfresco 5.2
     * and newer versions. Get details for group **groupId**.
     *
     * @param groupId The identifier of a group. (required)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return ApiResponse&lt;GroupEntry&gt;
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}")
    Observable<GroupRepresentation> getGroupObservable(@Path(GROUP_ID) String groupId,
            @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // CREATE GROUP
    // ///////////////////////////////////////////////////////////////////////////

    /**
     * Create a group **Note:** this endpoint is available in Alfresco 5.2 and
     * newer versions. Create a group. If the parent group is not specified then
     * this will be a root group (with no parents) otherwise the group will
     * become a member of the specified parent group. You must have admin rights
     * to create a group.
     *
     * @param groupBodyCreate The group to create. (required)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupEntry
     */
    @POST(CoreConstant.CORE_PUBLIC_API_V1 + "/groups")
    Observable<GroupRepresentation> addGroupObservable(@Body() GroupBodyCreate groupBodyCreate,
            @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // UPDATE GROUP
    // ///////////////////////////////////////////////////////////////////////////

    /**
     * Update group details **Note:** this endpoint is available in Alfresco 5.2
     * and newer versions. Update details (displayName) for group **groupId**.
     * You must have admin rights to update a group.
     *
     * @param groupId The identifier of a group. (required)
     * @param groupBodyUpdate The group information to update. (required)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupEntry
     */
    @PUT(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}")
    Observable<GroupRepresentation> updateGroupObservable(@Path(GROUP_ID) String groupId,
            @Body() GroupBodyUpdate groupBodyUpdate, @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // DELETE GROUP
    // ///////////////////////////////////////////////////////////////////////////

    /**
     * Delete a group **Note:** this endpoint is available in Alfresco 5.2 and
     * newer versions. Delete group **groupId**. This applies recursively to any
     * hierarchy of group members. Removing a group member does not delete the
     * person or sub-group itself. If a removed sub-group no longer has any
     * parent groups then it becomes a root group. You must have admin rights to
     * delete a group.
     *
     * @param groupId The identifier of a group. (required)
     */
    @DELETE(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}")
    Observable<Void> deleteGroupObservable(@Path(GROUP_ID) String groupId);

    // ///////////////////////////////////////////////////////////////////////////
    // LIST PARENT GROUP
    // ///////////////////////////////////////////////////////////////////////////

    /**
     * List parents of a group **Note:** this endpoint is available in Alfresco
     * 5.2 and newer versions. Gets a list of parent groups for group
     * **groupId**. A root group will have no parents. To list the groups that a
     * person belongs, see \&quot;listGroupMembershipsForPerson\&quot;. The
     * default sort order for the returned list is for the parent groups to be
     * sorted by ascending displayName. You can override the default by using
     * the **orderBy** parameter. You can specify one or more of the following
     * fields in the **orderBy** parameter: * id * displayName
     *
     * @param groupId The identifier of a group. (required)
     * @return GroupPaging
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/parents")
    Observable<ResultPaging<GroupRepresentation>> listGroupParentsObservable(@Path(GROUP_ID) String groupId);

    /**
     * List parents of a group **Note:** this endpoint is available in Alfresco
     * 5.2 and newer versions. Gets a list of parent groups for group
     * **groupId**. A root group will have no parents. To list the groups that a
     * person belongs, see \&quot;listGroupMembershipsForPerson\&quot;. The
     * default sort order for the returned list is for the parent groups to be
     * sorted by ascending displayName. You can override the default by using
     * the **orderBy** parameter. You can specify one or more of the following
     * fields in the **orderBy** parameter: * id * displayName
     *
     * @param groupId The identifier of a group. (required)
     * @param skipCount The number of entities that exist in the collection
     *            before those included in this list. (optional)
     * @param maxItems The maximum number of items to return in the list.
     *            (optional)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupPaging
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/parents")
    Observable<ResultPaging<GroupRepresentation>> listGroupParentsObservable(@Path(GROUP_ID) String groupId,
            @Query(SKIP_COUNT) Integer skipCount, @Query(MAX_ITEMS) Integer maxItems,
            @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // LIST GROUP MEMBERS
    // ///////////////////////////////////////////////////////////////////////////

    /**
     * List members of a group **Note:** this endpoint is available in Alfresco
     * 5.2 and newer versions. Gets a list of members (persons or sub-groups)
     * for group **groupId**. The default sort order for the returned list is
     * for group members to be sorted by ascending displayName. You can override
     * the default by using the **orderBy** parameter. You can specify one or
     * more of the following fields in the **orderBy** parameter: * id *
     * displayName
     *
     * @param groupId The identifier of a group. (required)
     * @return GroupMemberPaging
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/members")
    Observable<ResultPaging<GroupMemberRepresentation>> listGroupMembersObservable(@Path(GROUP_ID) String groupId);

    /**
     * List members of a group **Note:** this endpoint is available in Alfresco
     * 5.2 and newer versions. Gets a list of members (persons or sub-groups)
     * for group **groupId**. The default sort order for the returned list is
     * for group members to be sorted by ascending displayName. You can override
     * the default by using the **orderBy** parameter. You can specify one or
     * more of the following fields in the **orderBy** parameter: * id *
     * displayName
     *
     * @param groupId The identifier of a group. (required)
     * @param skipCount The number of entities that exist in the collection
     *            before those included in this list. (optional)
     * @param maxItems The maximum number of items to return in the list.
     *            (optional)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupMemberPaging
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/members")
    Observable<ResultPaging<GroupMemberRepresentation>> listGroupMembersObservable(@Path(GROUP_ID) String groupId,
            @Query(SKIP_COUNT) Integer skipCount, @Query(MAX_ITEMS) Integer maxItems,
            @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // GET GROUP MEMBER
    // ///////////////////////////////////////////////////////////////////////////

    /**
     * Get a group member **Note:** this endpoint is available in Alfresco 5.2
     * and newer versions. Get group member information for **groupMemberId**
     * (person or sub-group) in group **groupId**.
     *
     * @param groupId The identifier of a group. (required)
     * @param groupMemberId The identifier of a person or group. (required)
     * @return GroupMemberEntry
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/members/{groupMemberId}")
    Observable<GroupRepresentation> getGroupMemberObservable(@Path(GROUP_ID) String groupId,
            @Path(GROUP_MEMBER_ID) String groupMemberId);

    /**
     * Get a group member **Note:** this endpoint is available in Alfresco 5.2
     * and newer versions. Get group member information for **groupMemberId**
     * (person or sub-group) in group **groupId**.
     *
     * @param groupId The identifier of a group. (required)
     * @param groupMemberId The identifier of a person or group. (required)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupMemberEntry
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/members/{groupMemberId}")
    Observable<GroupRepresentation> getGroupMemberObservable(@Path(GROUP_ID) String groupId,
            @Path(GROUP_MEMBER_ID) String groupMemberId, @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // ADD GROUP MEMBER
    // //////////////////////////////////////////////////////////////////////////

    /**
     * Add a group member **Note:** this endpoint is available in Alfresco 5.2
     * and newer versions. Add a group member (person or sub-group) to group
     * **groupId**. If the added sub-group was previously a root group then it
     * becomes a non-root group since it now has a parent. You must have admin
     * rights to add a group member.
     *
     * @param groupId The identifier of a group. (required)
     * @param groupMemberBodyAdd The group member to add (person or sub-group).
     *            (required)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return GroupMemberEntry
     */
    @POST(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/members")
    Observable<GroupMemberRepresentation> addGroupMemberObservable(@Path(GROUP_ID) String groupId,
            @Body() GroupMemberBodyAdd groupMemberBodyAdd, @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // DELETE GROUP MEMBER
    // //////////////////////////////////////////////////////////////////////////

    /**
     * Delete a group member **Note:** this endpoint is available in Alfresco
     * 5.2 and newer versions. Delete group member **groupMemberId** (person or
     * sub-group) from group **groupId**. Removing a group member does not
     * delete the person or sub-group itself. If a removed sub-group no longer
     * has any parent groups then it becomes a root group. You must have admin
     * rights to delete a group member.
     *
     * @param groupId The identifier of a group. (required)
     * @param groupMemberId The identifier of a person or group. (required)
     * @return ApiResponse&lt;Void&gt;
     */
    @DELETE(CoreConstant.CORE_PUBLIC_API_V1 + "/groups/{groupId}/members/{groupMemberId}")
    Observable<Void> deleteGroupMemberObservable(@Path(GROUP_ID) String groupId,
            @Path(GROUP_MEMBER_ID) String groupMemberId);

    // ///////////////////////////////////////////////////////////////////////////
    // LIST GROUP MEMBERSHIP
    // //////////////////////////////////////////////////////////////////////////

    /**
     * List group memberships Gets a list of group membership information for
     * person **personId**. You can use the &#x60;-me-&#x60; string in place of
     * &#x60;&lt;personId&gt;&#x60; to specify the currently authenticated user.
     * The default sort order for the returned list is for groups to be sorted
     * by ascending displayName. You can override the default by using the
     * **orderBy** parameter. You can specify one or more of the following
     * fields in the **orderBy** parameter: * id * displayName
     *
     * @param personId The identifier of a person. (required)
     * @return ApiResponse&lt;GroupPaging&gt;
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/people/{personId}/groups")
    Observable<ResultPaging<GroupRepresentation>> listGroupsObservable(@Path(PERSON_ID) String personId);

    /**
     * List group memberships Gets a list of group membership information for
     * person **personId**. You can use the &#x60;-me-&#x60; string in place of
     * &#x60;&lt;personId&gt;&#x60; to specify the currently authenticated user.
     * The default sort order for the returned list is for groups to be sorted
     * by ascending displayName. You can override the default by using the
     * **orderBy** parameter. You can specify one or more of the following
     * fields in the **orderBy** parameter: * id * displayName
     *
     * @param personId The identifier of a person. (required)
     * @param skipCount The number of entities that exist in the collection
     *            before those included in this list. (optional)
     * @param maxItems The maximum number of items to return in the list.
     *            (optional)
     * @param fields A list of field names. You can use this parameter to
     *            restrict the fields returned within a response if, for
     *            example, you want to save on overall bandwidth. The list
     *            applies to a returned individual entity or entries within a
     *            collection. If the API method also supports the **include**
     *            parameter, then the fields specified in the **include**
     *            parameter are returned in addition to those specified in the
     *            **fields** parameter. (optional)
     * @return ApiResponse&lt;GroupPaging&gt;
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/people/{personId}/groups")
    Observable<ResultPaging<GroupRepresentation>> listGroupsObservable(@Path(PERSON_ID) String personId,
            @Query(SKIP_COUNT) Integer skipCount, @Query(MAX_ITEMS) Integer maxItems,
            @Query(FIELDS) FieldsParam fields);
}
