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
import org.alfresco.client.services.common.model.parameters.IncludeParam;
import org.alfresco.client.services.common.model.representation.ResultPaging;
import org.alfresco.client.services.content.core.CoreConstant;
import org.alfresco.client.services.content.core.model.representation.DeletedNodeRepresentation;
import org.alfresco.client.services.content.core.model.representation.NodeRepresentation;

import retrofit2.Call;
import retrofit2.http.*;
import rx.Observable;

public interface TrashcanAPI
{
    // ///////////////////////////////////////////////////////////////////////////
    // LISTING
    // ///////////////////////////////////////////////////////////////////////////
    /**
     * Get deleted nodes Returns a list of deleted nodes for the current user.
     * If the current user is an administrator deleted nodes for all users will
     * be returned. The list of deleted nodes will be ordered with the most
     * recently deleted node at the top of the list.
     * 
     * @return ResultPaging<DeletedNodeRepresentation>
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/deleted-nodes")
    Call<ResultPaging<DeletedNodeRepresentation>> listDeletedNodesCall();

    /**
     * Get deleted nodes Returns a list of deleted nodes for the current user.
     * If the current user is an administrator deleted nodes for all users will
     * be returned. The list of deleted nodes will be ordered with the most
     * recently deleted node at the top of the list.
     * 
     * @param skipCount The number of entities that exist in the collection
     *            before those included in this list. (optional)
     * @param maxItems The maximum number of items to return in the list.
     *            (optional)
     * @param include Returns additional information about the node. The
     *            following optional fields can be requested: * properties *
     *            aspects * path * isLink * allowableOperations * association
     *            (optional)
     * @return ResultPaging<DeletedNodeRepresentation>
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/deleted-nodes")
    Call<ResultPaging<DeletedNodeRepresentation>> listDeletedNodesCall(
            @Query(SKIP_COUNT) Integer skipCount, @Query(MAX_ITEMS) Integer maxItems,
            @Query(INCLUDE) IncludeParam include);

    // ///////////////////////////////////////////////////////////////////////////
    // INFO
    // ///////////////////////////////////////////////////////////////////////////
    /**
     * Get a deleted node Returns a specific deleted node identified by
     * **nodeId**.
     * 
     * @param nodeId The identifier of a node. (required)
     * @return DeletedNodeRepresentation
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/deleted-nodes/{nodeId}")
    Call<DeletedNodeRepresentation> getDeletedNodeCall(@Path(NODE_ID) String nodeId);

    /**
     * Get a deleted node Returns a specific deleted node identified by
     * **nodeId**.
     * 
     * @param nodeId The identifier of a node. (required)
     * @param include Returns additional information about the node. The
     *            following optional fields can be requested: * path * isLink *
     *            allowableOperations (optional)
     * @return DeletedNodeRepresentation
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/deleted-nodes/{nodeId}")
    Call<DeletedNodeRepresentation> getDeletedNodeCall(@Path(NODE_ID) String nodeId,
            @Query(INCLUDE) IncludeParam include);

    // ///////////////////////////////////////////////////////////////////////////
    // RESTORE
    // ///////////////////////////////////////////////////////////////////////////
    /**
     * Restore a deleted node Attempts to restore the deleted node identified by
     * **nodeId** to its original location. If the node is successfully restored
     * to it&#39;s former primary parent, then only the primary child
     * association will be restored, including recursively for any primary
     * children. It should be noted that no other secondary child association or
     * peer association will be restored, for any of the nodes within the
     * primary parent-child hierarchy of restored nodes, irrespective of whether
     * these association were to nodes within or outside of the restored
     * hierarchy. Also, any previously shared link will not be restored since it
     * is removed at the time of delete of each node.
     * 
     * @param nodeId The identifier of a node. (required)
     * @return NodeRepresentation
     */
    @POST(CoreConstant.CORE_PUBLIC_API_V1 + "/deleted-nodes/{nodeId}/restore")
    Call<NodeRepresentation> restoreDeletedNodeCall(@Path(NODE_ID) String nodeId, @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // DELETE
    // ///////////////////////////////////////////////////////////////////////////
    /**
     * Purge a deleted node Permanently removes the deleted node identified by
     * **nodeId**.
     * 
     * @param nodeId The identifier of a node. (required)
     */
    @DELETE(CoreConstant.CORE_PUBLIC_API_V1 + "/deleted-nodes/{nodeId}")
    Call<Void> purgeDeletedNodeCall(@Path(NODE_ID) String nodeId);

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
    // LISTING
    // ///////////////////////////////////////////////////////////////////////////

    /**
     * Get deleted nodes Returns a list of deleted nodes for the current user.
     * If the current user is an administrator deleted nodes for all users will
     * be returned. The list of deleted nodes will be ordered with the most
     * recently deleted node at the top of the list.
     *
     * @return ResultPaging<DeletedNodeRepresentation>
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/deleted-nodes")
    Observable<ResultPaging<DeletedNodeRepresentation>> listDeletedNodesObservable();

    /**
     * Get deleted nodes Returns a list of deleted nodes for the current user.
     * If the current user is an administrator deleted nodes for all users will
     * be returned. The list of deleted nodes will be ordered with the most
     * recently deleted node at the top of the list.
     *
     * @param skipCount The number of entities that exist in the collection
     *            before those included in this list. (optional)
     * @param maxItems The maximum number of items to return in the list.
     *            (optional)
     * @param include Returns additional information about the node. The
     *            following optional fields can be requested: * properties *
     *            aspects * path * isLink * allowableOperations * association
     *            (optional)
     * @return ResultPaging<DeletedNodeRepresentation>
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/deleted-nodes")
    Observable<ResultPaging<DeletedNodeRepresentation>> listDeletedNodesObservable(
            @Query(SKIP_COUNT) Integer skipCount, @Query(MAX_ITEMS) Integer maxItems,
            @Query(INCLUDE) IncludeParam include);

    // ///////////////////////////////////////////////////////////////////////////
    // INFO
    // ///////////////////////////////////////////////////////////////////////////

    /**
     * Get a deleted node Returns a specific deleted node identified by
     * **nodeId**.
     *
     * @param nodeId The identifier of a node. (required)
     * @return DeletedNodeRepresentation
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/deleted-nodes/{nodeId}")
    Observable<DeletedNodeRepresentation> getDeletedNodeObservable(@Path(NODE_ID) String nodeId);

    /**
     * Get a deleted node Returns a specific deleted node identified by
     * **nodeId**.
     *
     * @param nodeId The identifier of a node. (required)
     * @param include Returns additional information about the node. The
     *            following optional fields can be requested: * path * isLink *
     *            allowableOperations (optional)
     * @return DeletedNodeRepresentation
     */
    @GET(CoreConstant.CORE_PUBLIC_API_V1 + "/deleted-nodes/{nodeId}")
    Observable<DeletedNodeRepresentation> getDeletedNodeObservable(@Path(NODE_ID) String nodeId,
            @Query(INCLUDE) IncludeParam include);

    // ///////////////////////////////////////////////////////////////////////////
    // RESTORE
    // ///////////////////////////////////////////////////////////////////////////

    /**
     * Restore a deleted node Attempts to restore the deleted node identified by
     * **nodeId** to its original location. If the node is successfully restored
     * to it&#39;s former primary parent, then only the primary child
     * association will be restored, including recursively for any primary
     * children. It should be noted that no other secondary child association or
     * peer association will be restored, for any of the nodes within the
     * primary parent-child hierarchy of restored nodes, irrespective of whether
     * these association were to nodes within or outside of the restored
     * hierarchy. Also, any previously shared link will not be restored since it
     * is removed at the time of delete of each node.
     *
     * @param nodeId The identifier of a node. (required)
     * @return NodeRepresentation
     */
    @POST(CoreConstant.CORE_PUBLIC_API_V1 + "/deleted-nodes/{nodeId}/restore")
    Observable<NodeRepresentation> restoreDeletedNodeObservable(@Path(NODE_ID) String nodeId,
            @Query(FIELDS) FieldsParam fields);

    // ///////////////////////////////////////////////////////////////////////////
    // DELETE
    // ///////////////////////////////////////////////////////////////////////////

    /**
     * Purge a deleted node Permanently removes the deleted node identified by
     * **nodeId**.
     *
     * @param nodeId The identifier of a node. (required)
     */
    @DELETE(CoreConstant.CORE_PUBLIC_API_V1 + "/deleted-nodes/{nodeId}")
    Observable<Void> purgeDeletedNodeObservable(@Path(NODE_ID) String nodeId);

}
