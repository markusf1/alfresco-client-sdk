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

package org.alfresco.client.services.common.model.deserializer;

import static org.alfresco.client.services.common.constant.ApiConstant.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.alfresco.client.services.common.model.representation.PaginationRepresentation;
import org.alfresco.client.services.common.model.representation.ResultPaging;

import com.google.gson.*;

/**
 * Created by jpascal on 19/01/2016.
 */
public class PagingDeserializer<T> implements JsonDeserializer<ResultPaging<T>>
{
    protected Class<T> clazz;

    public PagingDeserializer(Class<T> clazz)
    {
        this.clazz = clazz;
    }

    @Override
    public ResultPaging<T> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException
    {
        JsonObject pagination = null;
        JsonArray entries = null;
        if (je.getAsJsonObject().has(LIST))
        {
            pagination = je.getAsJsonObject().get(LIST).getAsJsonObject().get(PAGINATION).getAsJsonObject();
            entries = je.getAsJsonObject().get(LIST).getAsJsonObject().get(ENTRIES).getAsJsonArray();
        }

        final List<T> result = new ArrayList<>();
        T obj = null;
        for (Object entry : entries)
        {
            obj = jdc.deserialize((JsonElement) entry, clazz);
            result.add(obj);
        }

        PaginationRepresentation paging = jdc.deserialize(pagination, PaginationRepresentation.class);

        return new ResultPaging(result, paging);
    }

}
